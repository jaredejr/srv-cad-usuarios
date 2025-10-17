package br.com.unumpeople.cad.users.core.service;

import br.com.unumpeople.cad.users.core.domain.UserRoleContext;
import br.com.unumpeople.cad.users.core.domain.User;
import br.com.unumpeople.cad.users.core.exception.DomainValidationException;
import br.com.unumpeople.cad.users.core.ports.RoleRepositoryPort;
import br.com.unumpeople.cad.users.core.ports.UserRepositoryPort;
import br.com.unumpeople.cad.users.core.ports.UserServicePort;
import br.com.unumpeople.cad.users.core.validation.user.UserValidator;
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
        return usuarioRepository.saveUser(user);
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
        return usuarioRepository.saveUser(editedUser);
    }

    @Override
    public Set<User> getUserByName(String name) {
        return Optional.ofNullable(usuarioRepository.getUserByName(name))
                .filter(set -> Boolean.FALSE.equals(set.isEmpty()))
                .orElseThrow(()-> new DomainValidationException(USER_NOT_FOUND_MESSAGE));
    }

    @Override
    public User getUserById(String id) {
        return usuarioRepository.getUserById(id).orElseThrow(() -> new DomainValidationException("Usuário não encontrado!"));
    }

    @Override
    public Set<User> getUserByRoleContext(String roleName, String context) {
        return Optional.ofNullable(usuarioRepository.findByUserRoleContext(new UserRoleContext(
                        roleRepository.getRoleByName(roleName)
                                .orElseThrow(()-> new DomainValidationException(USER_NOT_FOUND_MESSAGE)),
                        context)))
                .filter(set -> Boolean.FALSE.equals(set.isEmpty()))
                .orElseThrow(()-> new DomainValidationException(USER_NOT_FOUND_MESSAGE));
    }

    @Override
    public Set<User> getAllUsers() {
        return usuarioRepository.findAll();
    }

    @Override
    public void deleteUser(String id) {
        usuarioRepository.deleteUser(getUserById(id));
    }

    @Override
    public void updateLastAccess(User user) {
        user.updateLastAccess();
        usuarioRepository.saveUser(user);
    }

    @Override
    public User getUserByEmailAndUpdateLastAccess(String email) {
        User user = getUserByEmail(email);
        updateLastAccess(user);
        return user;
    }
}
