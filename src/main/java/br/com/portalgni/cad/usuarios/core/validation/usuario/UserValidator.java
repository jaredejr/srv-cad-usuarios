package br.com.portalgni.cad.usuarios.core.validation.usuario;

import br.com.portalgni.cad.usuarios.core.domain.Usuario;
import br.com.portalgni.cad.usuarios.core.validation.ValidationStrategy;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

import javax.management.InvalidAttributeValueException;

@AllArgsConstructor
public class CreateUsuarioValidation implements ValidationStrategy<Usuario, Usuario> {

    NotNullUsuarioFieldsValidation notNullUsuarioFieldsValidation;
    StatusUsuarioValidator statusUsuarioValidator;
    TipoUsuarioValidator tipoUsuarioValidator;
    EmailUsuarioValidation emailUsuarioValidation;


    @Override
    public Usuario validate(Usuario usuario) throws InvalidAttributeValueException {
        if (ObjectUtils.anyNotNull(usuario.getId()))
            throw new InvalidAttributeValueException("Ao criar um Usuario, o id não deve ser informado.");
        return tipoUsuarioValidator.validate(
                statusUsuarioValidator.validate(
                        emailUsuarioValidation.validate(
                                notNullUsuarioFieldsValidation.validate(usuario))));
    }
}
