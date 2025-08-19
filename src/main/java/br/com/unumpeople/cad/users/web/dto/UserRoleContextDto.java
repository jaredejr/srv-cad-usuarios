package br.com.unumpeople.cad.users.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "UserRoleContextDto", description = "Representação de um tipo de usuário")
public record UserRoleContextDto(
        @Schema(description = "Nome da Role do usuário")
        String role,

        @Schema(description = "Id do contexto do usuário")
        String contexto
) {


}
