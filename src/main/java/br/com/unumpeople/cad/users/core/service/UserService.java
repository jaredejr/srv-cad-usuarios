package br.com.unumpeople.cad.users.core.service;

import br.com.unumpeople.cad.users.core.domain.Address;
import br.com.unumpeople.cad.users.core.domain.Document;
import br.com.unumpeople.cad.users.core.domain.UserRoleContext;
import br.com.unumpeople.cad.users.core.domain.User;
import br.com.unumpeople.cad.users.core.domain.User.UserFieldsForUpdate;
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
    public User createUser(User user) {
        userValidator.validate(user);
        return usuarioRepository.saveUser(user);
    }

    @Override
    public User updateUser(String id, UserFieldsForUpdate userFields){
        User user = getUserById(id);
        user.updateUser(userFields);
        return usuarioRepository.saveUser(user);
    }

    @Override
    public Set<User> getUserByName(String name) {
        return Optional.ofNullable(usuarioRepository.getUserByName(name))
                .filter(set -> Boolean.FALSE.equals(set.isEmpty()))
                .orElseThrow(()-> new DomainValidationException(USER_NOT_FOUND_MESSAGE));
    }

    @Override
    public User getUserById(String id) {
        return usuarioRepository.getUserById(id).orElseThrow(() -> new DomainValidationException(USER_NOT_FOUND_MESSAGE));
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

    @Override
    public User addAddress(String userId, Address address) {
        User user = getUserById(userId);
        user.addNewAddress(address);
        return usuarioRepository.saveUser(user);
    }

    @Override
    public User removeAddress(String userId, String addressId) {
        User user = getUserById(userId);
        user.removeAddress(addressId);
        return usuarioRepository.saveUser(user);
    }

    @Override
    public User addDocument(String userId, Document document) {
        User user = getUserById(userId);
        user.addNewDocument(document);
        return usuarioRepository.saveUser(user);
    }

    @Override
    public User removeDocument(String userId, String documentNumber) {
        User user = getUserById(userId);
        user.removeDocument(documentNumber);
        return usuarioRepository.saveUser(user);
    }
}
