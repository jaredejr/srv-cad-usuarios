package br.com.portalgni.cad.usuarios.adapter.infra.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "roles")
public class RoleEntity {

    @Id
    private ObjectId id;
    private String nome;
    private String descricao;

}
