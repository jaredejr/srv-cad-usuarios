package br.com.unumpeople.cad.users.core.domain;

import br.com.unumpeople.cad.users.core.exception.DomainValidationException;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
public class User {

    private String id;
    private String name;
    private Email email;
    private Password password;
    private final Set<Address> addressList;
    private final Set<Document> documents;
    private Set<UserRoleContext> userRoleContextList;
    private final LocalDateTime creationDate;
    private LocalDateTime lastAccess;
    private Status status;

    private User(String email, String name, Set<Address> addressList, Set<Document> documents, Set<UserRoleContext> userRoleContextList, String status) {

        if (userRoleContextList == null || userRoleContextList.isEmpty()) throw new DomainValidationException("A lista de Roles do usuário não deve ser nula ou vazia");
        this.email = new Email(email);
        validateAndSetName(name);
        this.addressList = addressList != null ? addressList : Set.of();
        this.documents = documents != null ? documents : Set.of();
        this.userRoleContextList = userRoleContextList;
        this.creationDate = LocalDateTime.now();
        this.lastAccess = LocalDateTime.now();
        this.status = new Status(status);
    }

    private User(String id, String name, String email, String senha, Set<Address> addressList, Set<Document> documents, Set<UserRoleContext> UserRoleContextList, LocalDateTime creationDate, LocalDateTime lastAccess, String status) {

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
        if (null == name || name.isEmpty()) throw new DomainValidationException("O name não deve ser nulo ou vazio");
        this.name = name;
    }

    public void updateLastAccess(){
        this.lastAccess = LocalDateTime.now();
    }

    public void updateUser(UserFieldsForUpdate userFields) {
        if (userFields.getName() != null)
            validateAndSetName(userFields.getName());

        if (userFields.getEmail() != null)
            this.email = new Email(userFields.getEmail());

        if (userFields.getUserRoleContextList() != null && !userFields.getUserRoleContextList().isEmpty())
            this.userRoleContextList = userFields.getUserRoleContextList();

        if (userFields.getStatus() != null)
            this.status = new Status(userFields.getStatus());
    }

    public void setPassword(String value) {
        this.password = new Password(value);
    }

    public void addNewAddress(Address address){
        this.addressList.add(address);
    }

    public void removeAddress(String addressId) {
        Address addressToRemove = addressList
                .stream()
                .filter(address -> address.getId().equals(addressId))
                .findFirst()
                .orElseThrow(()-> new RuntimeException("Endereço não encontrado"));
        this.addressList.remove(addressToRemove);
    }

    public void addNewDocument(Document document){
        this.documents.add(document);
    }

    public void removeDocument(String documentNumber) {
        Document documentToRemove = documents
                .stream()
                .filter(doc -> doc.getNumber().equals(documentNumber))
                .findFirst()
                .orElseThrow(()-> new RuntimeException("Documento não encontrado"));
        this.addressList.remove(documentToRemove);
    }

    public static INameStep builder() {
        return new Builder();
    }

    public static User reconstitute(String id, String name, String email, String password, Set<Address> addressList, Set<Document> documents, Set<UserRoleContext> userRoleContextList, LocalDateTime creationDate, LocalDateTime lastAccess, String status) {
        return new User(id, name, email, password, addressList, documents, userRoleContextList, creationDate, lastAccess, status);
    }

    public static class Builder implements INameStep, IEmailStep, IRoleContextListStep, IStatusStep, IBuilderStep {

        private String name;
        private String email;
        private Set<Address> addressList;
        private Set<Document> documents;
        private Set<UserRoleContext> userRoleContextList;
        private String status;

        @Override
        public IRoleContextListStep setEmail(String email) {
            this.email = email;
            return this;
        }

        @Override
        public IEmailStep setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public IStatusStep setRoleContextList(Set<UserRoleContext> roleContextList) {
            this.userRoleContextList = roleContextList;
            return this;
        }

        @Override
        public IBuilderStep setStatus(String status) {
            this.status = status;
            return this;
        }

        @Override
        public IBuilderStep setAddresses(Set<Address> addresses) {
            this.addressList = addresses;
            return this;
        }

        @Override
        public IBuilderStep setDocuments(Set<Document> documents) {
            this.documents = documents;
            return this;
        }

        @Override
        public User build() {
            return new User(name, email, addressList, documents, userRoleContextList, status);
        }
    }

    public interface INameStep {
        IEmailStep setName(String name);
    }

    public interface IEmailStep {
        IRoleContextListStep setEmail(String email);
    }

    public interface IRoleContextListStep {
        IStatusStep setRoleContextList(Set<UserRoleContext> roleContextList);
    }

    public interface IStatusStep {
        IBuilderStep setStatus(String status);
    }

    public interface IBuilderStep {
        IBuilderStep setAddresses(Set<Address> addresses);
        IBuilderStep setDocuments(Set<Document> documents);
        User build();
    }

    @Data
    @AllArgsConstructor
    public static class UserFieldsForUpdate {
        private String name;
        private String email;
        private Set<UserRoleContext> userRoleContextList;
        private String status;
    }


}
