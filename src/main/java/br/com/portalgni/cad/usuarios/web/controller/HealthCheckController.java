package br.com.portalgni.cad.usuarios.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @Value("${app.name}")
    private String applicationName;

    @Value("${app.version}")
    private String applicationVersion;

    @GetMapping("/health")
    public ResponseEntity<String> ok() {
        return ResponseEntity.ok("OK - " + applicationName + " - Rlease: " + applicationVersion);
    }
}
