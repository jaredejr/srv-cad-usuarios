package br.com.portalgni.cad.usuarios.adapter.web.converter;

import br.com.portalgni.cad.usuarios.adapter.web.dto.RoleDto;
import br.com.portalgni.cad.usuarios.core.domain.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleToDtoConverter implements Converter<Role, RoleDto> {
    @Override
    public RoleDto convert(Role role) {
        return new RoleDto(
                role.getId(),
                role.getNome(),
                role.getDescricao()
        );
    }
}
