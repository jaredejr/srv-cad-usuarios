package br.com.portalgni.cad.usuarios.adapter.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "RoleDto", description = "Representação de uma role")
public class RoleDto {

    @Schema(description = "ID da Role (ObjectId em formato hexadecimal)", hidden = true)
    private String id;

    @Schema(description = "Nome da Role")
    private String nome;

    @Schema(description = "Descrição da Role")
    private String descricao;

}
