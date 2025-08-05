package br.com.unumpeople.cad.users.core.ports;

import br.com.unumpeople.cad.users.core.domain.Role;

import java.util.Set;

public interface RoleServicePort {

    Role addRole(Role role);

    Role editRole(String id, Role role);

    void deleteRole(String id);

    Set<Role> getAllRoles();

    Role getRoleById(String id);

    Role getRoleByName(String name);
}
