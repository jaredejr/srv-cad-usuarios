package br.com.portalgni.cad.usuarios.infra.converter;

import br.com.portalgni.cad.usuarios.infra.entity.RoleEntity;
import br.com.portalgni.cad.usuarios.core.domain.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntityToRoleConverter implements Converter<RoleEntity, Role> {
    @Override
    public Role convert(RoleEntity role) {
        return new Role(
                role.getId().toHexString(),
                role.getNome(),
                role.getDescricao()
        );
    }
}
