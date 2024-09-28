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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.management.InvalidAttributeValueException;

@AllArgsConstructor
@RestController
public class RoleController {

    private RoleServicePort roleService;
    private RoleToDtoConverter roleToDto;
    private DtoToRoleConverter dtoToRole;

    @GetMapping("/testRole")
    public String getMessage() {
        return "HELLO from API controller";
    }

    @Operation(summary = "Cria uma nova Role")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Role criada com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RoleDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    @PostMapping
    public ResponseEntity<RoleDto> criarRole(@RequestBody RoleDto roleDto, BindingResult result) throws InvalidAttributeValueException {
        Role role = roleService.adicionarRole(dtoToRole.convert(roleDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(roleToDto.convert(role));
    }
}
