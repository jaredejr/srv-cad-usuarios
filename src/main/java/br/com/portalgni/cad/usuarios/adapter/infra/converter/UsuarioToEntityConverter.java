package br.com.portalgni.cad.usuarios.adapter.infra.converter;

import br.com.portalgni.cad.usuarios.adapter.infra.entity.DocumentoEntity;
import br.com.portalgni.cad.usuarios.adapter.infra.entity.TipoUsuarioEntity;
import br.com.portalgni.cad.usuarios.adapter.infra.entity.UsuarioEntity;
import br.com.portalgni.cad.usuarios.core.domain.Usuario;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UsuarioToEntityConverter implements Converter<Usuario, UsuarioEntity> {

    @Override
    public UsuarioEntity convert(Usuario usuario) {
        return new UsuarioEntity(
                new ObjectId(usuario.getId()),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getDocumentos().stream().map(documento ->
                        new DocumentoEntity(documento.getNumero(),
                                documento.getTipoDocumento(),
                                documento.getEmissao(),
                                documento.getValidade(),
                                documento.getEmissor())).collect(Collectors.toSet()),
                usuario.getListaTipoUsuario().stream().map(tipoUsuario ->
                        new TipoUsuarioEntity(
                                new ObjectId(tipoUsuario.getRole().getId()),
                                new ObjectId(tipoUsuario.getContexto()))).collect(Collectors.toSet()),
                usuario.getDataCriacao(),
                usuario.getUltimoAcesso(),
                usuario.getStatus()
        );
    }
}
