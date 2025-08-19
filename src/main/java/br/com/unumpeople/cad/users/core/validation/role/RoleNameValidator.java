package br.com.unumpeople.cad.users.core.validation.role;

import br.com.unumpeople.cad.users.core.domain.Role;
import br.com.unumpeople.cad.users.core.exception.DomainValidationException;
import br.com.unumpeople.cad.users.core.validation.ValidationStrategy;
import br.com.unumpeople.cad.users.core.ports.RoleRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoleNameValidator implements ValidationStrategy<Role> {

    RoleRepositoryPort roleRepository;

    @Override
    public void validate(Role role) {
        if (checkIfAlreadyExists(role.getName()))
            throw new DomainValidationException("O name da Role informada já existe.");
    }

    private Boolean checkIfAlreadyExists(String nome) {
        return roleRepository.getRoleByName(nome).isPresent();
    }
}
