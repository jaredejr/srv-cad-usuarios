package br.com.unumpeople.cad.users.web.converter;

import br.com.unumpeople.cad.users.core.domain.Operation;
import br.com.unumpeople.cad.users.web.dto.RoleDto;
import br.com.unumpeople.cad.users.core.domain.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToRoleConverter implements Converter<RoleDto, Role> {
    @Override
    public Role convert(RoleDto role) {
        return new Role(
                role.id(),
                role.name(),
                role.description(),
                role.operations().stream().map(operation -> new Operation(operation.name(), operation.description())).toList()
        );
    }
}
