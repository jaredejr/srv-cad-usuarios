package br.com.portalgni.cad.usuarios.web.converter;

import br.com.portalgni.cad.usuarios.core.domain.User;
import br.com.portalgni.cad.usuarios.web.dto.UsuarioDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UsuarioToDtoConverter implements Converter<User, UsuarioDto> {
    @Override
    public UsuarioDto convert(User user) {
        return new UsuarioDto(user.getId(),
                user.getName(),
                user.getEmail().getValue(),
                user.getAddressList(),
                user.getDocuments(),
                user.getUserRoleContextList(),
                user.getCreationDate(),
                user.getLastAccess(),
                user.getStatus().getValue());
    }
}
