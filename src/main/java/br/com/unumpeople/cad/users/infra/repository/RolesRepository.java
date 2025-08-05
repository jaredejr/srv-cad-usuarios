package br.com.portalgni.cad.usuarios.infra.repository;

import br.com.portalgni.cad.usuarios.infra.entity.RoleEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends MongoRepository<RoleEntity, ObjectId> {
    Optional<RoleEntity> findByNome(String nome);
}
