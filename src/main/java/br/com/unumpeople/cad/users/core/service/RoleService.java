package br.com.portalgni.cad.usuarios.core.service;

import br.com.portalgni.cad.usuarios.core.domain.Role;
import br.com.portalgni.cad.usuarios.core.exception.DomainValidationException;
import br.com.portalgni.cad.usuarios.core.ports.RoleRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.RoleServicePort;
import br.com.portalgni.cad.usuarios.core.validation.role.RoleNameValidator;
import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public class RoleService implements RoleServicePort {

    RoleRepositoryPort roleRepository;
    RoleNameValidator roleNameValidator;

    @Override
    public Role addRole(Role role) {
        roleNameValidator.validate(role);
        return roleRepository.saveRole(role);
    }

    @Override
    public Role editRole(String id, Role role) {
        Role roleBeingUpdated = new Role(id, role.getName(), role.getDescription(), role.getOperations());
        roleNameValidator.validate(roleBeingUpdated);
        return roleRepository.saveRole(roleBeingUpdated);
    }

    @Override
    public void deleteRole(String id) {
        Role role =  getRoleById(id);
        roleRepository.deleteRole(role);
    }

    @Override
    public Set<Role> getAllRoles() {
        return roleRepository.buscarTodasAsRoles();
    }

    @Override
    public Role getRoleById(String id) {
        return roleRepository.buscarPorId(id).orElseThrow(() -> new DomainValidationException("Role não encontrada."));
    }

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.getRoleByName(name).orElseThrow(() -> new DomainValidationException("Role não encontrada."));
    }

}
