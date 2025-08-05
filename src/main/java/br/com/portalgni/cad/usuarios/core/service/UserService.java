package br.com.portalgni.cad.usuarios.core.service;

import br.com.portalgni.cad.usuarios.core.domain.UserRoleContext;
import br.com.portalgni.cad.usuarios.core.domain.User;
import br.com.portalgni.cad.usuarios.core.exception.DomainValidationException;
import br.com.portalgni.cad.usuarios.core.ports.RoleRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.UsuarioRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.UsuarioServicePort;
import br.com.portalgni.cad.usuarios.core.validation.usuario.UserValidator;
import lombok.AllArgsConstructor;

import javax.management.InvalidAttributeValueException;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
public class UsuarioService implements UsuarioServicePort {

    private UsuarioRepositoryPort usuarioRepository;
    private RoleRepositoryPort roleRepository;
    private UserValidator userValidator;

    private static final String NENHUM_USUARIO_ENCONTRADO = "Nenhum usuário encontrado";

    @Override
    public User buscarUsuarioPorEmail(String email) throws InvalidAttributeValueException {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(()-> new InvalidAttributeValueException(NENHUM_USUARIO_ENCONTRADO));
    }

    @Override
    public User criarUsuario(User user) {
        userValidator.validate(user);
        return usuarioRepository.salvarUsuario(user);
    }

    @Override
    public User editarUsuario(String id, User user){
        User editedUser = new User(id,
                user.getName(),
                user.getEmail().getValue(),
                user.getSenha(),
                user.getAdresses(),
                user.getDocuments(),
                user.getListaUserRoleContext(),
                user.getDataCriacao(),
                user.getUltimoAcesso(),
                user.getStatus().getValue());
        return usuarioRepository.salvarUsuario(editedUser);
    }

    @Override
    public Set<User> buscarUsuarioPorNome(String nome) throws InvalidAttributeValueException {
        return Optional.ofNullable(usuarioRepository.buscarUsuarioPorNome(nome))
                .filter(set -> Boolean.FALSE.equals(set.isEmpty()))
                .orElseThrow(()-> new InvalidAttributeValueException(NENHUM_USUARIO_ENCONTRADO));
    }

    @Override
    public User buscarUsuarioPorId(String id) {
        return usuarioRepository.buscarUsuarioPorId(id).orElseThrow(() -> new DomainValidationException("Usuário não encontrado!"));
    }

    @Override
    public Set<User> buscarUsuarioPorTipo(String roleName, String contexto) throws InvalidAttributeValueException {
        return Optional.ofNullable(usuarioRepository.buscarUsuarioPorTipo(new UserRoleContext(
                        roleRepository.buscarRolePorNome(roleName)
                                .orElseThrow(()-> new InvalidAttributeValueException(NENHUM_USUARIO_ENCONTRADO)),
                        contexto)))
                .filter(set -> Boolean.FALSE.equals(set.isEmpty()))
                .orElseThrow(()-> new InvalidAttributeValueException(NENHUM_USUARIO_ENCONTRADO));
    }

    @Override
    public Set<User> buscarTodos() {
        return usuarioRepository.buscarTodos();
    }

    @Override
    public void excluirUsuario(String id) {
        usuarioRepository.excluirUsuario(buscarUsuarioPorId(id));
    }

    @Override
    public void updateLastAccess(User user) {
        user.updateLastAccess();
        usuarioRepository.salvarUsuario(user);
    }
}
