package br.com.unumpeople.cad.users.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "TipoUsuarioDto", description = "Representação de um tipo de usuário")
public record TipoUsuarioDto(
        @Schema(description = "Noma da Role do usuário")
        String role,

        @Schema(description = "Id do contexto do usuário")
        String contexto,

        @Schema(description = "Lista de operações do usuário")
        List<OperationDto> operations
) {


}
