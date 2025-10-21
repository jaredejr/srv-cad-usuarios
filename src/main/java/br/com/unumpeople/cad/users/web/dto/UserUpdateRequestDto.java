package br.com.unumpeople.cad.users.web.dto;

import br.com.unumpeople.cad.users.core.domain.Address;
import br.com.unumpeople.cad.users.core.domain.Document;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Schema(name = "UserUpdateRequestDto", description = "Campo de atualização do usuário")
public record UserUpdateRequestDto(

        @Schema(description = "Nome do usuário")
        String name,

        @Schema(description = "Email do usuário")
        String email,

        @Schema(description = "Tipos de acesso do usuário")
        Map<String, String> userRoleContextList,

        @Schema(description = "Status do usuário")
        String status
) {
}