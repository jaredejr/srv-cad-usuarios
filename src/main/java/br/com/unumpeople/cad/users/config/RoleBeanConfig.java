package br.com.unumpeople.cad.users.config;

import br.com.unumpeople.cad.users.core.validation.role.RoleExistentValidator;
import br.com.unumpeople.cad.users.core.ports.RoleRepositoryPort;
import br.com.unumpeople.cad.users.core.ports.RoleServicePort;
import br.com.unumpeople.cad.users.core.service.RoleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleBeanConfig {

    @Bean
    public RoleExistentValidator roleNameValidator(RoleRepositoryPort rolesRepository){
        return new RoleExistentValidator(rolesRepository);
    }

    @Bean
    public RoleServicePort roleServicePort(
            RoleRepositoryPort roleRepository,
            RoleExistentValidator roleExistentValidator){
        return new RoleService(roleRepository, roleExistentValidator);
    }
}
