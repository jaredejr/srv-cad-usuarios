package br.com.unumpeople.cad.users.core.domain;

import br.com.unumpeople.cad.users.core.exception.DomainValidationException;
import lombok.*;

import java.util.List;


@Getter
public class Role {

    private String id;
    private String name;
    private String description;
    private List<Operation> operations;

    public Role (String name, String description, List<Operation> operations) {
        setAndValidateAttributes(name, description, operations);
    }

    public Role (String id, String name, String description, List<Operation> operations) {
        if (null == id || id.isEmpty()) throw new DomainValidationException("O ID não deve nulo ou vazio.");
        this.id = id;
        setAndValidateAttributes(name, description, operations);
    }

    private void setAndValidateAttributes(String nome, String descricao, List<Operation> operations) {
        if (null == nome || nome.isEmpty()) throw new DomainValidationException("O name não deve nulo ou vazio.");
        if (null == descricao || descricao.isEmpty()) throw new DomainValidationException("A descrição não deve nula ou vazia.");
        if (null == operations || operations.isEmpty()) throw new DomainValidationException("A lista de operações não deve ser nula ou vazia.");
        this.name = nome;
        this.description = descricao;
        this.operations = operations;
    }

}
