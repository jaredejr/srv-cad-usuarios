package br.com.unumpeople.cad.users.core.domain;

import br.com.unumpeople.cad.users.core.exception.DomainValidationException;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
public class User {

    private String id;
    private String name;
    private final Email email;
    private Password password;
    private final Set<Address> addressList;
    private final Set<Document> documents;
    private final Set<UserRoleContext> userRoleContextList;
    private final LocalDateTime creationDate;
    private LocalDateTime lastAccess;
    private final Status status;

    public User(String email, String name, Set<Address> addressList, Set<Document> documents, Set<UserRoleContext> userRoleContextList, String status) {

        this.email = new Email(email);
        validateAndSetName(name);
        this.addressList = addressList;
        this.documents = documents;
        this.userRoleContextList = userRoleContextList;
        this.creationDate = LocalDateTime.now();
        this.lastAccess = LocalDateTime.now();
        this.status = new Status(status);
    }

    public User(String id, String name, String email, String senha, Set<Address> addressList, Set<Document> documents, Set<UserRoleContext> UserRoleContextList, LocalDateTime creationDate, LocalDateTime lastAccess, String status) {

        if (null == id || id.isEmpty()) throw new DomainValidationException("O id não deve ser nulo ou vazio");
        this.id = id;
        validateAndSetName(name);
        this.email = new Email(email);
        this.password = new Password(senha);
        this.addressList = addressList;
        this.documents = documents;
        this.userRoleContextList = UserRoleContextList;
        this.creationDate = creationDate;
        this.lastAccess = lastAccess;
        this.status = new Status(status);
    }

    private void validateAndSetName(String name) {
        if (null == this.name || this.name.isEmpty()) throw new DomainValidationException("O name não deve ser nulo ou vazio");
        this.name = name;
    }

    public void updateLastAccess(){
        this.lastAccess = LocalDateTime.now();
    }

    public void setPassword(String value) {
        this.password = new Password(value);
    }
}
