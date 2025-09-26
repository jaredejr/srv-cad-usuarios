package br.com.unumpeople.cad.users.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.OAuthFlow;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
@ComponentScan(basePackages = {"br.com.unumpeople.cad.users.adapter.web"})
public class OpenApiConfig {

    @Value("${authorization.server.issuer-uri}")
    private String authorizationServerUri;

    @Value("${app.name}")
    private String applicationName;

    @Value("${app.version}")
    private String applicationVersion;

    @Value("${app.description}")
    private String applicationDescription;


    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "oAuth2";

        return new OpenAPI()
                .info(new Info()
                        .title(applicationName)
                        .version(applicationVersion)
                        .description(applicationDescription))
                // Adiciona o requisito de segurança a todos os endpoints
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                // Configura os componentes de segurança
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.OAUTH2)
                                        .flows(createOAuthFlows())
                        )
                );
    }

    private OAuthFlows createOAuthFlows() {
        // 1. O fluxo que já tínhamos para autenticação de usuário
        OAuthFlow authorizationCodeFlow = new OAuthFlow()
                .authorizationUrl(authorizationServerUri + "/oauth2/authorize")
                .tokenUrl(authorizationServerUri + "/oauth2/token")
                .scopes(new io.swagger.v3.oas.models.security.Scopes()
                        .addString("USER_DATA", "Acesso a dados de usuários")
                        .addString("ROLE_DATA", "Acesso a dados de roles")
                        .addString("SITE_DATA", "Acesso a dados de sites")
                        .addString("COMPANY_DATA", "Acesso a dados de companias")
                        .addString("ADVERTISEMENTS_DATA", "Acesso a dados de anúncios"));

        // 2. O novo fluxo para autenticação da aplicação (Client Credentials)
        OAuthFlow clientCredentialsFlow = new OAuthFlow()
                .tokenUrl(authorizationServerUri + "/oauth2/token")
                .scopes(new io.swagger.v3.oas.models.security.Scopes()
                        .addString("USER_DATA", "Acesso a dados de usuários")
                        .addString("ROLE_DATA", "Acesso a dados de roles")
                        .addString("SITE_DATA", "Acesso a dados de sites")
                        .addString("COMPANY_DATA", "Acesso a dados de companias")
                        .addString("ADVERTISEMENTS_DATA", "Acesso a dados de anúncios"));

        // 3. Retorna um objeto contendo OS DOIS fluxos
        return new OAuthFlows()
                .authorizationCode(authorizationCodeFlow)
                .clientCredentials(clientCredentialsFlow);
    }

    public String getDescription() {
        Charset isoCharset = StandardCharsets.ISO_8859_1;
        Charset utf8Charset = StandardCharsets.UTF_8;
        return new String(applicationDescription.getBytes(isoCharset), utf8Charset);
    }

}
