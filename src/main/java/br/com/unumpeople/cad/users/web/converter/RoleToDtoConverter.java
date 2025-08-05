package br.com.portalgni.cad.usuarios.web.converter;

import br.com.portalgni.cad.usuarios.core.domain.Operation;
import br.com.portalgni.cad.usuarios.web.dto.OperationDto;
import br.com.portalgni.cad.usuarios.web.dto.RoleDto;
import br.com.portalgni.cad.usuarios.core.domain.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleToDtoConverter implements Converter<Role, RoleDto> {
    @Override
    public RoleDto convert(Role role) {
        return new RoleDto(role.getId(),
                role.getName(),
                role.getDescription(),
                getOperationDtoList(role.getOperations())
        );
    }

    private static List<OperationDto> getOperationDtoList(List<Operation> operations) {
        return operations.stream().map(operation -> new OperationDto(operation.getName(), operation.getDescription())).toList();
    }
}
