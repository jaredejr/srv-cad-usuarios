package br.com.unumpeople.cad.users.core.domain;

import br.com.unumpeople.cad.users.core.exception.DomainValidationException;
import lombok.Getter;

import java.util.List;


@Getter
public class Role {

    private String id;
    private String name;
    private String description;
    private List<Operation> operations;

    private Role (String name, String description, List<Operation> operations) {
        setAndValidateAttributes(name, description, operations);
    }

    private Role (String id, String name, String description, List<Operation> operations) {
        if (null == id || id.isEmpty()) throw new DomainValidationException("O ID não deve nulo ou vazio.");
        this.id = id;
        setAndValidateAttributes(name, description, operations);
    }

    public static Builder builder() {
        return new Builder();
    }

    public void update(String name, String description, List<Operation> operations) {
        setAndValidateAttributes(name, description, operations);
    }

    private void setAndValidateAttributes(String name, String description, List<Operation> operations) {
        checkFields(name, description, operations);
        this.name = name;
        this.description = description;
        this.operations = operations;
    }

    private void checkFields(String name, String description, List<Operation> operations) {
        if (null == name || name.isEmpty())
            throw new DomainValidationException("O name não deve nulo ou vazio.");
        if (null == description || description.isEmpty())
            throw new DomainValidationException("A descrição não deve nula ou vazia.");
        if (null == operations || operations.isEmpty())
            throw new DomainValidationException("A lista de operações não deve ser nula ou vazia.");
    }

    public static class Builder {
        private String name;
        private String description;
        private List<Operation> operations;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setOperations(List<Operation> operations) {
            this.operations = operations;
            return this;
        }

        public Role build(){
            return new Role(name, description, operations);
        }
    }

    public static Role reconstitute(String id, String name, String description, List<Operation> operations) {
        return new Role(id, name, description, operations);
    }

}
