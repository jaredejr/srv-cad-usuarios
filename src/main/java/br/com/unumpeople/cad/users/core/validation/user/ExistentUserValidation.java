package br.com.unumpeople.cad.users.core.validation.user;

import br.com.unumpeople.cad.users.core.domain.User;
import br.com.unumpeople.cad.users.core.exception.DomainValidationException;
import br.com.unumpeople.cad.users.core.validation.ValidationStrategy;
import br.com.unumpeople.cad.users.core.ports.UserRepositoryPort;
import lombok.AllArgsConstructor;

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
                && (null==id || Boolean.FALSE.equals(usuario.get().getId().equals(id)));
    }
}
