package br.com.portalgni.cad.usuarios.config;

import br.com.portalgni.cad.usuarios.core.ports.RoleRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.UsuarioRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.UsuarioServicePort;
import br.com.portalgni.cad.usuarios.core.service.UsuarioService;
import br.com.portalgni.cad.usuarios.core.validation.usuario.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioBeansConfig {

    @Bean
    public UpdateContextValidator updateContextValidator(UsuarioRepositoryPort usuarioRepositoryPort){
        return new UpdateContextValidator(usuarioRepositoryPort);
    }

    @Bean
    public ExistentUserValidation emailUsuarioValidation(UsuarioRepositoryPort usuarioRepositoryPort){
        return new ExistentUserValidation(usuarioRepositoryPort);
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
    public UsuarioServicePort usuarioServicePort(UsuarioRepositoryPort usuarioRepositoryPort,
                                                 RoleRepositoryPort roleRepositoryPort,
                                                 UserValidator userValidator){
        return new UsuarioService(usuarioRepositoryPort,
                roleRepositoryPort,
                userValidator);
    }
}
