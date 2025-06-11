package br.com.portalgni.cad.usuarios.infra.converter;

import br.com.portalgni.cad.usuarios.infra.entity.TipoUsuarioEntity;
import br.com.portalgni.cad.usuarios.core.domain.TipoUsuario;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class EntityToTipoUsuarioConverter implements Converter<TipoUsuarioEntity, TipoUsuario> {

    ObjectIdToRoleEntityConverter objectIdToRoleEntity;
    EntityToRoleConverter entityToRole;

    @Override
    public TipoUsuario convert(TipoUsuarioEntity entity) {
        return new TipoUsuario(
                entityToRole.convert(Objects.requireNonNull(objectIdToRoleEntity.convert(entity.getRole()))),
                ObjectUtils.anyNull(entity.getContexto())
                        ?null
                        :entity.getContexto().toHexString()
        );
    }
}
