package br.com.portalgni.cad.usuarios.core.validation.usuario;


import br.com.portalgni.cad.usuarios.core.domain.TipoUsuario;
import br.com.portalgni.cad.usuarios.core.domain.Usuario;
import br.com.portalgni.cad.usuarios.core.ports.RoleRepositoryPort;
import br.com.portalgni.cad.usuarios.core.validation.Validation;
import br.com.portalgni.cad.usuarios.core.validation.role.RoleIdValidator;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

import javax.management.InvalidAttributeValueException;

@AllArgsConstructor
public class TipoUsuarioValidator implements Validation<Usuario, Usuario> {

    RoleRepositoryPort roleRepository;
    RoleIdValidator roleIdValidator;

    @Override
    public Usuario validate(Usuario usuario) throws InvalidAttributeValueException {
        for (TipoUsuario tipo : usuario.getListaTipoUsuario()) {
            if (ObjectUtils.allNotNull(tipo.getRole().getId())){
                tipo.setRole(roleIdValidator.validate(tipo.getRole().getId()));
            } else if (ObjectUtils.allNotNull(tipo.getRole().getNome())) {
                tipo.setRole(roleRepository.buscarRolePorNome(tipo.getRole().getNome())
                        .orElseThrow(()-> new InvalidAttributeValueException("Role não encontrada para o nome informado.")));
            } else {
                throw new InvalidAttributeValueException("É necessário informar o ID ou o Nome das Roles do usuário.");
            }
        }
        return usuario;
    }

}
