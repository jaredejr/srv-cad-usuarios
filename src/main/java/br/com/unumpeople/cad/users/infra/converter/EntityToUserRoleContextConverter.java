package br.com.unumpeople.cad.users.infra.converter;

import br.com.unumpeople.cad.users.infra.entity.UserRoleContextEntity;
import br.com.unumpeople.cad.users.core.domain.UserRoleContext;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class EntityToUserRoleContextConverter implements Converter<UserRoleContextEntity, UserRoleContext> {

    ObjectIdToRoleEntityConverter objectIdToRoleEntity;
    EntityToRoleConverter entityToRole;

    @Override
    public UserRoleContext convert(UserRoleContextEntity entity) {
        return new UserRoleContext(
                entityToRole.convert(Objects.requireNonNull(objectIdToRoleEntity.convert(entity.getRole()))),
                null==entity.getContext()
                        ?null
                        :entity.getContext().toHexString()
        );
    }
}
