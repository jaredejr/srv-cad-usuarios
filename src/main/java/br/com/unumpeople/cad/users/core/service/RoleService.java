package br.com.unumpeople.cad.users.core.service;

import br.com.unumpeople.cad.users.core.domain.Role;
import br.com.unumpeople.cad.users.core.exception.DomainValidationException;
import br.com.unumpeople.cad.users.core.ports.RoleRepositoryPort;
import br.com.unumpeople.cad.users.core.ports.RoleServicePort;
import br.com.unumpeople.cad.users.core.validation.role.RoleExistentValidator;
import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public class RoleService implements RoleServicePort {

    RoleRepositoryPort roleRepository;
    RoleExistentValidator roleExistentValidator;

    @Override
    public Role addRole(Role role) {
        roleExistentValidator.validate(role);
        return roleRepository.saveRole(role);
    }

    @Override
    public Role editRole(String id, Role role) {
        Role roleToUpdate = getRoleById(id);
        roleToUpdate.update(role.getName(), role.getDescription(), role.getOperations());
        roleExistentValidator.validate(roleToUpdate);
        return roleRepository.saveRole(roleToUpdate);
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
