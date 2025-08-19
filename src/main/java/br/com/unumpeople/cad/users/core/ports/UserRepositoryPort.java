package br.com.unumpeople.cad.users.core.ports;

import br.com.unumpeople.cad.users.core.domain.UserRoleContext;
import br.com.unumpeople.cad.users.core.domain.User;

import java.util.Optional;
import java.util.Set;

public interface UserRepositoryPort {
    Optional<User> findByEmail(String email);

    User saveUser(User user);

    Set<User> getUserByName(String nome);

    Optional<User> getUserById(String id);

    Set<User> findByUserRoleContext(UserRoleContext userRoleContext);

    void deleteUser(User user);

    Set<User> findAll();
}
