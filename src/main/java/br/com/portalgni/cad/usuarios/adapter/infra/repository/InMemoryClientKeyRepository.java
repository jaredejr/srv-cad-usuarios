package br.com.portalgni.cad.usuarios.adapter.infra.repository;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


@Repository
public class InMemoryClientKeyRepository implements ClientKeyRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryClientKeyRepository.class);

    // Agora injeta as chaves como String
    @Value("${jwt.public.key}")
    private String publicKeyPem;

    @Value("${jwt.private.key}")
    private String privateKeyPem;

    private Map<String, RSAPublicKey> clientPublicKeys;
    private Map<String, RSAPrivateKey> clientPrivateKeys;

    // Usar @PostConstruct para inicializar após a injeção das propriedades
    @PostConstruct
    public void init() {
        clientPublicKeys = new HashMap<>();
        clientPrivateKeys = new HashMap<>();

        if (publicKeyPem == null || publicKeyPem.isEmpty() || publicKeyPem.equals("#") ||
                privateKeyPem == null || privateKeyPem.isEmpty() || privateKeyPem.equals("#")) {
            log.warn("Chaves pública ou privada não configuradas ou com valor placeholder ('#'). O repositório de chaves não será populado.");
            // Você pode decidir lançar uma exceção aqui se as chaves forem obrigatórias
            // throw new IllegalStateException("Chaves JWT pública e privada devem ser configuradas.");
            return;
        }

        try {
            RSAPublicKey rsaPublicKey = convertPemToPublicKey(publicKeyPem);
            RSAPrivateKey rsaPrivateKey = convertPemToPrivateKey(privateKeyPem);

            clientPublicKeys.put("client-web", rsaPublicKey);
            clientPrivateKeys.put("client-web", rsaPrivateKey);
            log.info("Chaves RSA para 'client-web' carregadas e processadas com sucesso.");

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("Erro ao processar as chaves PEM RSA: {}", e.getMessage(), e);
            // Lançar uma exceção aqui pode ser apropriado para falhar o startup
            // se as chaves são essenciais e inválidas.
            throw new RuntimeException("Falha ao carregar ou converter chaves JWT PEM.", e);
        } catch (IllegalArgumentException e) {
            log.error("Erro ao decodificar chave Base64 (verifique o formato PEM): {}", e.getMessage(), e);
            throw new RuntimeException("Formato inválido para chave JWT PEM (Base64).", e);
        }
    }

    private RSAPublicKey convertPemToPublicKey(String pem) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String publicKeyContent = pem
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", ""); // Remove todos os espaços em branco, incluindo quebras de linha

        byte[] decodedKey = Base64.getDecoder().decode(publicKeyContent);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

    private RSAPrivateKey convertPemToPrivateKey(String pem) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String privateKeyContent = pem
                .replace("-----BEGIN PRIVATE KEY-----", "") // Comum para PKCS#8
                .replace("-----BEGIN RSA PRIVATE KEY-----", "") // Comum para PKCS#1
                .replace("-----END PRIVATE KEY-----", "")
                .replace("-----END RSA PRIVATE KEY-----", "")
                .replaceAll("\\s", ""); // Remove todos os espaços em branco, incluindo quebras de linha

        byte[] decodedKey = Base64.getDecoder().decode(privateKeyContent);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey); // PKCS#8 é o padrão mais moderno
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }

    @Override
    public RSAPublicKey getPublicKey(String clientId) {
        // Com @PostConstruct, não precisamos mais da verificação 'if (clientPublicKeys == null) init();'
        return clientPublicKeys.get(clientId);
    }

    @Override
    public RSAPrivateKey getPrivateKey(String clientId) {
        // Com @PostConstruct, não precisamos mais da verificação 'if (clientPrivateKeys == null) init();'
        return clientPrivateKeys.get(clientId);
    }

    public String[] getClientIds() {
        // Com @PostConstruct, não precisamos mais da verificação 'if (clientPublicKeys == null) init();'
        // No entanto, se clientPrivateKeys puder estar vazio devido a chaves não configuradas,
        // é bom verificar se é nulo antes de chamar keySet() para evitar NullPointerException.
        if (clientPrivateKeys == null) {
            return new String[0];
        }
        return clientPrivateKeys.keySet().toArray(new String[0]);
    }
}
