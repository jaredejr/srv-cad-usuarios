package br.com.portalgni.cad.usuarios.core.validation.usuario;

import br.com.portalgni.cad.usuarios.core.domain.User;
import br.com.portalgni.cad.usuarios.core.exception.DomainValidationException;
import br.com.portalgni.cad.usuarios.core.ports.UserRepositoryPort;
import br.com.portalgni.cad.usuarios.core.validation.ValidationStrategy;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Optional;

@AllArgsConstructor
public class ExistentUserValidation implements ValidationStrategy<User> {

    UserRepositoryPort usuarioRepository;



    @Override
    public void validate(User user) {
        if (checkIfAlreadyExists(user.getEmail().getValue(), user.getId()))
            throw new DomainValidationException("E-mail já cadastrado para outro usuário");

    }


    private boolean checkIfAlreadyExists(String email, String id){
        Optional<User> usuario = usuarioRepository.findByEmail(email);
        return usuario.isPresent()
                && (ObjectUtils.anyNull(id) || Boolean.FALSE.equals(usuario.get().getId().equals(id)));
    }
}
