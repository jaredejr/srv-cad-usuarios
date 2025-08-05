package br.com.unumpeople.cad.users.core.exception;

public class DomainValidationException extends RuntimeException{
    public DomainValidationException(String message) {
        super(message);
    }
}
