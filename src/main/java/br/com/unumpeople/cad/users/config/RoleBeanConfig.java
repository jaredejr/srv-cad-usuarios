package br.com.unumpeople.cad.users.config;

import br.com.unumpeople.cad.users.core.ports.RoleRepositoryPort;
import br.com.unumpeople.cad.users.core.ports.RoleServicePort;
import br.com.unumpeople.cad.users.core.service.RoleService;
import br.com.unumpeople.cad.users.core.validation.role.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleBeanConfig {

    @Bean
    public RoleNameValidator roleNameValidator(RoleRepositoryPort rolesRepository){
        return new RoleNameValidator(rolesRepository);
    }

    @Bean
    public RoleServicePort roleServicePort(
            RoleRepositoryPort roleRepository,
            RoleNameValidator roleNameValidator){
        return new RoleService(roleRepository, roleNameValidator);
    }
}
