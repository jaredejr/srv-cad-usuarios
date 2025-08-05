package br.com.portalgni.cad.usuarios.core.validation.usuario;

import br.com.portalgni.cad.usuarios.core.domain.User;
import br.com.portalgni.cad.usuarios.core.validation.Validator;

import java.util.List;

public class UserValidator extends Validator<User> {

    UserTypeValidation userTypeValidation;
    ExistentUserValidation existentUserValidation;

    public UserValidator(UserTypeValidation userTypeValidation, ExistentUserValidation existentUserValidation){
        super(List.of(userTypeValidation, existentUserValidation));
    }

}
