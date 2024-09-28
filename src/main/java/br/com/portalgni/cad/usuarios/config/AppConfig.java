package br.com.portalgni.cad.usuarios.config;

import br.com.portalgni.cad.usuarios.adapter.infra.converter.EntityToRoleConverter;
import br.com.portalgni.cad.usuarios.adapter.infra.converter.ObjectIdToRoleEntityConverter;
import br.com.portalgni.cad.usuarios.adapter.infra.converter.RoleToEntityConverter;
import br.com.portalgni.cad.usuarios.adapter.infra.repository.RolesRepository;
import br.com.portalgni.cad.usuarios.adapter.infra.repository.RolesRepositoryAdapter;
import br.com.portalgni.cad.usuarios.core.ports.RoleRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.RoleServicePort;
import br.com.portalgni.cad.usuarios.core.ports.UsuarioRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.UsuarioServicePort;
import br.com.portalgni.cad.usuarios.core.service.RoleService;
import br.com.portalgni.cad.usuarios.core.service.UsuarioService;
import br.com.portalgni.cad.usuarios.core.service.validation.role.CreateRoleValidation;
import br.com.portalgni.cad.usuarios.core.service.validation.role.UpdateRoleValidation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {

    private final RolesRepository repository;

    public AppConfig(@Lazy RolesRepository repository){
        this.repository = repository;
    }

    @Bean
    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new ObjectIdToRoleEntityConverter(repository));
        return new MongoCustomConversions(converters);
    }

    @Bean
    public CreateRoleValidation createRoleValidation(){
        return new CreateRoleValidation();
    }

    @Bean
    public UpdateRoleValidation updateRoleValidation(){
        return new UpdateRoleValidation();
    }

    @Bean
    public RoleServicePort roleServicePort(
            RoleRepositoryPort roleRepository,
            CreateRoleValidation createRoleValidation,
            UpdateRoleValidation updateRoleValidation){
        return new RoleService(roleRepository, createRoleValidation, updateRoleValidation);
    }

    @Bean
    public UsuarioServicePort usuarioServicePort(UsuarioRepositoryPort usuarioRepositoryPort){
        return new UsuarioService(usuarioRepositoryPort);
    }
}
