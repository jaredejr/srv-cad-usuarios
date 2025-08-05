package br.com.portalgni.cad.usuarios.core.ports;

import br.com.portalgni.cad.usuarios.core.domain.Role;

import java.util.Optional;
import java.util.Set;

public interface RoleRepositoryPort {

    Role saveRole(Role role);

    void deleteRole(Role role);

    Optional<Role> getRoleByName(String name);

    Set<Role> buscarTodasAsRoles();

    Optional<Role> buscarPorId(String id);
}
