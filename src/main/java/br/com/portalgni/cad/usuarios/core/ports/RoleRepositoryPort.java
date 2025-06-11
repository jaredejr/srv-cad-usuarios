package br.com.portalgni.cad.usuarios.core.ports;

import br.com.portalgni.cad.usuarios.core.domain.Role;

import java.util.Optional;
import java.util.Set;

public interface RoleRepositoryPort {

    Role salvarRole(Role role);

    void excluirRole(Role role);

    Optional<Role> buscarRolePorNome(String nome);

    Set<Role> buscarTodasAsRoles();

    Optional<Role> buscarPorId(String id);
}
