package br.com.portalgni.cad.usuarios.infra.converter;

import br.com.portalgni.cad.usuarios.infra.entity.TipoUsuarioEntity;
import br.com.portalgni.cad.usuarios.core.domain.TipoUsuario;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TipoUsuarioToEntityConverter implements Converter<TipoUsuario, TipoUsuarioEntity> {

    @Override
    public TipoUsuarioEntity convert(TipoUsuario tipoUsuario) {
        return new TipoUsuarioEntity(
                new ObjectId(tipoUsuario.getRole().getId()),
                tipoUsuario.getContexto()!=null?
                        new ObjectId(tipoUsuario.getContexto())
                        :null
        );
    }
}
