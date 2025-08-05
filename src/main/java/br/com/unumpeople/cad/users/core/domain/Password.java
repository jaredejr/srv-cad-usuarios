package br.com.portalgni.cad.usuarios.core.domain;

import br.com.portalgni.cad.usuarios.core.exception.DomainValidationException;
import lombok.Getter;

@Getter
public class Password {
    private String value;

    public Password(String value) {
        validatePassword(value);
        this.value = value;
    }

    private void validatePassword(String value) {
        if (null == value || value.isEmpty()) throw new DomainValidationException("A senha não deve ser nula ou vazia");
        if (value.length() < 8) throw new DomainValidationException("A senha deve ter no mínimo 8 caracteres");
    }
}
