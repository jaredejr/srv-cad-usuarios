package br.com.portalgni.cad.usuarios.adapter.infra.repository;

import br.com.portalgni.cad.usuarios.adapter.infra.entity.RoleEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RolesRepository extends MongoRepository<RoleEntity, ObjectId> {
    RoleEntity findByNome(String nome);
}
