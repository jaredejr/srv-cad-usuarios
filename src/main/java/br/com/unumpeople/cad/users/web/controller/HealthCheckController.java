package br.com.unumpeople.cad.users.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @Value("${springdoc.info.title}")
    private String applicationName;

    @Value("${springdoc.info.version}")
    private String applicationVersion;

    @GetMapping("/health")
    public ResponseEntity<String> ok() {
        return ResponseEntity.ok("OK - " + applicationName + " - Rlease: " + applicationVersion);
    }
}
