package br.com.unumpeople.cad.users.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;


import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de Cadastro de Usuários",
                version = "v1",
                description = "Serviço responsável pelo gerenciamento de usuários, perfis e autenticação."
        )
)
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
    // A classe agora pode ficar vazia!
    // As anotações no topo já fazem todo o trabalho de configuração.
    // O bean @Bean OpenAPI customOpenAPI() não é mais necessário.
}
