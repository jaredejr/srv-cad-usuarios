package br.com.portalgni.cad.usuarios.config;

import br.com.portalgni.cad.usuarios.core.ports.RoleRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.RoleServicePort;
import br.com.portalgni.cad.usuarios.core.service.RoleService;
import br.com.portalgni.cad.usuarios.core.validation.role.*;
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
