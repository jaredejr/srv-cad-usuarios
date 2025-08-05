package br.com.portalgni.cad.usuarios.web.auth;

import br.com.portalgni.cad.usuarios.infra.repository.InMemoryClientKeyRepository;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JwtService {

    private InMemoryClientKeyRepository clientKeyRepository;

    private static final Logger log = LoggerFactory.getLogger(JwtService.class);

    public String generateToken(Authentication authentication, String clientId) {
        log.error("gerando token do usuario ".concat(authentication.getName()));
        Instant now = Instant.now();
        long expiry = 3600;
        UsuarioDetails usuarioDetails = (UsuarioDetails) authentication.getPrincipal();
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("srv-cad-usuarios")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("roles", roles)
                .claim("clientId", clientId)
                .claim("contextMap", usuarioDetails.getContextMap())
                .claim("userId", usuarioDetails.getUserId())
                .build();

        RSAPublicKey publicKey = clientKeyRepository.getPublicKey(clientId);
        log.info(publicKey.toString());
        RSAPrivateKey privateKey = clientKeyRepository.getPrivateKey(clientId);
        log.info(privateKey.toString());
        JWK jwk = new RSAKey.Builder(publicKey).privateKey(privateKey).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        JwtEncoder encoder = new NimbusJwtEncoder(jwks);

        return encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
