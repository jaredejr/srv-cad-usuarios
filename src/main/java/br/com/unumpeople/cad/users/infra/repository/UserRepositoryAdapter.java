package br.com.unumpeople.cad.users.infra.repository;

import br.com.unumpeople.cad.users.core.domain.User;
import br.com.unumpeople.cad.users.infra.converter.EntityToUsuarioConverter;
import br.com.unumpeople.cad.users.infra.converter.TipoUsuarioToEntityConverter;
import br.com.unumpeople.cad.users.infra.converter.UsuarioToEntityConverter;
import br.com.unumpeople.cad.users.core.domain.UserRoleContext;
import br.com.unumpeople.cad.users.core.ports.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class UserRepositoryAdapter implements UserRepositoryPort {

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
