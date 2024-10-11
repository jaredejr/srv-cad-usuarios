package br.com.portalgni.cad.usuarios.adapter.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "TipoUsuarioDto", description = "Representação de um tipo de usuário")
public class TipoUsuarioDto {

    @Schema(description = "Noma da Role do usuário")
    private String role;

    @Schema(description = "Id do contexto do usuário")
    private String contexto;
}
