package br.com.portalgni.cad.usuarios.adapter.infra.repository;

import br.com.portalgni.cad.usuarios.adapter.infra.entity.TipoUsuarioEntity;
import br.com.portalgni.cad.usuarios.adapter.infra.entity.UsuarioEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UsuarioRepository extends MongoRepository<UsuarioEntity, ObjectId> {
    UsuarioEntity findByEmail(String email);
    Set<UsuarioEntity> findAllByNome(String nome);
    Set<UsuarioEntity> findByTipoUsuarioContaining(TipoUsuarioEntity tipoUsuarioEntity);

}
