package br.com.portalgni.cad.usuarios.core.ports;

import br.com.portalgni.cad.usuarios.core.domain.User;

import javax.naming.directory.InvalidAttributeValueException;
import java.util.Set;

public interface UsuarioServicePort {
    User buscarUsuarioPorEmail(String email) throws javax.management.InvalidAttributeValueException;

    User criarUsuario(User user) throws javax.management.InvalidAttributeValueException;

    User editarUsuario(String id, User user) throws javax.management.InvalidAttributeValueException;

    Set<User> buscarUsuarioPorNome(String nome) throws javax.management.InvalidAttributeValueException;

    User buscarUsuarioPorId(String id) throws javax.management.InvalidAttributeValueException;

    Set<User> buscarUsuarioPorTipo(String roleName, String contexto) throws InvalidAttributeValueException, javax.management.InvalidAttributeValueException;

    Set<User> buscarTodos();

    void excluirUsuario(String id) throws javax.management.InvalidAttributeValueException;

    void updateLastAccess(User user);
}
