package br.com.portalgni.cad.usuarios.infra.repository;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public interface ClientKeyRepository {
    RSAPublicKey getPublicKey(String clientId);
    RSAPrivateKey getPrivateKey(String clientId);
}
