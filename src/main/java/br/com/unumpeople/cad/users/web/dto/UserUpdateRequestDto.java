package br.com.unumpeople.cad.users.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "UserUpdateRequestDto", description = "Campo de atualização do usuário")
public record UserUpdateRequestDto(

        @Schema(description = "Nome do usuário")
        String name,

        @Schema(description = "Email do usuário")
        String email,

        @Schema(description = "Tipos de acesso do usuário")
        List<UserRoleContextDto> userRoleContextList,

        @Schema(description = "Status do usuário")
        String status
) {
}