package br.com.portalgni.cad.usuarios.core.service.validation.role;

import br.com.portalgni.cad.usuarios.core.domain.Role;
import br.com.portalgni.cad.usuarios.core.service.validation.Validation;

import javax.management.InvalidAttributeValueException;

public class CreateRoleValidation extends NotNullFieldsValidation implements Validation<Role> {

    @Override
    public void validate(Role role) throws InvalidAttributeValueException {
        if (role.getId()!=null) throw new InvalidAttributeValueException("Ao criar uma Role, o id não deve ser informado.");
        super.validate(role);
    }
}
