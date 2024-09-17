package br.com.portalgni.cad.usuarios.core.service.validation.usuario;

import br.com.portalgni.cad.usuarios.core.domain.Usuario;
import br.com.portalgni.cad.usuarios.core.service.validation.Validation;

import javax.management.InvalidAttributeValueException;

public class CreateUsuarioValidation implements Validation<Usuario> {

    @Override
    public void validate(Usuario role) throws InvalidAttributeValueException {
        if (role.getId()!=null) throw new InvalidAttributeValueException("Ao criar um Usuario, o id não deve ser informado.");
    }
}
