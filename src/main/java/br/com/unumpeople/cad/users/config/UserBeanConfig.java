package br.com.portalgni.cad.usuarios.config;

import br.com.portalgni.cad.usuarios.core.ports.RoleRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.UserRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.UserServicePort;
import br.com.portalgni.cad.usuarios.core.service.UserService;
import br.com.portalgni.cad.usuarios.core.validation.usuario.*;
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
