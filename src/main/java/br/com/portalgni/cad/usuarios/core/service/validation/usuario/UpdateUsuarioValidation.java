package br.com.portalgni.cad.usuarios.core.service.validation.usuario;

import br.com.portalgni.cad.usuarios.core.domain.Usuario;
import br.com.portalgni.cad.usuarios.core.service.validation.Validation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

import javax.management.InvalidAttributeValueException;

@AllArgsConstructor
public class UpdateUsuarioValidation implements Validation<Usuario, Usuario> {

    UsuarioIdValidator usuarioIdValidator;
    NotNullUsuarioFieldsValidation notNullUsuarioFieldsValidation;
    StatusUsuarioValidator statusUsuarioValidator;
    TipoUsuarioValidator tipoUsuarioValidator;
    EmailUsuarioValidation emailUsuarioValidation;

    @Override
    public Usuario validate(Usuario usuario) throws InvalidAttributeValueException {
        usuarioIdValidator.validate(usuario.getId());
        return tipoUsuarioValidator.validate(
                statusUsuarioValidator.validate(
                        emailUsuarioValidation.validate(
                                notNullUsuarioFieldsValidation.validate(usuario))));
    }
}
