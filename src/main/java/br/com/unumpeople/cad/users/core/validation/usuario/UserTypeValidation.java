package br.com.portalgni.cad.usuarios.core.validation.usuario;


import br.com.portalgni.cad.usuarios.core.domain.UserRoleContext;
import br.com.portalgni.cad.usuarios.core.domain.User;
import br.com.portalgni.cad.usuarios.core.exception.DomainValidationException;
import br.com.portalgni.cad.usuarios.core.ports.RoleRepositoryPort;
import br.com.portalgni.cad.usuarios.core.validation.ValidationStrategy;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserTypeValidation implements ValidationStrategy<User> {

    RoleRepositoryPort roleRepository;

    @Override
    public void validate(User user) {
        for (UserRoleContext tipo : user.getUserRoleContextList()) {
            if (null != tipo.getRole().getName()) {
                tipo.setRole(roleRepository.getRoleByName(tipo.getRole().getName())
                        .orElseThrow(()-> new DomainValidationException("Role não encontrada para o nome informado.")));
            } else {
                throw new DomainValidationException("É necessário informar o ID ou o Nome das Roles do usuário.");
            }
        }
    }

}
