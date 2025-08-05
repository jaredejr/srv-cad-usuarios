package br.com.portalgni.cad.usuarios.core.ports;

import br.com.portalgni.cad.usuarios.core.domain.Role;

import java.util.Set;

public interface RoleServicePort {

    Role addRole(Role role);

    Role editRole(String id, Role role);

    void deleteRole(String id);

    Set<Role> getAllRoles();

    Role getRoleById(String id);

    Role getRoleByName(String name);
}
