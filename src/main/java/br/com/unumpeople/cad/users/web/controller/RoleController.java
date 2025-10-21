package br.com.unumpeople.cad.users.web.controller;

import br.com.unumpeople.cad.users.web.converter.RoleConverter;
import br.com.unumpeople.cad.users.web.dto.RoleRequestDto;

import br.com.unumpeople.cad.users.core.domain.Role;
import br.com.unumpeople.cad.users.core.ports.RoleServicePort;
import br.com.unumpeople.cad.users.web.dto.RoleResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("role")
public class RoleController {

    private final RoleServicePort roleService;
    private final RoleConverter roleConverter;

    @Operation(summary = "Busca todas as roles",
            security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles encontradas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoleResponseDto.class)) })
    })
    @PreAuthorize("hasAuthority('ROLE_READ_ALL_ROLE')")
    @GetMapping
    public ResponseEntity<List<RoleResponseDto>> buscarTodasAsRoles() {
        Set<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles.stream().map(roleConverter::toResponseDto).collect(Collectors.toList()));
    }

    @Operation(summary = "Busca uma role pelo ID",
            security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Role encontrada",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RoleResponseDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Role não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDto> buscarRolePorId(@PathVariable("id") String id) {
        Role role = roleService.getRoleById(id);
        return ResponseEntity.ok(roleConverter.toResponseDto(role));
    }


    @Operation(summary = "Cria uma nova Role",
            security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Role criada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoleResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping
    public ResponseEntity<RoleResponseDto> criarRole(@RequestBody RoleRequestDto roleRequestDto) {
        Role role = roleService.addRole(roleConverter.toDomain(roleRequestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(roleConverter.toResponseDto(role));
    }

    @Operation(summary = "Atualiza uma Role",
            security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role atualizada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoleResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PutMapping("/{id}")
    public ResponseEntity<RoleResponseDto> editarRole(@PathVariable String id, @RequestBody RoleRequestDto roleRequestDto) {
        Role role = roleService.editRole(id, roleConverter.toDomain(roleRequestDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(roleConverter.toResponseDto(role));
    }

    @Operation(summary = "Exclui uma role pelo ID",
            security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Role excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Role não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirRole(@PathVariable String id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
