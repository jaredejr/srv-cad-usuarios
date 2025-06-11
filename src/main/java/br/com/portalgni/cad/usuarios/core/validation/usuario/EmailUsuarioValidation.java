package br.com.portalgni.cad.usuarios.core.validation.usuario;

import br.com.portalgni.cad.usuarios.core.domain.Usuario;
import br.com.portalgni.cad.usuarios.core.ports.UsuarioRepositoryPort;
import br.com.portalgni.cad.usuarios.core.validation.Validation;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.management.InvalidAttributeValueException;

@AllArgsConstructor
public class EmailUsuarioValidation implements Validation<Usuario, Usuario> {

    UsuarioRepositoryPort usuarioRepository;

    private static final String EMAIL_PATTERN
            = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    @Override
    public Usuario validate(Usuario usuario) throws InvalidAttributeValueException {
        if (checkIfAlreadyExists(usuario.getEmail(), usuario.getId()))
            throw new InvalidAttributeValueException("E-mail já cadastrado para outro usuário");
        if (Boolean.FALSE.equals(isValidEmail(usuario.getEmail())))
            throw new InvalidAttributeValueException("O formato do e-mail não é válido.");
        return usuario;
    }

    private static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean checkIfAlreadyExists(String email, String id){
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        return usuario.isPresent()
                && (ObjectUtils.anyNull(id) || Boolean.FALSE.equals(usuario.get().getId().equals(id)));
    }
}
