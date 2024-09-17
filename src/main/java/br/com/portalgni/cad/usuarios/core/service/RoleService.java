package br.com.portalgni.cad.usuarios.core.service;

import br.com.portalgni.cad.usuarios.core.domain.Role;
import br.com.portalgni.cad.usuarios.core.ports.RoleRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.RoleServicePort;
import br.com.portalgni.cad.usuarios.core.service.validation.role.CreateRoleValidation;
import br.com.portalgni.cad.usuarios.core.service.validation.role.UpdateRoleValidation;
import lombok.AllArgsConstructor;

import javax.management.InvalidAttributeValueException;
import java.util.Set;

@AllArgsConstructor
public class RoleService implements RoleServicePort {

    RoleRepositoryPort roleRepository;
    CreateRoleValidation createRoleValidation;
    UpdateRoleValidation updateRoleValidation;

    @Override
    public Role adicionarRole(Role role) throws InvalidAttributeValueException {
        createRoleValidation.validate(role);
        return roleRepository.salvarRole(role);
    }

    @Override
    public Role editarRole(Role role) throws InvalidAttributeValueException {
        updateRoleValidation.validate(role);
        return roleRepository.salvarRole(role);
    }

    @Override
    public void excluirRole(String id) {
        roleRepository.excluirRole(id);
    }

    @Override
    public Set<Role> buscarTodasAsRoles() {
        return roleRepository.buscarTodasAsRoles();
    }

}
