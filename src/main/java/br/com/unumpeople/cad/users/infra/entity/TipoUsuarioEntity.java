package br.com.unumpeople.cad.users.infra.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
public class TipoUsuarioEntity {

    private ObjectId role;
    private ObjectId contexto;
}
