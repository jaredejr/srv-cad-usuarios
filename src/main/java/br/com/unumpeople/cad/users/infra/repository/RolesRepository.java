package br.com.unumpeople.cad.users.infra.repository;

import br.com.unumpeople.cad.users.infra.entity.RoleEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends MongoRepository<RoleEntity, ObjectId> {
    Optional<RoleEntity> findByName(String name);
}
