package br.com.portalgni.cad.usuarios.infra.converter;

import br.com.portalgni.cad.usuarios.infra.entity.TipoUsuarioEntity;
import br.com.portalgni.cad.usuarios.core.domain.UserRoleContext;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class EntityToTipoUsuarioConverter implements Converter<TipoUsuarioEntity, UserRoleContext> {

    ObjectIdToRoleEntityConverter objectIdToRoleEntity;
    EntityToRoleConverter entityToRole;

    @Override
    public UserRoleContext convert(TipoUsuarioEntity entity) {
        return new UserRoleContext(
                entityToRole.convert(Objects.requireNonNull(objectIdToRoleEntity.convert(entity.getRole()))),
                ObjectUtils.anyNull(entity.getContexto())
                        ?null
                        :entity.getContexto().toHexString()
        );
    }
}
