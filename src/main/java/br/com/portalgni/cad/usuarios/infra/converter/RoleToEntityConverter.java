package br.com.portalgni.cad.usuarios.infra.converter;

import br.com.portalgni.cad.usuarios.infra.entity.RoleEntity;
import br.com.portalgni.cad.usuarios.core.domain.Role;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleToEntityConverter implements Converter<Role, RoleEntity> {
    @Override
    public RoleEntity convert(Role role) {
        return new RoleEntity(
                (role.getId()!=null) ? new ObjectId(role.getId()) : null,
                role.getNome(),
                role.getDescricao()
        );
    }
}
