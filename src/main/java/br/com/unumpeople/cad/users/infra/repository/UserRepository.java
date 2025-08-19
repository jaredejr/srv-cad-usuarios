package br.com.unumpeople.cad.users.infra.repository;

import br.com.unumpeople.cad.users.infra.entity.UserRoleContextEntity;
import br.com.unumpeople.cad.users.infra.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, ObjectId> {
    Optional<UserEntity> findByEmail(String email);

    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    Set<UserEntity> findByNomeContainingIgnoreCase(String nome);

    @Query("{ 'userRoleContextList': { $elemMatch: ?0 } }")
    Set<UserEntity> findByUserRoleContext(UserRoleContextEntity userRoleContextEntity);

}
