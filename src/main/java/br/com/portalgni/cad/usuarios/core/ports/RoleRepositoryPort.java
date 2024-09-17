package br.com.portalgni.cad.usuarios.core.ports;

import br.com.portalgni.cad.usuarios.core.domain.Role;

import java.util.Set;

public interface RoleRepositoryPort {

    Role salvarRole(Role role);

    void excluirRole(String id);

    Set<Role> buscarTodasAsRoles();
}
