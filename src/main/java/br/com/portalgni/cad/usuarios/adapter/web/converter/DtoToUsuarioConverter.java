package br.com.portalgni.cad.usuarios.adapter.web.converter;

import br.com.portalgni.cad.usuarios.adapter.web.dto.UsuarioDto;
import br.com.portalgni.cad.usuarios.core.domain.Usuario;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToUsuarioConverter implements Converter<UsuarioDto, Usuario> {
    @Override
    public Usuario convert(UsuarioDto dto) {
        return new Usuario(dto.getId(),
                dto.getNome(),
                dto.getEmail(),
                dto.getSenha(),
                dto.getEnderecos(),
                dto.getDocumentos(),
                dto.getListaTipoUsuario(),
                dto.getDataCriacao(),
                dto.getUltimoAcesso(),
                dto.getStatus());
    }
}
