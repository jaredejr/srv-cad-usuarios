package br.com.unumpeople.cad.users.config;

import br.com.unumpeople.cad.users.core.validation.user.ExistentUserValidation;
import br.com.unumpeople.cad.users.core.validation.user.UpdateContextValidator;
import br.com.unumpeople.cad.users.core.validation.user.UserTypeValidation;
import br.com.unumpeople.cad.users.core.validation.user.UserValidator;
import br.com.unumpeople.cad.users.core.ports.RoleRepositoryPort;
import br.com.unumpeople.cad.users.core.ports.UserRepositoryPort;
import br.com.unumpeople.cad.users.core.ports.UserServicePort;
import br.com.unumpeople.cad.users.core.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBeanConfig {

    @Bean
    public UpdateContextValidator updateContextValidator(UserRepositoryPort userRepositoryPort){
        return new UpdateContextValidator(userRepositoryPort);
    }

    @Bean
    public ExistentUserValidation emailUsuarioValidation(UserRepositoryPort userRepositoryPort){
        return new ExistentUserValidation(userRepositoryPort);
    }

    @Bean
    public UserTypeValidation tipoUsuarioValidator(RoleRepositoryPort roleRepositoryPort) {
        return new UserTypeValidation(roleRepositoryPort);
    }

    @Bean
    public UserValidator createUsuarioValidation(UserTypeValidation userTypeValidation,
                                                 ExistentUserValidation existentUserValidation){
        return new UserValidator(userTypeValidation,
                existentUserValidation);
    }

    @Bean
    public UserServicePort usuarioServicePort(UserRepositoryPort userRepositoryPort,
                                              RoleRepositoryPort roleRepositoryPort,
                                              UserValidator userValidator){
        return new UserService(userRepositoryPort,
                roleRepositoryPort,
                userValidator);
    }
}
