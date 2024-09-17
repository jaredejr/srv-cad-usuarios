package br.com.portalgni.cad.usuarios.core.service.validation.role;

import br.com.portalgni.cad.usuarios.core.domain.Role;
import br.com.portalgni.cad.usuarios.core.service.validation.Validation;

import javax.management.InvalidAttributeValueException;

public class NotNullFieldsValidation implements Validation<Role> {

    @Override
    public void validate(Role role) throws InvalidAttributeValueException {
        if (role.getNome()==null || role.getNome().isEmpty()) throw new InvalidAttributeValueException("O nome não deve nulo ou vazio.");
        if (role.getDescricao()==null || role.getDescricao().isEmpty()) throw new InvalidAttributeValueException("A descrição não deve nula ou vazia.");
    }
}
