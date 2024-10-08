package br.com.portalgni.cad.usuarios.core.service.validation.role;

import br.com.portalgni.cad.usuarios.core.domain.Role;
import br.com.portalgni.cad.usuarios.core.service.validation.Validation;
import org.apache.commons.lang3.ObjectUtils;

import javax.management.InvalidAttributeValueException;

public class NotNullRoleFieldsValidation implements Validation<Role, Role> {

    @Override
    public Role validate(Role role) throws InvalidAttributeValueException {
        if (ObjectUtils.anyNull(role.getNome()) || role.getNome().isEmpty()) throw new InvalidAttributeValueException("O nome não deve nulo ou vazio.");
        if (ObjectUtils.anyNull(role.getDescricao()) || role.getDescricao().isEmpty()) throw new InvalidAttributeValueException("A descrição não deve nula ou vazia.");
        return role;
    }
}
