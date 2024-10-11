package br.com.portalgni.cad.usuarios.core.service.validation.role;

import br.com.portalgni.cad.usuarios.core.domain.Role;
import br.com.portalgni.cad.usuarios.core.ports.RoleRepositoryPort;
import br.com.portalgni.cad.usuarios.core.service.validation.Validation;
import br.com.portalgni.cad.usuarios.core.service.validation.util.Utils;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;

import javax.management.InvalidAttributeValueException;
import java.util.NoSuchElementException;
import java.util.Optional;

@AllArgsConstructor
public class RoleIdValidator implements Validation<String, Role> {

    RoleRepositoryPort roleRepository;

    @Override
    public Role validate(String id) throws InvalidAttributeValueException {
        if (Boolean.FALSE.equals(Utils.isValidHex(id)))
            throw new InvalidAttributeValueException("O formato do id da Role é inválido.");
        return findAndThrowIfNotFound(id);
    }

    private Role findAndThrowIfNotFound(String id) throws InvalidAttributeValueException {
        return roleRepository.buscarPorId(id)
                .orElseThrow(()-> new InvalidAttributeValueException("Role não encontrada"));
    }
}
