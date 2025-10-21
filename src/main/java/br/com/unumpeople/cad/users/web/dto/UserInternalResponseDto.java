package br.com.unumpeople.cad.users.web.dto;

import br.com.unumpeople.cad.users.core.domain.Address;
import br.com.unumpeople.cad.users.core.domain.Document;
import br.com.unumpeople.cad.users.core.domain.UserRoleContext;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Set;

@Schema(name = "UserInternalResponseDto", description = "Representação de um usuário para o login")
public record UserInternalResponseDto(

        String id,

        String name,

        String email,

        String password,

        Set<UserRoleContext> userRoleContextList,

        LocalDateTime creationDate,

        LocalDateTime lastAccess,

        String status
) {
}