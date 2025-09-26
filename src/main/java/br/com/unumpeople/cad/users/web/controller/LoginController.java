package br.com.unumpeople.cad.users.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        // Retorna o nome do template HTML (sem a extensão .html)
        // O Thymeleaf irá procurar por "src/main/resources/templates/login.html"
        return "login";
    }
}
