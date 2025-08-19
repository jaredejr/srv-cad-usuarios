package br.com.unumpeople.cad.users.core.validation.user;

import br.com.unumpeople.cad.users.core.domain.User;
import br.com.unumpeople.cad.users.core.validation.Validator;

import java.util.List;

public class UserValidator extends Validator<User> {

    UserTypeValidation userTypeValidation;
    ExistentUserValidation existentUserValidation;

    public UserValidator(UserTypeValidation userTypeValidation, ExistentUserValidation existentUserValidation){
        super(List.of(userTypeValidation, existentUserValidation));
    }

}
