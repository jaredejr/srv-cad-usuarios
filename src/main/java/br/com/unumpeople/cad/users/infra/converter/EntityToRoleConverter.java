package br.com.portalgni.cad.usuarios.infra.converter;

import br.com.portalgni.cad.usuarios.core.domain.Operation;
import br.com.portalgni.cad.usuarios.infra.entity.OperationEntity;
import br.com.portalgni.cad.usuarios.infra.entity.RoleEntity;
import br.com.portalgni.cad.usuarios.core.domain.Role;
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

    private static List<Operation> getOperationList(List<OperationEntity> operationEntities) {
        return operationEntities.stream().map(operationEntity -> new Operation(operationEntity.name(), operationEntity.description())).toList();
    }
}
