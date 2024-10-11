package br.com.portalgni.cad.usuarios.adapter.infra.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;


@Repository
public class InMemoryClientKeyRepository implements ClientKeyRepository {

    @Value("${jwt.public.key}")
    private RSAPublicKey key;

    @Value("${jwt.private.key}")
    private RSAPrivateKey priv;

    private Map<String, RSAPublicKey> clientPublicKeys;
    private Map<String, RSAPrivateKey> clientPrivateKeys;

    public void init(){
        clientPublicKeys = new HashMap<>();
        clientPrivateKeys = new HashMap<>();
        clientPublicKeys.put("client-web", key);
        clientPrivateKeys.put("client-web", priv);
    }

    @Override
    public RSAPublicKey getPublicKey(String clientId) {
        if(clientPublicKeys==null) init();
        return clientPublicKeys.get(clientId);
    }

    @Override
    public RSAPrivateKey getPrivateKey(String clientId) {
        if(clientPrivateKeys==null) init();
        return clientPrivateKeys.get(clientId);
    }

    public String[] getClientIds() {
        if(clientPublicKeys==null) init();
        return clientPrivateKeys.keySet().toArray(new String[0]);
    }
}
