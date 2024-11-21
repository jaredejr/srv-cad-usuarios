package br.com.portalgni.cad.usuarios.core.validation.usuario;

import br.com.portalgni.cad.usuarios.core.domain.TipoUsuario;
import br.com.portalgni.cad.usuarios.core.domain.Usuario;
import br.com.portalgni.cad.usuarios.core.validation.Validation;
import org.apache.commons.lang3.ObjectUtils;

import javax.management.InvalidAttributeValueException;

public class NotNullUsuarioFieldsValidation implements Validation<Usuario, Usuario> {

    @Override
    public Usuario validate(Usuario usuario) throws InvalidAttributeValueException {
        if (ObjectUtils.anyNull(usuario.getNome()) || usuario.getNome().isEmpty()) throw new InvalidAttributeValueException("O nome não deve nulo ou vazio.");
        if (ObjectUtils.anyNull(usuario.getEmail()) || usuario.getEmail().isEmpty()) throw new InvalidAttributeValueException("O e-mail não deve nulo ou vazio.");
        if (ObjectUtils.anyNull(usuario.getListaTipoUsuario()) || usuario.getListaTipoUsuario().isEmpty()) throw new InvalidAttributeValueException("A lista de tipo de acesso do usuário não deve ser nula ou vazia.");
        for (TipoUsuario tipoUsuario: usuario.getListaTipoUsuario())
            if (ObjectUtils.anyNull(tipoUsuario.getRole())) throw new InvalidAttributeValueException("As Roles da lista TipoUsuario não devem ser nulas.");
        if (ObjectUtils.anyNull(usuario.getStatus()) || usuario.getStatus().isEmpty()) throw new InvalidAttributeValueException("O status do usuário não deve ser nulo ou vazio.");
        return usuario;
    }
}
