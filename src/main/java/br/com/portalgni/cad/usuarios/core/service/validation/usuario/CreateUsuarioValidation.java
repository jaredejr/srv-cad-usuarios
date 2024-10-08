package br.com.portalgni.cad.usuarios.core.service.validation.usuario;

import br.com.portalgni.cad.usuarios.core.domain.Usuario;
import br.com.portalgni.cad.usuarios.core.service.validation.Validation;
import org.apache.commons.lang3.ObjectUtils;

import javax.management.InvalidAttributeValueException;

public class CreateUsuarioValidation implements Validation<Usuario, Usuario> {

    @Override
    public Usuario validate(Usuario usuario) throws InvalidAttributeValueException {
        if (ObjectUtils.anyNotNull(usuario.getId())) throw new InvalidAttributeValueException("Ao criar um Usuario, o id não deve ser informado.");
        return usuario;
    }
}
