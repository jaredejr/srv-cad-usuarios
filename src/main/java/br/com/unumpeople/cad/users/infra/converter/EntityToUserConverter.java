package br.com.unumpeople.cad.users.infra.converter;

import br.com.unumpeople.cad.users.core.domain.*;
import br.com.unumpeople.cad.users.infra.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class EntityToUserConverter implements Converter<UserEntity, User> {

    ObjectIdToRoleEntityConverter objectIdToRoleEntity;
    EntityToRoleConverter entityToRole;
    EntityToAddressConverter entityToEndereco;

    @Override
    public User convert(UserEntity entity) {
        return User.reconstitute(entity.getId().toHexString(),
                entity.getName(),
                entity.getEmail(),
                entity.getPassword(),
                ObjectUtils.anyNotNull(entity.getAddressList())
                        ? entity.getAddressList()
                                .stream()
                                .map(entityToEndereco::convert)
                                .collect(Collectors.toSet())
                        : null,
                ObjectUtils.anyNotNull(entity.getDocuments())
                        ? entity.getDocuments()
                                .stream()
                                .map(documentoEntity -> new Document(
                                        documentoEntity.getNumber(),
                                        documentoEntity.getDocumentType(),
                                        documentoEntity.getIssueDate(),
                                        documentoEntity.getExpirationDate(),
                                        documentoEntity.getIssuer()))
                                .collect(Collectors.toSet())
                        : null,
                entity.getUserRoleContextList()
                        .stream()
                        .map(tipoUsuarioEntity -> new UserRoleContext(
                                entityToRole.convert(Objects.requireNonNull(
                                                objectIdToRoleEntity.convert(tipoUsuarioEntity.getRole()))),
                                ObjectUtils.anyNotNull(tipoUsuarioEntity.getContext())
                                        ? tipoUsuarioEntity.getContext().toHexString() : null))
                        .collect(Collectors.toSet()),
                entity.getCreationDate(),
                entity.getLastAccess(),
                entity.getStatus()
        );
    }
}
