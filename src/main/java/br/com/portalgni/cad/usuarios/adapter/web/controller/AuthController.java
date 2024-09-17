package br.com.portalgni.cad.usuarios.adapter.web.controller;

import br.com.portalgni.cad.usuarios.adapter.web.auth.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/authenticate")
    public String authenticate(Authentication authentication) {
        log.error("autenticando usuário: ".concat(authentication.getName()));
        System.out.println("autenticando usuário: ".concat(authentication.getName()));
        return authService.auth(authentication);
    }

}

