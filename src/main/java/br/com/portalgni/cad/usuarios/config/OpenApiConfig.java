package br.com.portalgni.cad.usuarios.config;

import br.com.portalgni.cad.usuarios.adapter.web.dto.RoleDto;
import br.com.portalgni.cad.usuarios.adapter.web.dto.TipoUsuarioDto;
import br.com.portalgni.cad.usuarios.adapter.web.dto.UsuarioDto;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = {"br.com.portalgni.cad.usuarios.adapter.web"})
public class OpenApiConfig {

    @Value("${app.name}")
    private String applicationName;

    @Value("${app.version}")
    private String applicationVersion;

    @Value("${app.description}")
    private String applicationDescription;


    @Bean
    public GroupedOpenApi privateApi() {
        return GroupedOpenApi.builder()
                .group("private-api")
                .pathsToExclude("/authenticate", "/validate-token", "/jwks")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("auth-api")
                .pathsToMatch("/authenticate", "/validate-token", "/jwks")
                .addOpenApiCustomizer(new OpenApiCustomizer() {
                    @Override
                    public void customise(OpenAPI openApi) {
                        openApi.components(new Components()
                                        .addSecuritySchemes("basicAuth", new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("basic")))
                                .info(new Info().title(applicationName)
                                        .version(applicationVersion)
                                        .description(getDescription()))
                                .security(List.of(
                                        new SecurityRequirement().addList(
                                                "basicAuth", List.of("/authenticate", "/validate-token"))));
                    }
                })
                .build();
    }

    public String getDescription() {
        Charset isoCharset = StandardCharsets.ISO_8859_1;
        Charset utf8Charset = StandardCharsets.UTF_8;
        return new String(applicationDescription.getBytes(isoCharset), utf8Charset);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title(applicationName)
                        .version(applicationVersion)
                        .description(getDescription()))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .security(List.of(
                        new SecurityRequirement().addList("bearerAuth")));
    }

}
