package br.com.unumpeople.cad.users.infra.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "roles")
public record RoleEntity(
        @Id ObjectId id,
        String name,
        String description,
        List<OperationEntity> operations
) {
}
