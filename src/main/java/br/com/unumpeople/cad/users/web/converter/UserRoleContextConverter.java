package br.com.unumpeople.cad.users.web.converter;

import br.com.unumpeople.cad.users.core.domain.UserRoleContext;
import br.com.unumpeople.cad.users.core.ports.RoleServicePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserRoleContextConverter {

    private final RoleServicePort roleService;

    public UserRoleContext convert(String roleName, String context) {
        return new UserRoleContext(roleService.getRoleByName(roleName), context);
    }
}
