package br.com.portalgni.cad.usuarios.core.ports;

import br.com.portalgni.cad.usuarios.core.domain.Usuario;

import javax.naming.directory.InvalidAttributeValueException;
import java.util.Set;

public interface UsuarioServicePort {
    Usuario findByEmail(String email) throws javax.management.InvalidAttributeValueException;

    Usuario criarUsuario(Usuario usuario) throws javax.management.InvalidAttributeValueException;

    Usuario editarUsuario(String id, Usuario usuario) throws javax.management.InvalidAttributeValueException;

    Set<Usuario> buscarUsuarioPorNome(String nome) throws javax.management.InvalidAttributeValueException;

    Usuario buscarUsuarioPorId(String id) throws javax.management.InvalidAttributeValueException;

    Set<Usuario> buscarUsuarioPorTipo(String roleName, String contexto) throws InvalidAttributeValueException, javax.management.InvalidAttributeValueException;

    Set<Usuario> buscarTodos();

    void excluirUsuario(String id) throws javax.management.InvalidAttributeValueException;
}
