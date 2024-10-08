package br.com.portalgni.cad.usuarios.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${app.name}")
    private String applicationName;

    @Value("${app.version}")
    private String applicationVersion;

    @Value("${app.description}")
    private String applicationDescription;



    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("auth-api")
                .pathsToMatch("/authenticate")
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
                                        new SecurityRequirement().addList("basicAuth", List.of("/authenticate"))));
                    }
                })
                .build();
    }

    @Bean
    public GroupedOpenApi
    privateApi() {
        return GroupedOpenApi.builder()
                .group("private-api")
                .pathsToExclude("/authenticate")
                .pathsToMatch("/**")
                .addOpenApiCustomizer(new OpenApiCustomizer() {
                    @Override
                    public void customise(OpenAPI openApi) {
                        openApi.components(new Components()
                                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")))
                            .info(new Info().title(applicationName)
                                    .version(applicationVersion)
                                    .description(getDescription()))
                            .security(List.of(
                                    new SecurityRequirement().addList("bearerAuth")));
                    }
                })
                .build();
    }

    public String getDescription() {
        Charset isoCharset = StandardCharsets.ISO_8859_1;
        Charset utf8Charset = StandardCharsets.UTF_8;
        return new String(applicationDescription.getBytes(isoCharset), utf8Charset);
    }
}
