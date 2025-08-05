package br.com.unumpeople.cad.users.infra.converter;

import br.com.unumpeople.cad.users.infra.entity.TipoUsuarioEntity;
import br.com.unumpeople.cad.users.core.domain.UserRoleContext;
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
                null==entity.getContexto()
                        ?null
                        :entity.getContexto().toHexString()
        );
    }
}
