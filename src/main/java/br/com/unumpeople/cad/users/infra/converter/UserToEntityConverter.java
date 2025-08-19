package br.com.unumpeople.cad.users.infra.converter;

import br.com.unumpeople.cad.users.infra.entity.DocumentEntity;
import br.com.unumpeople.cad.users.infra.entity.UserRoleContextEntity;
import br.com.unumpeople.cad.users.infra.entity.UserEntity;
import br.com.unumpeople.cad.users.core.domain.User;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserToEntityConverter implements Converter<User, UserEntity> {

    AddressToEntityConverter enderecoToEntity;

    @Override
    public UserEntity convert(User user) {
        return new UserEntity(
                user.getId()!=null?
                        new ObjectId(user.getId())
                        :null,
                user.getName(),
                user.getEmail().getValue(),
                user.getPassword().getValue(),
                ObjectUtils.anyNotNull(user.getAddressList())
                        ? user.getAddressList().stream()
                                .map(enderecoToEntity::convert)
                                .collect(Collectors.toSet())
                        : null,
                ObjectUtils.anyNotNull(user.getDocuments())
                        ? user.getDocuments()
                                .stream()
                                .map(documento -> new DocumentEntity(
                                        documento.getNumber(),
                                        documento.getDocumentType(),
                                        documento.getIssueDate(),
                                        documento.getExpirationDate(),
                                        documento.getIssuer()))
                                .collect(Collectors.toSet())
                        : null,
                user.getUserRoleContextList()
                        .stream()
                        .map(tipoUsuario -> new UserRoleContextEntity(
                                new ObjectId(tipoUsuario.getRole().getId()),
                                ObjectUtils.anyNotNull(tipoUsuario.getContext())
                                        ? new ObjectId(tipoUsuario.getContext()) :null))
                        .collect(Collectors.toSet()),
                user.getCreationDate(),
                user.getLastAccess(),
                user.getStatus().getValue()
        );
    }
}
