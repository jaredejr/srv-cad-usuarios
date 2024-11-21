package br.com.portalgni.cad.usuarios.core.service;

import br.com.portalgni.cad.usuarios.core.domain.Role;
import br.com.portalgni.cad.usuarios.core.ports.RoleRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.RoleServicePort;
import br.com.portalgni.cad.usuarios.core.validation.role.CreateRoleValidation;
import br.com.portalgni.cad.usuarios.core.validation.role.RoleIdValidator;
import br.com.portalgni.cad.usuarios.core.validation.role.UpdateRoleValidation;
import lombok.AllArgsConstructor;

import javax.management.InvalidAttributeValueException;
import java.util.Set;

@AllArgsConstructor
public class RoleService implements RoleServicePort {

    RoleRepositoryPort roleRepository;
    CreateRoleValidation createRoleValidation;
    UpdateRoleValidation updateRoleValidation;
    RoleIdValidator roleIdValidator;

    @Override
    public Role adicionarRole(Role role) throws InvalidAttributeValueException {
        return roleRepository.salvarRole(createRoleValidation.validate(role));
    }

    @Override
    public Role editarRole(String id, Role role) throws InvalidAttributeValueException {
        role.setId(id);
        return roleRepository.salvarRole(updateRoleValidation.validate(role));
    }

    @Override
    public void excluirRole(String id) throws InvalidAttributeValueException {
        roleRepository.excluirRole(roleIdValidator.validate(id));
    }

    @Override
    public Set<Role> buscarTodasAsRoles() {
        return roleRepository.buscarTodasAsRoles();
    }

    @Override
    public Role bucarPorId(String id) throws InvalidAttributeValueException {
        return roleIdValidator.validate(id);
    }


}
