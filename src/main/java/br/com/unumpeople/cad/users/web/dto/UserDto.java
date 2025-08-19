package br.com.unumpeople.cad.users.web.dto;

import br.com.unumpeople.cad.users.core.domain.Document;
import br.com.unumpeople.cad.users.core.domain.Address;
import br.com.unumpeople.cad.users.core.domain.UserRoleContext;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Set;

@Schema(name = "UserDto", description = "Representação de um usuário")
public record UserDto(

        @Schema(description = "ID do usuário (ObjectId em formato hexadecimal)", hidden = true)
        String id,

        @Schema(description = "Nome do usuário")
        String name,

        @Schema(description = "Email do usuário")
        String email,

        @Schema(description = "Endereços do usuário")
        Set<Address> addresses,

        @Schema(description = "Documentos do usuário")
        Set<Document> documents,

        @Schema(description = "Tipos de acesso do usuário")
        Set<UserRoleContext> userRoleContextlist,

        @Schema(description = "Data de criação do usuário")
        LocalDateTime creationDate,

        @Schema(description = "Último acesso do usuário")
        LocalDateTime lastAcess,

        @Schema(description = "Status do usuário")
        String status
) {
}