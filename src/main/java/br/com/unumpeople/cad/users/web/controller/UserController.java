package br.com.unumpeople.cad.users.web.controller;


import br.com.unumpeople.cad.users.core.domain.Address;
import br.com.unumpeople.cad.users.core.domain.Document;
import br.com.unumpeople.cad.users.core.domain.User;
import br.com.unumpeople.cad.users.core.ports.UserServicePort;
import br.com.unumpeople.cad.users.web.converter.UserConverter;
import br.com.unumpeople.cad.users.web.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    private final UserServicePort usuarioService;
    private final UserConverter userConverter;

    @Operation(summary = "Busca todos os usuários",
            security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class)) })
    })
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> buscarTodasOsUsuarios() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Set<User> users = usuarioService.getAllUsers();
        return ResponseEntity.ok(users.stream().map(userConverter::toResponseDto).collect(Collectors.toList()));
    }

    @Operation(summary = "Busca um usuario pelo ID",
            security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Usuario encontrado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = { @Content(schema = @Schema(implementation = Object.class)) })
    })
    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> buscarUsuarioPorId(@PathVariable("id") String id) {
        User user = usuarioService.getUserById(id);
        return ResponseEntity.ok(userConverter.toResponseDto(user));
    }


    @Operation(summary = "Cria um novo Usuario",
            security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = { @Content(schema = @Schema(implementation = Object.class)) })
    })
    @PostMapping
    public ResponseEntity<UserResponseDto> criarUsuario(@RequestBody UserCreateRequestDto userDto) throws InvalidAttributeValueException {
        User user = usuarioService.createUser(userConverter.toDomain(userDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(userConverter.toResponseDto(user));
    }

    @Operation(summary = "Atualiza uma Usuario",
            security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = { @Content(schema = @Schema(implementation = Object.class)) })
    })
    @PutMapping("{id}")
    public ResponseEntity<UserResponseDto> editarUsuario(@PathVariable String id, @RequestBody UserUpdateRequestDto userDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getPrincipal();
            String userId = jwt.getClaim("userId");
            log.info("userId: ".concat(userId));
        }
        User user = usuarioService.updateUser(id, Objects.requireNonNull(userConverter.toDomain(userDto)));
        return ResponseEntity.status(HttpStatus.CREATED).body(userConverter.toResponseDto(user));
    }

    @Operation(summary = "Exclui um usuario pelo ID",
            security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable String id) throws InvalidAttributeValueException {
        usuarioService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Busca um usuario pelo name",
            security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Usuarios encontrados",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = { @Content(schema = @Schema(implementation = Object.class)) })
    })
    @PostMapping("buscar-por-name")
    public ResponseEntity<Set<UserResponseDto>> buscarUsuarioPorNome(@RequestBody String nome) throws InvalidAttributeValueException {
        return ResponseEntity.ok(usuarioService
                .getUserByName(nome)
                .stream()
                .map(userConverter::toResponseDto)
                .collect(Collectors.toSet()));
    }

    @Operation(summary = "Busca um usuario pelo tipo",
            security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Usuarios encontrados",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Usuários não encontrados",
                    content = { @Content(schema = @Schema(implementation = Object.class)) })
    })
    @PostMapping("buscar-por-tipo")
    public ResponseEntity<Set<UserResponseDto>> buscarUsuarioPorTipo(@RequestBody UserRoleContextDto userRoleContextDto) throws InvalidAttributeValueException, javax.naming.directory.InvalidAttributeValueException {
        return ResponseEntity.ok(usuarioService
                .getUserByRoleContext(userRoleContextDto.role(), userRoleContextDto.context())
                .stream()
                .map(userConverter::toResponseDto)
                .collect(Collectors.toSet()));
    }

    @Operation(summary = "Adiciona um endereço a um usuário",
            security = @SecurityRequirement(name = "security_auth"))
    @PostMapping("/{userId}/addresses")
    public ResponseEntity<UserResponseDto> addAddressToUser(
            @PathVariable String userId,
            @RequestBody AddressRequestDto addressRequestDto) {
        Address address = userConverter.toAddress(addressRequestDto);
        User updatedUser = usuarioService.addAddress(userId, address);
        return ResponseEntity.ok(userConverter.toResponseDto(updatedUser));
    }

    @Operation(summary = "Remove um endereço de um usuário",
            security = @SecurityRequirement(name = "security_auth"))
    @DeleteMapping("/{userId}/addresses/{addressId}")
    public ResponseEntity<UserResponseDto> removeAddressFromUser(
            @PathVariable String userId,
            @PathVariable String addressId) {
        User updatedUser = usuarioService.removeAddress(userId, addressId);
        return ResponseEntity.ok(userConverter.toResponseDto(updatedUser));
    }

    @Operation(summary = "Adiciona um documento a um usuário",
            security = @SecurityRequirement(name = "security_auth"))
    @PostMapping("/{userId}/documents")
    public ResponseEntity<UserResponseDto> addDocumentToUser(
            @PathVariable String userId,
            @RequestBody DocumentRequestDto documentRequestDto) {
        Document document = userConverter.toDocument(documentRequestDto);
        User updatedUser = usuarioService.addDocument(userId, document);
        return ResponseEntity.ok(userConverter.toResponseDto(updatedUser));
    }

    @Operation(summary = "Remove um documento de um usuário",
            security = @SecurityRequirement(name = "security_auth"))
    @DeleteMapping("/{userId}/documents/{documentNumber}")
    public ResponseEntity<UserResponseDto> removeDocumentFromUser(
            @PathVariable String userId,
            @PathVariable String documentNumber) {
        User updatedUser = usuarioService.removeDocument(userId, documentNumber);
        return ResponseEntity.ok(userConverter.toResponseDto(updatedUser));
    }
}
