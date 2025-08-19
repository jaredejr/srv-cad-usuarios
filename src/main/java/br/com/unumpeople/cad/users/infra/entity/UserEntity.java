package br.com.unumpeople.cad.users.infra.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class UserEntity {

    @Id
    private ObjectId id;
    private String name;
    private String email;
    private String password;
    private Set<AddressEntity> addressList;
    private Set<DocumentEntity> documents;
    private Set<UserRoleContextEntity> userRoleContextList;
    private LocalDateTime creationDate;
    private LocalDateTime lastAccess;
    private String status;

}
