package br.com.portalgni.cad.usuarios.web.dto;

import br.com.portalgni.cad.usuarios.core.domain.Document;
import br.com.portalgni.cad.usuarios.core.domain.Address;
import br.com.portalgni.cad.usuarios.core.domain.UserRoleContext;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Set;

@Schema(name = "UsuarioDto", description = "Representação de um usuário")
public record UsuarioDto(

        @Schema(description = "ID do usuário (ObjectId em formato hexadecimal)", hidden = true)
        String id,

        @Schema(description = "Nome do usuário")
        String nome,

        @Schema(description = "Email do usuário")
        String email,

        @Schema(description = "Endereços do usuário")
        Set<Address> addresses,

        @Schema(description = "Documentos do usuário")
        Set<Document> documents,

        @Schema(description = "Tipos de acesso do usuário")
        Set<UserRoleContext> listaUserRoleContext,

        @Schema(description = "Data de criação do usuário")
        LocalDateTime dataCriacao,

        @Schema(description = "Último acesso do usuário")
        LocalDateTime ultimoAcesso,

        @Schema(description = "Status do usuário")
        String status
) {
}