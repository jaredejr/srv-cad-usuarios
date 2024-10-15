package br.com.portalgni.cad.usuarios.adapter.web.controller;


import br.com.portalgni.cad.usuarios.adapter.web.converter.DtoToUsuarioConverter;
import br.com.portalgni.cad.usuarios.adapter.web.converter.UsuarioToDtoConverter;
import br.com.portalgni.cad.usuarios.adapter.web.dto.TipoUsuarioDto;
import br.com.portalgni.cad.usuarios.adapter.web.dto.UsuarioDto;
import br.com.portalgni.cad.usuarios.core.domain.Usuario;
import br.com.portalgni.cad.usuarios.core.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioToDtoConverter usuarioToDto;
    private final DtoToUsuarioConverter dtoToUsuario;

    @Operation(summary = "Busca todos os usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDto.class, ref = "/components/schemas/UsuarioDto")) })
    })
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> buscarTodasOsUsuarios() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Set<Usuario> usuarios = usuarioService.buscarTodos();
        return ResponseEntity.ok(usuarios.stream().map(usuarioToDto::convert).collect(Collectors.toList()));
    }

    @Operation(summary = "Busca um usuario pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Usuario encontrado",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = { @Content(schema = @Schema(implementation = Object.class)) })
    })
    @GetMapping("{id}")
    public ResponseEntity<UsuarioDto> buscarUsuarioPorId(@PathVariable("id") String id) throws InvalidAttributeValueException {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuarioToDto.convert(usuario));
    }


    @Operation(summary = "Cria um novo Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = { @Content(schema = @Schema(implementation = Object.class)) })
    })
    @PostMapping
    public ResponseEntity<UsuarioDto> criarUsuario(@RequestBody UsuarioDto UsuarioDto) throws InvalidAttributeValueException {
        Usuario usuario = usuarioService.criarUsuario(dtoToUsuario.convert(UsuarioDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioToDto.convert(usuario));
    }

    @Operation(summary = "Atualiza uma Usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = { @Content(schema = @Schema(implementation = Object.class)) })
    })
    @PutMapping("{id}")
    @PreAuthorize("hasRole('SYSTEM_ADMIN') or " +
            "@updateContextValidator.validate(principal.claims['userId'],#id)")
    public ResponseEntity<UsuarioDto> editarUsuario(@PathVariable String id, @RequestBody UsuarioDto usuarioDto) throws InvalidAttributeValueException {
        Usuario usuario = usuarioService.editarUsuario(id, Objects.requireNonNull(dtoToUsuario.convert(usuarioDto)));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioToDto.convert(usuario));
    }

    @Operation(summary = "Exclui um usuario pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable String id) throws InvalidAttributeValueException {
        usuarioService.excluirUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Busca um usuario pelo nome")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Usuarios encontrados",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = { @Content(schema = @Schema(implementation = Object.class)) })
    })
    @PostMapping("buscar-por-nome")
    public ResponseEntity<Set<UsuarioDto>> buscarUsuarioPorNome(@RequestBody String nome) throws InvalidAttributeValueException {
        return ResponseEntity.ok(usuarioService
                .buscarUsuarioPorNome(nome)
                .stream()
                .map(usuarioToDto::convert)
                .collect(Collectors.toSet()));
    }

    @Operation(summary = "Busca um usuario pelo tipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Usuarios encontrados",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Usuários não encontrados",
                    content = { @Content(schema = @Schema(implementation = Object.class)) })
    })
    @PostMapping("buscar-por-tipo")
    public ResponseEntity<Set<UsuarioDto>> buscarUsuarioPorTipo(@RequestBody TipoUsuarioDto tipoUsuarioDto) throws InvalidAttributeValueException {
        return ResponseEntity.ok(usuarioService
                .buscarUsuarioPorTipo(tipoUsuarioDto.getRole(), tipoUsuarioDto.getContexto())
                .stream()
                .map(usuarioToDto::convert)
                .collect(Collectors.toSet()));
    }
}
