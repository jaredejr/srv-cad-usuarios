package br.com.portalgni.cad.usuarios.core.ports;

import br.com.portalgni.cad.usuarios.core.domain.TipoUsuario;
import br.com.portalgni.cad.usuarios.core.domain.Usuario;

import java.util.Optional;
import java.util.Set;

public interface UsuarioRepositoryPort {
    Optional<Usuario> findByEmail(String email);

    Usuario salvarUsuario(Usuario usuario);

    Set<Usuario> buscarUsuarioPorNome(String nome);

    Optional<Usuario> buscarUsuarioPorId(String id);

    Set<Usuario> buscarUsuarioPorTipo(TipoUsuario tipoUsuario);

    void excluirUsuario(Usuario usuario);

    Set<Usuario> buscarTodos();
}
