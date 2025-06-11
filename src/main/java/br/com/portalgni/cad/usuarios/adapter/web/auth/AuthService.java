package br.com.portalgni.cad.usuarios.adapter.web.auth;


import br.com.portalgni.cad.usuarios.adapter.infra.repository.ClientKeyRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPublicKey;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final JwtService jwtService;
    private final HttpServletRequest request;

    public AuthService(JwtService jwtService, HttpServletRequest request){
        this.jwtService = jwtService;
        this.request = request;
    }

    public String auth(Authentication authentication){
        String clientId = request.getHeader("X-Client-Id");
        log.error("######## --- CLIENT ID : "+ clientId);
        return jwtService.generateToken(authentication, clientId);
    }
}
