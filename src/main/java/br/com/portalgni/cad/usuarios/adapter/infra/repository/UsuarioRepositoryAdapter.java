package br.com.portalgni.cad.usuarios.adapter.infra.repository;

import br.com.portalgni.cad.usuarios.adapter.infra.converter.EntityToUsuarioConverter;
import br.com.portalgni.cad.usuarios.adapter.infra.converter.TipoUsuarioToEntityConverter;
import br.com.portalgni.cad.usuarios.adapter.infra.converter.UsuarioToEntityConverter;
import br.com.portalgni.cad.usuarios.core.domain.TipoUsuario;
import br.com.portalgni.cad.usuarios.core.domain.Usuario;
import br.com.portalgni.cad.usuarios.core.ports.UsuarioRepositoryPort;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    UsuarioRepository usuarioRepository;
    EntityToUsuarioConverter entityToUsuario;
    UsuarioToEntityConverter usuarioToEntity;
    TipoUsuarioToEntityConverter tipoUsuarioToEntity;

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email).map(entityToUsuario::convert);
    }

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        return entityToUsuario.convert(
                usuarioRepository.save(
                        Objects.requireNonNull(usuarioToEntity.convert(usuario))));
    }

    @Override
    public Set<Usuario> buscarUsuarioPorNome(String nome) {
        return usuarioRepository.
                findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(entityToUsuario::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public Optional<Usuario> buscarUsuarioPorId(String id) {
        return usuarioRepository.findById(new ObjectId(id)).map(entityToUsuario::convert);
    }

    @Override
    public Set<Usuario> buscarUsuarioPorTipo(TipoUsuario tipoUsuario) {
        return usuarioRepository
                .findByTipoUsuarioContaining(tipoUsuarioToEntity.convert(tipoUsuario))
                .stream()
                .map(entityToUsuario::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public void excluirUsuario(Usuario usuario) {
        usuarioRepository.delete(Objects.requireNonNull(usuarioToEntity.convert(usuario)));
    }

    @Override
    public Set<Usuario> buscarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(entityToUsuario::convert)
                .collect(Collectors.toSet());
    }
}
