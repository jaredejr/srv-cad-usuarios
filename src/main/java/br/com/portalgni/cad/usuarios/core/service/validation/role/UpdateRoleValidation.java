package br.com.portalgni.cad.usuarios.core.service.validation.role;

import br.com.portalgni.cad.usuarios.core.domain.Role;
import br.com.portalgni.cad.usuarios.core.service.validation.Validation;

import javax.management.InvalidAttributeValueException;

public class UpdateRoleValidation extends NotNullFieldsValidation implements Validation<Role> {

    @Override
    public void validate(Role role) throws InvalidAttributeValueException {
        if (role.getId()!=null) throw new InvalidAttributeValueException("Ao atualizar uma role, o id não deve nulo.");
        super.validate(role);
    }
}
