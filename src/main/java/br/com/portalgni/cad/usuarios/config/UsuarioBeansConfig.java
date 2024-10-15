package br.com.portalgni.cad.usuarios.config;

import br.com.portalgni.cad.usuarios.adapter.infra.repository.RolesRepository;
import br.com.portalgni.cad.usuarios.adapter.infra.repository.UsuarioRepository;
import br.com.portalgni.cad.usuarios.core.ports.RoleRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.RoleServicePort;
import br.com.portalgni.cad.usuarios.core.ports.UsuarioRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.UsuarioServicePort;
import br.com.portalgni.cad.usuarios.core.service.RoleService;
import br.com.portalgni.cad.usuarios.core.service.UsuarioService;
import br.com.portalgni.cad.usuarios.core.service.validation.role.*;
import br.com.portalgni.cad.usuarios.core.service.validation.usuario.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class UsuarioBeansConfig {

    @Bean
    public NotNullUsuarioFieldsValidation notNullUsuarioFieldsValidation(){
        return new NotNullUsuarioFieldsValidation();
    }

    @Bean
    public UpdateContextValidator updateContextValidator(UsuarioRepositoryPort usuarioRepositoryPort){
        return new UpdateContextValidator(usuarioRepositoryPort);
    }

    @Bean
    public EmailUsuarioValidation emailUsuarioValidation(UsuarioRepositoryPort usuarioRepositoryPort){
        return new EmailUsuarioValidation(usuarioRepositoryPort);
    }

    @Bean
    public UsuarioIdValidator usuarioIdValidator(UsuarioRepositoryPort usuarioRepositoryPort){
        return new UsuarioIdValidator(usuarioRepositoryPort);
    }

    @Bean
    public StatusUsuarioValidator statusUsuarioValidato() {
        return new StatusUsuarioValidator();
    }

    @Bean
    public TipoUsuarioValidator tipoUsuarioValidator(RoleRepositoryPort roleRepositoryPort,
                                                     RoleIdValidator roleIdValidator) {
        return new TipoUsuarioValidator(roleRepositoryPort, roleIdValidator);
    }

    @Bean
    public CreateUsuarioValidation createUsuarioValidation(NotNullUsuarioFieldsValidation notNullUsuarioFieldsValidation,
                                                           StatusUsuarioValidator statusUsuarioValidator,
                                                           TipoUsuarioValidator tipoUsuarioValidator,
                                                           EmailUsuarioValidation emailUsuarioValidation){
        return new CreateUsuarioValidation(notNullUsuarioFieldsValidation,
                statusUsuarioValidator,
                tipoUsuarioValidator,
                emailUsuarioValidation);
    }

    @Bean
    public UpdateUsuarioValidation updateUsuarioValidation(UsuarioIdValidator usuarioIdValidator,
                                                           NotNullUsuarioFieldsValidation notNullUsuarioFieldsValidation,
                                                           StatusUsuarioValidator statusUsuarioValidator,
                                                           TipoUsuarioValidator tipoUsuarioValidator,
                                                           EmailUsuarioValidation emailUsuarioValidation){
        return new UpdateUsuarioValidation(usuarioIdValidator,
                notNullUsuarioFieldsValidation,
                statusUsuarioValidator,
                tipoUsuarioValidator,
                emailUsuarioValidation);
    }

    @Bean
    public UsuarioServicePort usuarioServicePort(UsuarioRepositoryPort usuarioRepositoryPort,
                                                 RoleRepositoryPort roleRepositoryPort,
                                                 CreateUsuarioValidation createUsuarioValidation,
                                                 UpdateUsuarioValidation updateUsuarioValidation,
                                                 UsuarioIdValidator usuarioIdValidator){
        return new UsuarioService(usuarioRepositoryPort,
                roleRepositoryPort,
                createUsuarioValidation,
                updateUsuarioValidation,
                usuarioIdValidator);
    }
}
