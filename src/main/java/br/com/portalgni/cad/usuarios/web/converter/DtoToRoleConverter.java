package br.com.portalgni.cad.usuarios.web.converter;

import br.com.portalgni.cad.usuarios.web.dto.RoleDto;
import br.com.portalgni.cad.usuarios.core.domain.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToRoleConverter implements Converter<RoleDto, Role> {
    @Override
    public Role convert(RoleDto role) {
        return new Role(
                role.getId(),
                role.getNome(),
                role.getDescricao()
        );
    }
}
