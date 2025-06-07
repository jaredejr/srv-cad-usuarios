package br.com.portalgni.cad.usuarios.adapter.web.auth;

import br.com.portalgni.cad.usuarios.adapter.infra.repository.InMemoryClientKeyRepository;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.bind.annotation.*;

import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final ClientAwareJwtDecoder jwtDecoder;
    private final InMemoryClientKeyRepository clientKeyRepository;

    @GetMapping("/health")
    public ResponseEntity<String> ok() {
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/authenticate")
    public Map<String, String> authenticate(Authentication authentication,
                                            @Parameter(in = ParameterIn.HEADER, description = "Client ID", required = true, schema = @Schema(type = "string"))
                                            @RequestHeader("X-Client-Id") String clientId) {
        log.error("autenticando usuário: ".concat(authentication.getName()));
        System.out.println("autenticando usuário: ".concat(authentication.getName()));
        String tokenValue = authService.auth(authentication);
        return Map.of("token", tokenValue);
    }

    @PostMapping("/validate-token")
    public Map<String, Object> validateToken(@RequestBody Map<String, String> request) {
        String token = request.get("token");
        try {
            Jwt jwt = jwtDecoder.decode(token);
            // Token válido
            return Map.of("valid", true, "claims", jwt.getClaims());
        } catch (JwtException e) {
            // Token inválido
            return Map.of("valid", false, "error", e.getMessage());
        }
    }

    @GetMapping("/jwks")
    public Map<String, Object> getJwks() {
        List<JWK> keys = new ArrayList<>();
        for (String clientId : clientKeyRepository.getClientIds()) {
            RSAPublicKey publicKey = clientKeyRepository.getPublicKey(clientId);
            if (publicKey != null) {
                JWK jwk = new RSAKey.Builder(publicKey)
                        .keyID(clientId) // Define o clientId como kid
                        .build();
                keys.add(jwk);
            }
        }
        return new JWKSet(keys).toJSONObject();
    }

}

