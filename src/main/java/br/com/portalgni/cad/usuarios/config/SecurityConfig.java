package br.com.portalgni.cad.usuarios.config;


import br.com.portalgni.cad.usuarios.infra.repository.InMemoryClientKeyRepository;
import br.com.portalgni.cad.usuarios.web.auth.ClientAwareJwtDecoder;
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
                        .requestMatchers(HttpMethod.POST,"/role/**", "/usuario/**").hasRole("SYSTEM_ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/role/**", "/usuario/**").hasRole("SYSTEM_ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/role/**", "/usuario/**").hasRole("SYSTEM_ADMIN")
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
