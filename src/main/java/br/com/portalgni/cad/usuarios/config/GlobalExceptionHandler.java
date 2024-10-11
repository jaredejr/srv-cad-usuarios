package br.com.portalgni.cad.usuarios.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MissingRequestHeaderException;
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

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        log.error("Erro: ", ex);
        String errorMessage = formatErrorMessage("ERRO","Ocorreu um erro interno no servidor.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<String> handleMissingRequestHeaderException(MissingRequestHeaderException ex){
        log.error("ERRO: Request header ausente. ", ex);
        String errorMessage = formatErrorMessage("ERRO", "Request header ausente. ".concat(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<String> handleAuthorizationDeniedException(AuthorizationDeniedException ex){
        log.error("ERRO: Acesso não autorizado. ", ex);
        String errorMessage = formatErrorMessage("ERRO", "Acesso não autorizado. ".concat(ex.getMessage()));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }

    @ExceptionHandler(InvalidAttributeValueException.class)
    public ResponseEntity<String> handleInvalidAttributeValueException(InvalidAttributeValueException ex) {
        String errorMessage = formatErrorMessage("Requisição inválida: ",ex.getMessage());
        return ResponseEntity.badRequest().body(errorMessage);
    }
}
