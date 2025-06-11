package br.com.portalgni.cad.usuarios.infra.converter;

import br.com.portalgni.cad.usuarios.infra.repository.RolesRepository;
import br.com.portalgni.cad.usuarios.infra.entity.RoleEntity;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

@Component
@ReadingConverter
@AllArgsConstructor
public class ObjectIdToRoleEntityConverter implements Converter<ObjectId, RoleEntity> {

    private final RolesRepository rolesRepository;

    @Override
    public RoleEntity convert(ObjectId objectId) {
        return rolesRepository.findById(objectId).orElse(null);
    }
}
