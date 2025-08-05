package br.com.portalgni.cad.usuarios.core.validation.usuario;

import br.com.portalgni.cad.usuarios.core.domain.Usuario;
import br.com.portalgni.cad.usuarios.core.exception.DomainValidationException;
import br.com.portalgni.cad.usuarios.core.ports.UsuarioRepositoryPort;
import br.com.portalgni.cad.usuarios.core.validation.ValidationStrategy;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.InvalidAttributeValueException;

@AllArgsConstructor
public class EmailUsuarioValidation implements ValidationStrategy<Usuario> {

    UsuarioRepositoryPort usuarioRepository;



    @Override
    public void validate(Usuario usuario) {
        if (checkIfAlreadyExists(usuario.getEmail().getValue(), usuario.getId()))
            throw new DomainValidationException("E-mail já cadastrado para outro usuário");

    }


    private boolean checkIfAlreadyExists(String email, String id){
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        return usuario.isPresent()
                && (ObjectUtils.anyNull(id) || Boolean.FALSE.equals(usuario.get().getId().equals(id)));
    }
}
