package br.com.portalgni.cad.usuarios.adapter.web.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @GetMapping("/testRole")
    public String getMessage() {
        return "HELLO from API controller";
    }
}
