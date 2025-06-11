package br.com.portalgni.cad.usuarios.config;

import br.com.portalgni.cad.usuarios.core.ports.RoleRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.RoleServicePort;
import br.com.portalgni.cad.usuarios.core.service.RoleService;
import br.com.portalgni.cad.usuarios.core.validation.role.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleBeansConfig {

    @Bean
    public RoleIdValidator roleIdValidator(RoleRepositoryPort roleRepositoryPort){
        return new RoleIdValidator(roleRepositoryPort);
    }

    @Bean
    public RoleNameValidator roleNameValidator(RoleRepositoryPort rolesRepository){
        return new RoleNameValidator(rolesRepository);
    }

    @Bean
    public NotNullRoleFieldsValidation notNullRoleFieldsValidation(){
        return new NotNullRoleFieldsValidation();
    }

    @Bean
    public CreateRoleValidation createRoleValidation(NotNullRoleFieldsValidation notNullRoleFieldsValidation,
                                                     RoleNameValidator roleNameValidator){
        return new CreateRoleValidation(notNullRoleFieldsValidation, roleNameValidator);
    }

    @Bean
    public UpdateRoleValidation updateRoleValidation(NotNullRoleFieldsValidation notNullRoleFieldsValidation,
                                                     RoleIdValidator roleIdValidator){
        return new UpdateRoleValidation(notNullRoleFieldsValidation, roleIdValidator);
    }

    @Bean
    public RoleServicePort roleServicePort(
            RoleRepositoryPort roleRepository,
            CreateRoleValidation createRoleValidation,
            UpdateRoleValidation updateRoleValidation,
            RoleIdValidator roleIdValidator){
        return new RoleService(roleRepository, createRoleValidation, updateRoleValidation, roleIdValidator);
    }
}
