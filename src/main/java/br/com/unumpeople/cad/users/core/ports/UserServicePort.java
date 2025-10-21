package br.com.unumpeople.cad.users.core.ports;

import br.com.unumpeople.cad.users.core.domain.Address;
import br.com.unumpeople.cad.users.core.domain.Document;
import br.com.unumpeople.cad.users.core.domain.User;

import java.util.Set;

public interface UserServicePort {
    User getUserByEmail(String email);

    User createUser(User user);

    User updateUser(String id, User.UserFieldsForUpdate userFields);

    Set<User> getUserByName(String name);

    User getUserById(String id);

    Set<User> getUserByRoleContext(String roleName, String contexto);

    Set<User> getAllUsers();

    void deleteUser(String id);

    void updateLastAccess(User user);

    User getUserByEmailAndUpdateLastAccess(String email);

    User addAddress(String userId, Address address);

    User removeAddress(String userId, String addressId);

    User addDocument(String userId, Document document);

    User removeDocument(String userId, String documentNumber);
}
