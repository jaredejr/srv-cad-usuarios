package br.com.portalgni.cad.usuarios.config;

import br.com.portalgni.cad.usuarios.adapter.infra.converter.ObjectIdToRoleEntityConverter;
import br.com.portalgni.cad.usuarios.adapter.infra.repository.RolesRepository;
import br.com.portalgni.cad.usuarios.core.ports.RoleRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.RoleServicePort;
import br.com.portalgni.cad.usuarios.core.ports.UsuarioRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.UsuarioServicePort;
import br.com.portalgni.cad.usuarios.core.service.RoleService;
import br.com.portalgni.cad.usuarios.core.service.UsuarioService;
import br.com.portalgni.cad.usuarios.core.service.validation.role.CreateRoleValidation;
import br.com.portalgni.cad.usuarios.core.service.validation.role.NotNullRoleFieldsValidation;
import br.com.portalgni.cad.usuarios.core.service.validation.role.RoleIdValidator;
import br.com.portalgni.cad.usuarios.core.service.validation.role.UpdateRoleValidation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RoleBeansConfig {

    private final RolesRepository repository;

    public RoleBeansConfig(@Lazy RolesRepository repository){
        this.repository = repository;
    }

    @Bean
    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new ObjectIdToRoleEntityConverter(repository));
        return new MongoCustomConversions(converters);
    }

    @Bean
    public RoleIdValidator roleIdValidator(RoleRepositoryPort roleRepositoryPort){
        return new RoleIdValidator(roleRepositoryPort);
    }

    @Bean
    public NotNullRoleFieldsValidation notNullRoleFieldsValidation(){
        return new NotNullRoleFieldsValidation();
    }

    @Bean
    public CreateRoleValidation createRoleValidation(NotNullRoleFieldsValidation notNullRoleFieldsValidation){
        return new CreateRoleValidation(notNullRoleFieldsValidation);
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

    @Bean
    public UsuarioServicePort usuarioServicePort(UsuarioRepositoryPort usuarioRepositoryPort){
        return new UsuarioService(usuarioRepositoryPort);
    }
}
