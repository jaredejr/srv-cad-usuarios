package br.com.portalgni.cad.usuarios.core.service.validation.usuario;


import br.com.portalgni.cad.usuarios.core.service.validation.util.ValidStatusUsuario;
import br.com.portalgni.cad.usuarios.core.domain.Usuario;
import br.com.portalgni.cad.usuarios.core.service.validation.Validation;

import javax.management.InvalidAttributeValueException;

public class StatusUsuarioValidator implements Validation<Usuario, Usuario> {

    @Override
    public Usuario validate(Usuario usuario) throws InvalidAttributeValueException {
        if(Boolean.FALSE.equals(ValidStatusUsuario.isValid(usuario.getStatus())))
            throw new InvalidAttributeValueException("Status inválido!");
        return usuario;
    }

}
