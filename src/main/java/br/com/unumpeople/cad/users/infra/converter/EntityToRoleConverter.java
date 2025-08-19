package br.com.unumpeople.cad.users.infra.converter;

import br.com.unumpeople.cad.users.core.domain.Operation;
import br.com.unumpeople.cad.users.infra.entity.RoleEntity;
import br.com.unumpeople.cad.users.core.domain.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EntityToRoleConverter implements Converter<RoleEntity, Role> {
    @Override
    public Role convert(RoleEntity roleEntity) {
        return new Role(
                roleEntity.id().toHexString(),
                roleEntity.name(),
                roleEntity.description(),
                getOperationList(roleEntity.operations())
        );
    }

    private static List<Operation> getOperationList(List<String> operationEntities) {
        return operationEntities.stream().map(Operation::new).toList();
    }
}
