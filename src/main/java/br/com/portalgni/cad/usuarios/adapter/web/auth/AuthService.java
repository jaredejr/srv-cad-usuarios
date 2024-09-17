package br.com.portalgni.cad.usuarios.adapter.web.auth;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final JwtService jwtService;

    public AuthService(JwtService jwtService){
        this.jwtService = jwtService;
    }

    public String auth(Authentication authentication){
        log.error("AuthService autenticando ".concat(authentication.getName()));
        return jwtService.generateToken(authentication);
    }
}
