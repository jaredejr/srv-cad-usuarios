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
//                                        .addSecuritySchemes("Client-Id", new SecurityScheme()
//                                                .type(SecurityScheme.Type.APIKEY)
//                                                .in(SecurityScheme.In.HEADER)
//                                                .name("X-Client-Id")))
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

    @Bean
    public GroupedOpenApi privateApi() {
        return GroupedOpenApi.builder()
                .group("private-api")
                .packagesToScan("br.com.portalgni.cad.usuarios.adapter.web.controller","br.com.portalgni.cad.usuarios.adapter.web.dto")
            .pathsToExclude("/authenticate","/validate-token")
                .pathsToMatch("/**")
                .addOpenApiCustomizer(new OpenApiCustomizer() {
                    @Override
                    public void customise(OpenAPI openApi) {
                        openApi.components(new Components()
                                        .schemas(Map.of(
                                                "UsuarioDto",  new Schema<UsuarioDto>().$ref("#/components/schemas/" + UsuarioDto.class.getSimpleName()),
                                                "RoleDto", new Schema<RoleDto>().$ref("#/components/schemas/" + RoleDto.class.getSimpleName()),
                                                "TipoUsuarioDto", new Schema<TipoUsuarioDto >().$ref("#/components/schemas/" + TipoUsuarioDto.class.getSimpleName())))
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

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI();
    }

}
