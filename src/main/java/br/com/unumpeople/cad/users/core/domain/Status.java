package br.com.portalgni.cad.usuarios.core.domain;

import br.com.portalgni.cad.usuarios.core.exception.DomainValidationException;
import lombok.Getter;

@Getter
public class Status {

    private String value;

    public Status( String status ) {
        if (null == status || status.isEmpty()) throw new DomainValidationException("O status não deve ser nulo ou vazio");
        if(Boolean.FALSE.equals(ValidUserStatusEnum.isValid(status))) throw new DomainValidationException("Status inválido!");
    }

    enum ValidUserStatusEnum {

        ACTIVE,
        INACTIVE;

        public static boolean isValid(String value) {
            for (ValidUserStatusEnum status : ValidUserStatusEnum.values()) {
                if (status.name().equalsIgnoreCase(value)) {
                    return true;
                }
            }
            return false;
        }

    }
}
