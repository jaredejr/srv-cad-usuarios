package br.com.portalgni.cad.usuarios.core.validation.role;

import br.com.portalgni.cad.usuarios.core.domain.Role;
import br.com.portalgni.cad.usuarios.core.validation.Validation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

import javax.management.InvalidAttributeValueException;

@AllArgsConstructor
public class CreateRoleValidation implements Validation<Role, Role> {

    NotNullRoleFieldsValidation notNullFieldsValidation;
    RoleNameValidator roleNameValidator;

    @Override
    public Role validate(Role role) throws InvalidAttributeValueException {
        if (ObjectUtils.anyNotNull(role.getId())) throw new InvalidAttributeValueException("Ao criar uma Role, o id não deve ser informado.");
        return roleNameValidator.validate(
                notNullFieldsValidation.validate(role));
    }
}
