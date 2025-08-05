package br.com.portalgni.cad.usuarios.core.ports;

import br.com.portalgni.cad.usuarios.core.domain.User;

import java.util.Set;

public interface UserServicePort {
    User getUserByEmail(String email);

    User createUsuario(User user);

    User editarUsuario(String id, User user);

    Set<User> getUserByName(String name);

    User getUserById(String id);

    Set<User> getUserByRoleContext(String roleName, String contexto);

    Set<User> getAllUsers();

    void deleteUser(String id);

    void updateLastAccess(User user);
}
