package br.com.portalgni.cad.usuarios.core.exception;

public class DomainValidationException extends RuntimeException{
    public DomainValidationException(String message) {
        super(message);
    }
}
