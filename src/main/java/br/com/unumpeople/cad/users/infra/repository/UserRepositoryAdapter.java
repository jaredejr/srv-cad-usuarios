package br.com.unumpeople.cad.users.infra.repository;

import br.com.unumpeople.cad.users.core.domain.User;
import br.com.unumpeople.cad.users.infra.converter.EntityToUserConverter;
import br.com.unumpeople.cad.users.infra.converter.UserRoleContextToEntityConverter;
import br.com.unumpeople.cad.users.infra.converter.UserToEntityConverter;
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

    UserRepository userRepository;
    EntityToUserConverter entityToUsuario;
    UserToEntityConverter usuarioToEntity;
    UserRoleContextToEntityConverter tipoUsuarioToEntity;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(entityToUsuario::convert);
    }

    @Override
    public User saveUser(User user) {
        return entityToUsuario.convert(
                userRepository.save(
                        Objects.requireNonNull(usuarioToEntity.convert(user))));
    }

    @Override
    public Set<User> getUserByName(String nome) {
        return userRepository.
                findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(entityToUsuario::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<User> getUserById(String id) {
        return userRepository.findById(new ObjectId(id)).map(entityToUsuario::convert);
    }

    @Override
    public Set<User> findByUserRoleContext(UserRoleContext userRoleContext) {
        return userRepository
                .findByUserRoleContext(tipoUsuarioToEntity.convert(userRoleContext))
                .stream()
                .map(entityToUsuario::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(Objects.requireNonNull(usuarioToEntity.convert(user)));
    }

    @Override
    public Set<User> findAll() {
        return userRepository.findAll()
                .stream()
                .map(entityToUsuario::convert)
                .collect(Collectors.toSet());
    }
}
