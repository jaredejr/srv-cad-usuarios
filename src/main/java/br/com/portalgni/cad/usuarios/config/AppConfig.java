package br.com.portalgni.cad.usuarios.config;

import br.com.portalgni.cad.usuarios.adapter.infra.converter.ObjectIdToRoleConverter;
import br.com.portalgni.cad.usuarios.adapter.infra.repository.RolesRepository;
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
        converters.add(new ObjectIdToRoleConverter(repository));
        return new MongoCustomConversions(converters);
    }
}
