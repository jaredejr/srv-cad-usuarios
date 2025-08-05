package br.com.portalgni.cad.usuarios.infra.converter;

import br.com.portalgni.cad.usuarios.core.domain.*;
import br.com.portalgni.cad.usuarios.infra.entity.UsuarioEntity;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class EntityToUsuarioConverter implements Converter<UsuarioEntity, User> {

    ObjectIdToRoleEntityConverter objectIdToRoleEntity;
    EntityToRoleConverter entityToRole;
    EntityToEnderecoConverter entityToEndereco;

    @Override
    public User convert(UsuarioEntity entity) {
        return new User(entity.getId().toHexString(),
                entity.getNome(),
                entity.getEmail(),
                entity.getSenha(),
                ObjectUtils.anyNotNull(entity.getEnderecos())
                        ? entity.getEnderecos()
                                .stream()
                                .map(entityToEndereco::convert)
                                .collect(Collectors.toSet())
                        : null,
                ObjectUtils.anyNotNull(entity.getDocumentos())
                        ? entity.getDocumentos()
                                .stream()
                                .map(documentoEntity -> new Document(
                                        documentoEntity.getNumero(),
                                        documentoEntity.getTipoDocumento(),
                                        documentoEntity.getEmissao(),
                                        documentoEntity.getValidade(),
                                        documentoEntity.getEmissor()))
                                .collect(Collectors.toSet())
                        : null,
                entity.getTipoUsuario()
                        .stream()
                        .map(tipoUsuarioEntity -> new UserRoleContext(
                                entityToRole.convert(Objects.requireNonNull(
                                                objectIdToRoleEntity.convert(tipoUsuarioEntity.getRole()))),
                                ObjectUtils.anyNotNull(tipoUsuarioEntity.getContexto())
                                        ? tipoUsuarioEntity.getContexto().toHexString() : null))
                        .collect(Collectors.toSet()),
                entity.getDataCriacao(),
                entity.getUltimoAcesso(),
                entity.getStatus()
        );
    }
}
