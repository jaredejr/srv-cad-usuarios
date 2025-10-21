package br.com.unumpeople.cad.users.web.converter;

import br.com.unumpeople.cad.users.core.domain.Operation;
import br.com.unumpeople.cad.users.core.domain.Role;
import br.com.unumpeople.cad.users.web.dto.RoleRequestDto;
import br.com.unumpeople.cad.users.web.dto.RoleResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleConverter {
    public Role toDomain(RoleRequestDto roleDto) {
        return Role.builder()
                .setName(roleDto.name())
                .setDescription(roleDto.description())
                .setOperations(getOperations(roleDto.operations()))
                .build();
    }

    public RoleResponseDto toResponseDto(Role role) {
        return new RoleResponseDto(role.getId(), role.getName(), role.getDescription(), role.getOperations());
    }

    private List<Operation> getOperations(List<String> operations) {
        return operations.stream().map(Operation::new).toList();
    }
}
