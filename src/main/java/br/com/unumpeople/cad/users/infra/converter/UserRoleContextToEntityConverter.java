package br.com.unumpeople.cad.users.infra.converter;

import br.com.unumpeople.cad.users.infra.entity.UserRoleContextEntity;
import br.com.unumpeople.cad.users.core.domain.UserRoleContext;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserRoleContextToEntityConverter implements Converter<UserRoleContext, UserRoleContextEntity> {

    @Override
    public UserRoleContextEntity convert(UserRoleContext userRoleContext) {
        return new UserRoleContextEntity(
                new ObjectId(userRoleContext.getRole().getId()),
                userRoleContext.getContext()!=null?
                        new ObjectId(userRoleContext.getContext())
                        :null
        );
    }
}
