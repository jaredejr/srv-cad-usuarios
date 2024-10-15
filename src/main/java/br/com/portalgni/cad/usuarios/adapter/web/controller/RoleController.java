package br.com.portalgni.cad.usuarios.adapter.web.controller;

import br.com.portalgni.cad.usuarios.adapter.web.converter.DtoToRoleConverter;
import br.com.portalgni.cad.usuarios.adapter.web.converter.RoleToDtoConverter;
import br.com.portalgni.cad.usuarios.adapter.web.dto.RoleDto;

import br.com.portalgni.cad.usuarios.core.domain.Role;
import br.com.portalgni.cad.usuarios.core.ports.RoleServicePort;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.management.InvalidAttributeValueException;
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

    @Operation(summary = "Busca todas as roles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Roles encontradas",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoleDto.class)) })
    })
    @GetMapping
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<List<RoleDto>> buscarTodasAsRoles() {
        Set<Role> roles = roleService.buscarTodasAsRoles();
        return ResponseEntity.ok(roles.stream().map(roleToDto::convert).collect(Collectors.toList()));
    }

    @Operation(summary = "Busca uma role pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Role encontrada",
                    content = {@Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RoleDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Role não encontrada")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    public ResponseEntity<RoleDto> buscarRolePorId(@PathVariable("id") String id) throws InvalidAttributeValueException {
        Role role = roleService.bucarPorId(id);
        return ResponseEntity.ok(roleToDto.convert(role));
    }


    @Operation(summary = "Cria uma nova Role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Role criada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoleDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping
    public ResponseEntity<RoleDto> criarRole(@RequestBody RoleDto roleDto) throws InvalidAttributeValueException {
        Role role = roleService.adicionarRole(dtoToRole.convert(roleDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(roleToDto.convert(role));
    }

    @Operation(summary = "Atualiza uma Role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role atualizada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoleDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> editarRole(@PathVariable String id, @RequestBody RoleDto roleDto) throws InvalidAttributeValueException {
        Role role = roleService.editarRole(id, dtoToRole.convert(roleDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(roleToDto.convert(role));
    }

    @Operation(summary = "Exclui uma role pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Role excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Role não encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirRole(@PathVariable String id) throws InvalidAttributeValueException {
        roleService.excluirRole(id);
        return ResponseEntity.noContent().build();
    }
}
