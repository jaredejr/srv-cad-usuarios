package br.com.unumpeople.cad.users.core.domain;

import br.com.unumpeople.cad.users.core.exception.DomainValidationException;
import lombok.Getter;

@Getter
public class Operation {

    private final String name;
    private final String description;

    public Operation(String name, String description) {
        if (null == name || name.isEmpty()) throw new DomainValidationException("O name não deve ser nulo ou vazio");
        if (null == description || description.isEmpty()) throw new DomainValidationException("A descrição não deve ser nula ou vazia");
        if (Boolean.FALSE.equals(Status.ValidUserStatusEnum.isValid(name))) throw new DomainValidationException("Tipo de operação inválida!");
        this.name = name;
        this.description = description;
    }

    private enum ValidOperationNames {
        READ_SITE,
        CREATE_SITE,
        UPDATE_SITE,
        DELETE_SITE,
        READ_USER,
        CREATE_USER,
        UPDATE_USER,
        DELETE_USER,
        READ_ROLE,
        CREATE_ROLE,
        UPDATE_ROLE,
        DELETE_ROLE,
        READ_COMPANY,
        CREATE_COMPANY,
        UPDATE_COMPANY,
        DELETE_COMPANY,
        READ_ADS,
        CREATE_ADS,
        UPDATE_ADS,
        DELETE_ADS;

        public static boolean isValid(String value) {
            for (Operation.ValidOperationNames operation : Operation.ValidOperationNames.values()) {
                if (operation.name().equalsIgnoreCase(value)) {
                    return true;
                }
            }
            return false;
        }
    }
}
