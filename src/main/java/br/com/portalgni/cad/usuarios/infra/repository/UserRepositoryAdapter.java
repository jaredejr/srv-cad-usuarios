package br.com.portalgni.cad.usuarios.infra.repository;

import br.com.portalgni.cad.usuarios.core.domain.User;
import br.com.portalgni.cad.usuarios.infra.converter.EntityToUsuarioConverter;
import br.com.portalgni.cad.usuarios.infra.converter.TipoUsuarioToEntityConverter;
import br.com.portalgni.cad.usuarios.infra.converter.UsuarioToEntityConverter;
import br.com.portalgni.cad.usuarios.core.domain.UserRoleContext;
import br.com.portalgni.cad.usuarios.core.ports.UsuarioRepositoryPort;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    UsuarioRepository usuarioRepository;
    EntityToUsuarioConverter entityToUsuario;
    UsuarioToEntityConverter usuarioToEntity;
    TipoUsuarioToEntityConverter tipoUsuarioToEntity;

    @Override
    public Optional<User> findByEmail(String email) {
        return usuarioRepository.findByEmail(email).map(entityToUsuario::convert);
    }

    @Override
    public User salvarUsuario(User user) {
        return entityToUsuario.convert(
                usuarioRepository.save(
                        Objects.requireNonNull(usuarioToEntity.convert(user))));
    }

    @Override
    public Set<User> buscarUsuarioPorNome(String nome) {
        return usuarioRepository.
                findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(entityToUsuario::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<User> buscarUsuarioPorId(String id) {
        return usuarioRepository.findById(new ObjectId(id)).map(entityToUsuario::convert);
    }

    @Override
    public Set<User> buscarUsuarioPorTipo(UserRoleContext userRoleContext) {
        return usuarioRepository
                .findByTipoUsuarioContaining(tipoUsuarioToEntity.convert(userRoleContext))
                .stream()
                .map(entityToUsuario::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public void excluirUsuario(User user) {
        usuarioRepository.delete(Objects.requireNonNull(usuarioToEntity.convert(user)));
    }

    @Override
    public Set<User> buscarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(entityToUsuario::convert)
                .collect(Collectors.toSet());
    }
}
