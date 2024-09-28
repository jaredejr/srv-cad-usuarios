package br.com.portalgni.cad.usuarios.adapter.infra.repository;

import br.com.portalgni.cad.usuarios.adapter.infra.converter.EntityToUsuarioConverter;
import br.com.portalgni.cad.usuarios.adapter.infra.converter.TipoUsuarioToEntityConverter;
import br.com.portalgni.cad.usuarios.adapter.infra.converter.UsuarioToEntityConverter;
import br.com.portalgni.cad.usuarios.adapter.infra.entity.UsuarioEntity;
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
    public Usuario findByEmail(String email) {
        return entityToUsuario.convert(usuarioRepository.findByEmail(email));
    }

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        return entityToUsuario.convert(usuarioRepository.save(Objects.requireNonNull(usuarioToEntity.convert(usuario))));
    }

    @Override
    public Set<Usuario> buscarUsuarioPorNome(String nome) {
        return usuarioRepository.
                findAllByNome(nome)
                .stream()
                .map(entityToUsuario::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public Usuario buscarUsuarioPorId(String id) {
        Optional<UsuarioEntity> entity = usuarioRepository.findById(new ObjectId(id));
        return entity.map(entityToUsuario::convert).orElse(null);
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
    public void excluirUsuario(String id) {

    }

    @Override
    public Set<Usuario> buscarTodos() {
        return Set.of();
    }
}
