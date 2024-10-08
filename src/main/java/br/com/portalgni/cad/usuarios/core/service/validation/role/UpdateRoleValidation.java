package br.com.portalgni.cad.usuarios.core.service.validation.role;

import br.com.portalgni.cad.usuarios.core.domain.Role;
import br.com.portalgni.cad.usuarios.core.service.validation.Validation;
import lombok.AllArgsConstructor;

import javax.management.InvalidAttributeValueException;

@AllArgsConstructor
public class UpdateRoleValidation implements Validation<Role, Role> {

    NotNullRoleFieldsValidation notNullFieldsValidation;
    RoleIdValidator roleIdValidator;

    @Override
    public Role validate(Role role) throws InvalidAttributeValueException {
        roleIdValidator.validate(role.getId());
        return notNullFieldsValidation.validate(role);
    }
}
