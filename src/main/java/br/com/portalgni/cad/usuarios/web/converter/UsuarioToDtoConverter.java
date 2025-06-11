package br.com.portalgni.cad.usuarios.web.converter;

import br.com.portalgni.cad.usuarios.web.dto.UsuarioDto;
import br.com.portalgni.cad.usuarios.core.domain.Usuario;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UsuarioToDtoConverter implements Converter<Usuario, UsuarioDto> {
    @Override
    public UsuarioDto convert(Usuario usuario) {
        return new UsuarioDto(usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getEnderecos(),
                usuario.getDocumentos(),
                usuario.getListaTipoUsuario(),
                usuario.getDataCriacao(),
                usuario.getUltimoAcesso(),
                usuario.getStatus());
    }
}
