package br.com.unumpeople.cad.users.web.dto;

import br.com.unumpeople.cad.users.core.domain.Operation;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Representação de uma role")
public record RoleResponseDto(
        @Schema(description = "ID da Role (ObjectId em formato hexadecimal)", hidden = true)
        String id,
        @Schema(description = "Nome da Role")
        String name,
        @Schema(description = "Descrição da Role")
        String description,
        @Schema(description = "Lista de operações da Role")
        List<Operation> operations
) {
}
