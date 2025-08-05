package br.com.portalgni.cad.usuarios.core.service;

import br.com.portalgni.cad.usuarios.core.domain.UserRoleContext;
import br.com.portalgni.cad.usuarios.core.domain.User;
import br.com.portalgni.cad.usuarios.core.exception.DomainValidationException;
import br.com.portalgni.cad.usuarios.core.ports.RoleRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.UserRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.UserServicePort;
import br.com.portalgni.cad.usuarios.core.validation.usuario.UserValidator;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
public class UserService implements UserServicePort {

    private UserRepositoryPort usuarioRepository;
    private RoleRepositoryPort roleRepository;
    private UserValidator userValidator;

    private static final String USER_NOT_FOUND_MESSAGE = "Nenhum usuário encontrado";

    @Override
    public User getUserByEmail(String email){
        return usuarioRepository.findByEmail(email)
                .orElseThrow(()-> new DomainValidationException(USER_NOT_FOUND_MESSAGE));
    }

    @Override
    public User createUsuario(User user) {
        userValidator.validate(user);
        return usuarioRepository.salvarUsuario(user);
    }

    @Override
    public User editarUsuario(String id, User user){
        User editedUser = new User(id,
                user.getName(),
                user.getEmail().getValue(),
                user.getPassword().getValue(),
                user.getAddressList(),
                user.getDocuments(),
                user.getUserRoleContextList(),
                user.getCreationDate(),
                user.getLastAccess(),
                user.getStatus().getValue());
        return usuarioRepository.salvarUsuario(editedUser);
    }

    @Override
    public Set<User> getUserByName(String name) {
        return Optional.ofNullable(usuarioRepository.buscarUsuarioPorNome(name))
                .filter(set -> Boolean.FALSE.equals(set.isEmpty()))
                .orElseThrow(()-> new DomainValidationException(USER_NOT_FOUND_MESSAGE));
    }

    @Override
    public User getUserById(String id) {
        return usuarioRepository.buscarUsuarioPorId(id).orElseThrow(() -> new DomainValidationException("Usuário não encontrado!"));
    }

    @Override
    public Set<User> getUserByRoleContext(String roleName, String context) {
        return Optional.ofNullable(usuarioRepository.buscarUsuarioPorTipo(new UserRoleContext(
                        roleRepository.getRoleByName(roleName)
                                .orElseThrow(()-> new DomainValidationException(USER_NOT_FOUND_MESSAGE)),
                        context)))
                .filter(set -> Boolean.FALSE.equals(set.isEmpty()))
                .orElseThrow(()-> new DomainValidationException(USER_NOT_FOUND_MESSAGE));
    }

    @Override
    public Set<User> getAllUsers() {
        return usuarioRepository.buscarTodos();
    }

    @Override
    public void deleteUser(String id) {
        usuarioRepository.excluirUsuario(getUserById(id));
    }

    @Override
    public void updateLastAccess(User user) {
        user.updateLastAccess();
        usuarioRepository.salvarUsuario(user);
    }
}
