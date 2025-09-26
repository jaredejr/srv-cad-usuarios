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
        if (checkIfAlreadyExists(role.getName(), role.getId()))
            throw new DomainValidationException("O name da Role informada já existe.");
    }

    private Boolean checkIfAlreadyExists(String nome, String id) {
        Role role = roleRepository.getRoleByName(nome).orElse(null);
        if (null == role) return Boolean.FALSE;
        if (id.equals(role.getId())) return Boolean.FALSE;
        return Boolean.TRUE;
    }
}
