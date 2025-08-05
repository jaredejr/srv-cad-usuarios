package br.com.portalgni.cad.usuarios.core.ports;

import br.com.portalgni.cad.usuarios.core.domain.UserRoleContext;
import br.com.portalgni.cad.usuarios.core.domain.User;

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
