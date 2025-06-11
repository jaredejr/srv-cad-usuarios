package br.com.portalgni.cad.usuarios.adapter.infra.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
@AllArgsConstructor
public class TipoUsuarioEntity {

    private ObjectId role;
    private ObjectId contexto;
}
