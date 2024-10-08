package br.com.portalgni.cad.usuarios.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.management.InvalidAttributeValueException;
import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_FORMAT = "{\"%s\":\"%s\"}";

    private String formatErrorMessage(String title, String message){
        return String.format(ERROR_FORMAT,title,message);
    }

    @ExceptionHandler(Exception.class) // Captura qualquer tipo de exceção
    public ResponseEntity<String> handleException(Exception ex) {
        log.error("Erro: ", ex);
        String errorMessage = formatErrorMessage("ERRO","Ocorreu um erro interno no servidor.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler(InvalidAttributeValueException.class)
    public ResponseEntity<String> handleInvalidAttributeValueException(InvalidAttributeValueException ex) {
        String errorMessage = formatErrorMessage("Valor inválido: ",ex.getMessage());
        return ResponseEntity.badRequest().body(errorMessage);
    }
}
