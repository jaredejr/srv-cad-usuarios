package br.com.unumpeople.cad.users.config;


import br.com.unumpeople.cad.users.infra.repository.InMemoryClientKeyRepository;
import br.com.unumpeople.cad.users.web.auth.ClientAwareJwtDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    InMemoryClientKeyRepository clientKeyRepository;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/authenticate", "/validate-token", "/jwks", "/health").permitAll()
                        .requestMatchers(HttpMethod.GET,"/role/**").hasRole("READ_ROLE")
                        .requestMatchers(HttpMethod.POST,"/role/**").hasRole("CREATE_ROLE")
                        .requestMatchers(HttpMethod.PUT,"/role/**").hasRole("UPDATE_ROLE")
                        .requestMatchers(HttpMethod.DELETE,"/role/**").hasRole("DELETE_ROLE")
                        .requestMatchers(HttpMethod.GET, "/usuario/**").hasRole("READ_USER")
                        .requestMatchers(HttpMethod.POST, "/usuario/**").hasRole("CREATE_USER")
                        .requestMatchers(HttpMethod.PUT, "/usuario/**").hasRole("UPDATE_USER")
                        .requestMatchers(HttpMethod.DELETE, "/usuario/**").hasRole("DELETE_USER")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .oauth2ResourceServer(conf -> conf.jwt(jwt -> jwt.decoder(clientAwareJwtDecoder())))
                .securityContext(securityContext -> securityContext.requireExplicitSave(false))
                .build();
    }

    @Bean
    ClientAwareJwtDecoder clientAwareJwtDecoder() {
        return new ClientAwareJwtDecoder(clientKeyRepository);
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        grantedAuthoritiesConverter.setAuthoritiesClaimName("roles");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);

        return jwtAuthenticationConverter;

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
