package br.com.unumpeople.cad.users.infra.repository;

import br.com.unumpeople.cad.users.infra.entity.TipoUsuarioEntity;
import br.com.unumpeople.cad.users.infra.entity.UsuarioEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UsuarioRepository extends MongoRepository<UsuarioEntity, ObjectId> {
    Optional<UsuarioEntity> findByEmail(String email);

    @Query("{ 'name': { $regex: ?0, $options: 'i' } }")
    Set<UsuarioEntity> findByNomeContainingIgnoreCase(String nome);

    Set<UsuarioEntity> findByTipoUsuarioContaining(TipoUsuarioEntity tipoUsuarioEntity);

}
