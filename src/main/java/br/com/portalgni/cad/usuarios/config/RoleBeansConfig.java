package br.com.portalgni.cad.usuarios.config;

import br.com.portalgni.cad.usuarios.adapter.infra.converter.ObjectIdToRoleEntityConverter;
import br.com.portalgni.cad.usuarios.adapter.infra.repository.RolesRepository;
import br.com.portalgni.cad.usuarios.core.ports.RoleRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.RoleServicePort;
import br.com.portalgni.cad.usuarios.core.ports.UsuarioRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.UsuarioServicePort;
import br.com.portalgni.cad.usuarios.core.service.RoleService;
import br.com.portalgni.cad.usuarios.core.service.UsuarioService;
import br.com.portalgni.cad.usuarios.core.service.validation.role.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

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
