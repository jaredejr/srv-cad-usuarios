package br.com.portalgni.cad.usuarios.adapter.web.auth;

import br.com.portalgni.cad.usuarios.adapter.infra.repository.ClientKeyRepository;
import br.com.portalgni.cad.usuarios.adapter.infra.repository.InMemoryClientKeyRepository;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientAwareJwtDecoder implements JwtDecoder {

    private final InMemoryClientKeyRepository clientKeyRepository;
    private final Map<String, JwtDecoder> decoders = new ConcurrentHashMap<>();

    public ClientAwareJwtDecoder(InMemoryClientKeyRepository clientKeyRepository) {
        this.clientKeyRepository = clientKeyRepository;
    }

    @Override
    public Jwt decode(String token) throws JwtException {
        String clientId = extractClientId(token);
        Jwt jwt = getDecoder(clientId).decode(token);
        return jwt;
    }

    private String extractClientId(String token) {
        try {
            SignedJWT decodedJWT = SignedJWT.parse(token);
            return decodedJWT.getJWTClaimsSet().getStringClaim("clientId");
        } catch (Exception e) {
            throw new JwtException("Error extracting clientId from token", e);
        }
    }

    private JwtDecoder getDecoder(String clientId) {
        return decoders.computeIfAbsent(clientId, this::createDecoder);
    }

    private JwtDecoder createDecoder(String clientId) {
        RSAPublicKey publicKey = clientKeyRepository.getPublicKey(clientId);
        if (publicKey == null) {
            throw new JwtException("Public key not found for client: " + clientId);
        }
        return NimbusJwtDecoder.withPublicKey(publicKey).build();
    }
}