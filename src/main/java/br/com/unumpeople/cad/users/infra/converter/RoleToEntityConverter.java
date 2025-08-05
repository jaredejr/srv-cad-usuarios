package br.com.portalgni.cad.usuarios.infra.converter;

import br.com.portalgni.cad.usuarios.core.domain.Operation;
import br.com.portalgni.cad.usuarios.infra.entity.OperationEntity;
import br.com.portalgni.cad.usuarios.infra.entity.RoleEntity;
import br.com.portalgni.cad.usuarios.core.domain.Role;
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

    private static List<OperationEntity> getOperations(List<Operation> operations) {
        return operations.stream().map(operation -> new OperationEntity(operation.getName(), operation.getDescription())).toList();
    }
}
