package br.com.unumpeople.cad.users.web.dto;

import br.com.unumpeople.cad.users.core.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Objeto para criação de uma Role")
public record RoleRequestDto(
        @Schema(description = "Nome da Role")
        String name,
        @Schema(description = "Descrição da Role")
        String description,
        @Schema(description = "Lista de operações da Role")
        List<String> operations
) {

}
