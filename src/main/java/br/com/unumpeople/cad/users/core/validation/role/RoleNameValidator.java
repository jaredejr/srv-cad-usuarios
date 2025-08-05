package br.com.portalgni.cad.usuarios.core.validation.role;

import br.com.portalgni.cad.usuarios.core.domain.Role;
import br.com.portalgni.cad.usuarios.core.exception.DomainValidationException;
import br.com.portalgni.cad.usuarios.core.ports.RoleRepositoryPort;
import br.com.portalgni.cad.usuarios.core.validation.ValidationStrategy;
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
