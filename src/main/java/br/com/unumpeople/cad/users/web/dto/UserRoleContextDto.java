package br.com.unumpeople.cad.users.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UserRoleContextDto", description = "Representação de um RoleContext")
public record UserRoleContextDto(

        @Schema(description = "Role do usuário")
        String role,

        @Schema(description = "Contexto onde a Role se aplica")
        String context)
{
}
