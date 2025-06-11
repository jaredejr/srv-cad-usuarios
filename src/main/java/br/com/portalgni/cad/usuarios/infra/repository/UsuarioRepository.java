package br.com.portalgni.cad.usuarios.infra.repository;

import br.com.portalgni.cad.usuarios.infra.entity.TipoUsuarioEntity;
import br.com.portalgni.cad.usuarios.infra.entity.UsuarioEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UsuarioRepository extends MongoRepository<UsuarioEntity, ObjectId> {
    Optional<UsuarioEntity> findByEmail(String email);

    @Query("{ 'nome': { $regex: ?0, $options: 'i' } }")
    Set<UsuarioEntity> findByNomeContainingIgnoreCase(String nome);

    Set<UsuarioEntity> findByTipoUsuarioContaining(TipoUsuarioEntity tipoUsuarioEntity);

}
