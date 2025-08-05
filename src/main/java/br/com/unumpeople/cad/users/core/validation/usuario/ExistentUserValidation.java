package br.com.unumpeople.cad.users.core.validation.usuario;

import br.com.unumpeople.cad.users.core.domain.User;
import br.com.unumpeople.cad.users.core.exception.DomainValidationException;
import br.com.unumpeople.cad.users.core.ports.UserRepositoryPort;
import br.com.unumpeople.cad.users.core.validation.ValidationStrategy;
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
