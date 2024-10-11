package br.com.portalgni.cad.usuarios.core.service;

import br.com.portalgni.cad.usuarios.core.domain.Role;
import br.com.portalgni.cad.usuarios.core.domain.TipoUsuario;
import br.com.portalgni.cad.usuarios.core.domain.Usuario;
import br.com.portalgni.cad.usuarios.core.ports.RoleRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.UsuarioRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.UsuarioServicePort;
import br.com.portalgni.cad.usuarios.core.service.validation.role.RoleNameValidator;
import br.com.portalgni.cad.usuarios.core.service.validation.usuario.CreateUsuarioValidation;
import br.com.portalgni.cad.usuarios.core.service.validation.usuario.TipoUsuarioValidator;
import br.com.portalgni.cad.usuarios.core.service.validation.usuario.UpdateUsuarioValidation;
import br.com.portalgni.cad.usuarios.core.service.validation.usuario.UsuarioIdValidator;
import lombok.AllArgsConstructor;

import javax.management.InvalidAttributeValueException;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
public class UsuarioService implements UsuarioServicePort {

    private UsuarioRepositoryPort usuarioRepository;
    private RoleRepositoryPort roleRepository;
    private CreateUsuarioValidation createUsuarioValidation;
    private UpdateUsuarioValidation updateUsuarioValidation;
    private UsuarioIdValidator usuarioIdValidator;

    private static final String NENHUM_USUARIO_ENCONTRADO = "Nenhum usuário encontrado";

    @Override
    public Usuario findByEmail(String email) throws InvalidAttributeValueException {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(()-> new InvalidAttributeValueException(NENHUM_USUARIO_ENCONTRADO));
    }

    @Override
    public Usuario criarUsuario(Usuario usuario) throws InvalidAttributeValueException {
        return usuarioRepository.salvarUsuario(createUsuarioValidation.validate(usuario));
    }

    @Override
    public Usuario editarUsuario(String id, Usuario usuario) throws InvalidAttributeValueException {
        usuario.setId(id);
        return usuarioRepository.salvarUsuario(updateUsuarioValidation.validate(usuario));
    }

    @Override
    public Set<Usuario> buscarUsuarioPorNome(String nome) throws InvalidAttributeValueException {
        return Optional.ofNullable(usuarioRepository.buscarUsuarioPorNome(nome))
                .filter(set -> Boolean.FALSE.equals(set.isEmpty()))
                .orElseThrow(()-> new InvalidAttributeValueException(NENHUM_USUARIO_ENCONTRADO));
    }

    @Override
    public Usuario buscarUsuarioPorId(String id) throws InvalidAttributeValueException {
        return usuarioIdValidator.validate(id);
    }

    @Override
    public Set<Usuario> buscarUsuarioPorTipo(String roleName, String contexto) throws InvalidAttributeValueException {
        return Optional.ofNullable(usuarioRepository.buscarUsuarioPorTipo(new TipoUsuario(
                        roleRepository.buscarRolePorNome(roleName)
                                .orElseThrow(()-> new InvalidAttributeValueException(NENHUM_USUARIO_ENCONTRADO)),
                        contexto)))
                .filter(set -> Boolean.FALSE.equals(set.isEmpty()))
                .orElseThrow(()-> new InvalidAttributeValueException(NENHUM_USUARIO_ENCONTRADO));
    }

    @Override
    public Set<Usuario> buscarTodos() {
        return usuarioRepository.buscarTodos();
    }

    @Override
    public void excluirUsuario(String id) throws InvalidAttributeValueException {
        usuarioRepository.excluirUsuario(usuarioIdValidator.validate(id));
    }
}
