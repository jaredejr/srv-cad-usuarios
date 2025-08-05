package br.com.portalgni.cad.usuarios.infra.converter;

import br.com.portalgni.cad.usuarios.infra.entity.TipoUsuarioEntity;
import br.com.portalgni.cad.usuarios.core.domain.UserRoleContext;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TipoUsuarioToEntityConverter implements Converter<UserRoleContext, TipoUsuarioEntity> {

    @Override
    public TipoUsuarioEntity convert(UserRoleContext userRoleContext) {
        return new TipoUsuarioEntity(
                new ObjectId(userRoleContext.getRole().getId()),
                userRoleContext.getContext()!=null?
                        new ObjectId(userRoleContext.getContext())
                        :null
        );
    }
}
