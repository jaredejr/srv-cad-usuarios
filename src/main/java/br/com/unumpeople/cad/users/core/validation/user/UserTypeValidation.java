package br.com.unumpeople.cad.users.core.validation.user;


import br.com.unumpeople.cad.users.core.domain.UserRoleContext;
import br.com.unumpeople.cad.users.core.domain.User;
import br.com.unumpeople.cad.users.core.exception.DomainValidationException;
import br.com.unumpeople.cad.users.core.validation.ValidationStrategy;
import br.com.unumpeople.cad.users.core.ports.RoleRepositoryPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserTypeValidation implements ValidationStrategy<User> {

    RoleRepositoryPort roleRepository;

    @Override
    public void validate(User user) {
        for (UserRoleContext tipo : user.getUserRoleContextList()) {
            if (null != tipo.getRole().getName()) {
                tipo.setRole(roleRepository.getRoleByName(tipo.getRole().getName())
                        .orElseThrow(()-> new DomainValidationException("Role não encontrada para o name informado.")));
            } else {
                throw new DomainValidationException("É necessário informar o ID ou o Nome das Roles do usuário.");
            }
        }
    }

}
