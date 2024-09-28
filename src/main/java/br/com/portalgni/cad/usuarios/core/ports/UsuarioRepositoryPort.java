package br.com.portalgni.cad.usuarios.core.ports;

import br.com.portalgni.cad.usuarios.core.domain.TipoUsuario;
import br.com.portalgni.cad.usuarios.core.domain.Usuario;

import java.util.Set;

public interface UsuarioRepositoryPort {
    Usuario findByEmail(String email);

    Usuario salvarUsuario(Usuario usuario);

    Set<Usuario> buscarUsuarioPorNome(String nome);

    Usuario buscarUsuarioPorId(String id);

    Set<Usuario> buscarUsuarioPorTipo(TipoUsuario tipoUsuario);

    void excluirUsuario(String id);

    Set<Usuario> buscarTodos();
}
