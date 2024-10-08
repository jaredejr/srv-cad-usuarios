package br.com.portalgni.cad.usuarios.core.ports;

import br.com.portalgni.cad.usuarios.core.domain.Role;

import javax.management.InvalidAttributeValueException;
import java.util.Optional;
import java.util.Set;

public interface RoleServicePort {

    Role adicionarRole(Role role) throws InvalidAttributeValueException;

    Role editarRole(String id, Role role) throws InvalidAttributeValueException;

    void excluirRole(String id) throws InvalidAttributeValueException;

    Set<Role> buscarTodasAsRoles();

    Role bucarPorId(String id) throws InvalidAttributeValueException;
}
