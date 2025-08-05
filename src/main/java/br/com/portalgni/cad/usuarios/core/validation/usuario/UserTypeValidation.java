package br.com.portalgni.cad.usuarios.core.validation.usuario;


import br.com.portalgni.cad.usuarios.core.domain.TipoUsuario;
import br.com.portalgni.cad.usuarios.core.domain.Usuario;
import br.com.portalgni.cad.usuarios.core.exception.DomainValidationException;
import br.com.portalgni.cad.usuarios.core.ports.RoleRepositoryPort;
import br.com.portalgni.cad.usuarios.core.validation.ValidationStrategy;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TipoUsuarioValidator implements ValidationStrategy<Usuario> {

    RoleRepositoryPort roleRepository;

    @Override
    public void validate(Usuario usuario) {
        for (TipoUsuario tipo : usuario.getListaTipoUsuario()) {
            if (null != tipo.getRole().getName()) {
                tipo.setRole(roleRepository.buscarRolePorNome(tipo.getRole().getName())
                        .orElseThrow(()-> new DomainValidationException("Role não encontrada para o nome informado.")));
            } else {
                throw new DomainValidationException("É necessário informar o ID ou o Nome das Roles do usuário.");
            }
        }
    }

}
