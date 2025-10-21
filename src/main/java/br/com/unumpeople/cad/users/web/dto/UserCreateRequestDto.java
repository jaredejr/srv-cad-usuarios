package br.com.unumpeople.cad.users.web.dto;

import br.com.unumpeople.cad.users.core.domain.*;
import br.com.unumpeople.cad.users.web.converter.UserRoleContextConverter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Schema(name = "UserCreateRequestDto", description = "Objeto para criação de um novo usuário")
public record UserCreateRequestDto(

        @Schema(description = "Nome do usuário")
        String name,

        @Schema(description = "Email do usuário")
        String email,

        @Schema(description = "Endereços do usuário")
        Set<Address> addresses,

        @Schema(description = "Documentos do usuário")
        Set<Document> documents,

        @Schema(description = "Tipos de acesso do usuário")
        List<UserRoleContextDto> userRoleContextList,

        @Schema(description = "Status do usuário")
        String status
) {
}