package br.com.portalgni.cad.usuarios.web.converter;

import br.com.portalgni.cad.usuarios.core.domain.Operation;
import br.com.portalgni.cad.usuarios.core.domain.Role;
import br.com.portalgni.cad.usuarios.web.dto.RoleDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToNewRoleConverter implements Converter<RoleDto, Role> {
    @Override
    public Role convert(RoleDto roleDto) {
        return new Role(
                roleDto.name(),
                roleDto.description(),
                roleDto.operations().stream().map(operationDto -> new Operation(operationDto.name(), operationDto.description())).toList()
        );
    }
}
