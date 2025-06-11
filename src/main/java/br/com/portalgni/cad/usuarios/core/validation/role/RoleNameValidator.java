package br.com.portalgni.cad.usuarios.core.validation.role;

import br.com.portalgni.cad.usuarios.core.domain.Role;
import br.com.portalgni.cad.usuarios.core.ports.RoleRepositoryPort;
import br.com.portalgni.cad.usuarios.core.validation.Validation;
import lombok.AllArgsConstructor;

import javax.management.InvalidAttributeValueException;

@AllArgsConstructor
public class RoleNameValidator implements Validation<Role, Role> {

    RoleRepositoryPort roleRepository;

    @Override
    public Role validate(Role role) throws InvalidAttributeValueException {
        if (checkIfAlreadyExists(role.getNome()))
            throw new InvalidAttributeValueException("O nome da Role informada já existe.");
        return role;
    }

    private Boolean checkIfAlreadyExists(String nome) {
        return roleRepository.buscarRolePorNome(nome).isPresent();
    }
}
