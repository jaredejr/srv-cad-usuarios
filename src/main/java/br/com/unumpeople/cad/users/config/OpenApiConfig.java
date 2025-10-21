package br.com.unumpeople.cad.users.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;


import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition()
@SecurityScheme(
        name = "security_auth", // Um nome de referência para o esquema de segurança
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(
                authorizationCode = @OAuthFlow(
                        authorizationUrl = "${authorization.server.issuer-uri.external}/oauth2/authorize", // Busca a URL das suas properties
                        tokenUrl = "${authorization.server.issuer-uri.external}/oauth2/token",             // Busca a URL das suas properties
                        scopes = {
                                @OAuthScope(name = "openid", description = "Scope padrão do OIDC"),
                                @OAuthScope(name = "USER_DATA", description = "Permissão para acessar dados de usuários"),
                                @OAuthScope(name = "ROLE_DATA", description = "Permissão para acessar dados de perfis (roles)")
                                // Adicione outros scopes aqui conforme necessário
                        }
                )
        )
)
public class OpenApiConfig {
    // Injeta os mesmos valores que o HealthCheckController usa
    @Value("${springdoc.info.title}")
    private String title;

    @Value("${springdoc.info.version}")
    private String version;

    @Value("${springdoc.info.description}")
    private String description;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .version(version)
                        .description(description));
    }
}
