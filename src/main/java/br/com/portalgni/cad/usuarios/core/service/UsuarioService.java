package br.com.portalgni.cad.usuarios.core.service;

import br.com.portalgni.cad.usuarios.core.domain.Usuario;
import br.com.portalgni.cad.usuarios.core.ports.UsuarioRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.UsuarioServicePort;
import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public class UsuarioService implements UsuarioServicePort {

    private UsuarioRepositoryPort usuarioRepository;

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Usuario criarUsuário(Usuario usuario) {
        return null;
    }

    @Override
    public Usuario editarUsuário(Usuario usuario) {
        return null;
    }

    @Override
    public Set<Usuario> buscarUsuarioPorNome(String nome) {
        return Set.of();
    }

    @Override
    public Usuario buscarUsuarioPorId(String id) {
        return null;
    }

    @Override
    public Set<Usuario> buscarUsuarioPorTipo(String tipo) {
        return Set.of();
    }

    @Override
    public Set<Usuario> buscarTodos() {
        return Set.of();
    }

    @Override
    public void excluirUsuario(Usuario usuario) {

    }
}
