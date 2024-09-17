package br.com.portalgni.cad.usuarios.core.ports;

import br.com.portalgni.cad.usuarios.core.domain.Usuario;

import java.util.Set;

public interface UsuarioServicePort {
    Usuario findByEmail(String email);

    Usuario criarUsuário(Usuario usuario);

    Usuario editarUsuário(Usuario usuario);

    Set<Usuario> buscarUsuarioPorNome(String nome);

    Usuario buscarUsuarioPorId(String id);

    Set<Usuario> buscarUsuarioPorTipo(String tipo);

    Set<Usuario> buscarTodos();

    void excluirUsuario(Usuario usuario);
}
