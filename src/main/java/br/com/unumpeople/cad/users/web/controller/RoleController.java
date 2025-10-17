package br.com.unumpeople.cad.users.web.controller;

import br.com.unumpeople.cad.users.web.converter.DtoToNewRoleConverter;
import br.com.unumpeople.cad.users.web.converter.DtoToRoleConverter;
import br.com.unumpeople.cad.users.web.converter.RoleToDtoConverter;
import br.com.unumpeople.cad.users.web.dto.RoleDto;

import br.com.unumpeople.cad.users.core.domain.Role;
import br.com.unumpeople.cad.users.core.ports.RoleServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
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

    private RoleServicePort roleService;
    private RoleToDtoConverter roleToDto;
    private DtoToRoleConverter dtoToRole;
    private DtoToNewRoleConverter dtoToNewRole;

    @Operation(summary = "Busca todas as roles",
            security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles encontradas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoleDto.class)) })
    })
    @PreAuthorize("hasAuthority('ROLE_READ_ALL_ROLE')")
    @GetMapping
    public ResponseEntity<List<RoleDto>> buscarTodasAsRoles() {
        Set<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles.stream().map(roleToDto::convert).collect(Collectors.toList()));
    }

    @Operation(summary = "Busca uma role pelo ID",
            security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Role encontrada",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RoleDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Role não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> buscarRolePorId(@PathVariable("id") String id) {
        Role role = roleService.getRoleById(id);
        return ResponseEntity.ok(roleToDto.convert(role));
    }


    @Operation(summary = "Cria uma nova Role",
            security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Role criada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoleDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping
    public ResponseEntity<RoleDto> criarRole(@RequestBody RoleDto roleDto) {
        Role role = roleService.addRole(dtoToNewRole.convert(roleDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(roleToDto.convert(role));
    }

    @Operation(summary = "Atualiza uma Role",
            security = @SecurityRequirement(name = "security_auth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role atualizada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoleDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> editarRole(@PathVariable String id, @RequestBody RoleDto roleDto) {
        Role role = roleService.editRole(id, dtoToRole.convert(roleDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(roleToDto.convert(role));
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
