package br.com.portalgni.cad.usuarios.adapter.infra.converter;

import br.com.portalgni.cad.usuarios.adapter.infra.entity.DocumentoEntity;
import br.com.portalgni.cad.usuarios.adapter.infra.entity.TipoUsuarioEntity;
import br.com.portalgni.cad.usuarios.adapter.infra.entity.UsuarioEntity;
import br.com.portalgni.cad.usuarios.core.domain.Usuario;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UsuarioToEntityConverter implements Converter<Usuario, UsuarioEntity> {

    EnderecoToEntityConverter enderecoToEntity;

    @Override
    public UsuarioEntity convert(Usuario usuario) {
        return new UsuarioEntity(
                usuario.getId()!=null?
                        new ObjectId(usuario.getId())
                        :null,
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                ObjectUtils.anyNotNull(usuario.getEnderecos())
                        ? usuario.getEnderecos().stream()
                                .map(enderecoToEntity::convert)
                                .collect(Collectors.toSet())
                        : null,
                ObjectUtils.anyNotNull(usuario.getDocumentos())
                        ? usuario.getDocumentos()
                                .stream()
                                .map(documento -> new DocumentoEntity(
                                        documento.getNumero(),
                                        documento.getTipoDocumento(),
                                        documento.getEmissao(),
                                        documento.getValidade(),
                                        documento.getEmissor()))
                                .collect(Collectors.toSet())
                        : null,
                usuario.getListaTipoUsuario()
                        .stream()
                        .map(tipoUsuario -> new TipoUsuarioEntity(
                                new ObjectId(tipoUsuario.getRole().getId()),
                                ObjectUtils.anyNotNull(tipoUsuario.getContexto())
                                        ? new ObjectId(tipoUsuario.getContexto()) :null))
                        .collect(Collectors.toSet()),
                usuario.getDataCriacao(),
                usuario.getUltimoAcesso(),
                usuario.getStatus()
        );
    }
}
