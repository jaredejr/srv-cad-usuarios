package br.com.portalgni.cad.usuarios.infra.converter;

import br.com.portalgni.cad.usuarios.infra.entity.UsuarioEntity;
import br.com.portalgni.cad.usuarios.core.domain.Documento;
import br.com.portalgni.cad.usuarios.core.domain.TipoUsuario;
import br.com.portalgni.cad.usuarios.core.domain.Usuario;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class EntityToUsuarioConverter implements Converter<UsuarioEntity, Usuario> {

    ObjectIdToRoleEntityConverter objectIdToRoleEntity;
    EntityToRoleConverter entityToRole;
    EntityToEnderecoConverter entityToEndereco;

    @Override
    public Usuario convert(UsuarioEntity entity) {
        return new Usuario(entity.getId().toHexString(),
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
                                .map(documentoEntity -> new Documento(
                                        documentoEntity.getNumero(),
                                        documentoEntity.getTipoDocumento(),
                                        documentoEntity.getEmissao(),
                                        documentoEntity.getValidade(),
                                        documentoEntity.getEmissor()))
                                .collect(Collectors.toSet())
                        : null,
                entity.getTipoUsuario()
                        .stream()
                        .map(tipoUsuarioEntity -> new TipoUsuario(
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
