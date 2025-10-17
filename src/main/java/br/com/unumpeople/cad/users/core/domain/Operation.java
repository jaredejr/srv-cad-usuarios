package br.com.unumpeople.cad.users.core.domain;

import br.com.unumpeople.cad.users.core.exception.DomainValidationException;
import lombok.Getter;

import java.util.Map;

@Getter
public class Operation {

    private final String name;
    private final String description;

    public Operation(String name) {
        if (null == name || name.isEmpty()) throw new DomainValidationException("O nome não deve ser nulo ou vazio");
        if (!ValidOperationNames.isValid(name)) throw new DomainValidationException("Tipo de operação inválida!");
        this.name = name;
        this.description = ValidOperationNames.valueOf(name).getDescription();
    }

    public enum ValidOperationNames {

        READ_SITE("Read site information"),
        READ_ALL_SITE("Read all sites information"),
        CREATE_SITE("Create a new site"),
        UPDATE_SITE("Update an existing site"),
        DELETE_SITE("Delete a site"),

        READ_USER("Read user information"),
        READ_ALL_USER("Read all users information"),
        CREATE_USER("Create a new user"),
        UPDATE_USER("Update an existing user"),
        DELETE_USER("Delete a user"),

        READ_ROLE("Read role information"),
        READ_ALL_ROLE("Read all roles information"),
        CREATE_ROLE("Create a new role"),
        UPDATE_ROLE("Update an existing role"),
        DELETE_ROLE("Delete a role"),

        READ_COMPANY("Read company information"),
        READ_ALL_COMPANY("Read all companies information"),
        CREATE_COMPANY("Create a new company"),
        UPDATE_COMPANY("Update an existing company"),
        DELETE_COMPANY("Delete a company"),

        READ_ADS("Read advertisement information"),
        READ_ALL_ADS("Read all advertisements information"),
        CREATE_ADS("Create a new advertisement"),
        UPDATE_ADS("Update an existing advertisement"),
        DELETE_ADS("Delete an advertisement");

        private final String description;

        ValidOperationNames(String description) {
            this.description = description;
        }

        String getDescription() {
            return this.description;
        }

        public static boolean isValid(String value) {
            for (ValidOperationNames operation : ValidOperationNames.values()) {
                if (operation.name().equalsIgnoreCase(value)) {
                    return true;
                }
            }
            return false;
        }
    }
}
