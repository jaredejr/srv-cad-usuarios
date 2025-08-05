package br.com.unumpeople.cad.users.core.ports;

import br.com.unumpeople.cad.users.core.domain.UserRoleContext;
import br.com.unumpeople.cad.users.core.domain.User;

import java.util.Optional;
import java.util.Set;

public interface UserRepositoryPort {
    Optional<User> findByEmail(String email);

    User salvarUsuario(User user);

    Set<User> buscarUsuarioPorNome(String nome);

    Optional<User> buscarUsuarioPorId(String id);

    Set<User> buscarUsuarioPorTipo(UserRoleContext userRoleContext);

    void excluirUsuario(User user);

    Set<User> buscarTodos();
}
