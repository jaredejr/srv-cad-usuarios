package br.com.unumpeople.cad.users.infra.converter;

import br.com.unumpeople.cad.users.core.domain.Operation;
import br.com.unumpeople.cad.users.infra.entity.RoleEntity;
import br.com.unumpeople.cad.users.core.domain.Role;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleToEntityConverter implements Converter<Role, RoleEntity> {
    @Override
    public RoleEntity convert(Role role) {
        return new RoleEntity(
                (role.getId()!=null) ? new ObjectId(role.getId()) : null,
                role.getName(),
                role.getDescription(),
                getOperations(role.getOperations())
        );
    }

    private static List<String> getOperations(List<Operation> operations) {
        return operations.stream().map(Operation::getDescription).toList();
    }
}
