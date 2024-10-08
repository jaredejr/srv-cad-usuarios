package br.com.portalgni.cad.usuarios.core.domain;

import lombok.*;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoUsuario {

    private Role role;
    private String contexto;
}
