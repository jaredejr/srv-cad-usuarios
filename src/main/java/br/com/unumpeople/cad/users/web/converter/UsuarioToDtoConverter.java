package br.com.unumpeople.cad.users.web.converter;

import br.com.unumpeople.cad.users.core.domain.User;
import br.com.unumpeople.cad.users.web.dto.UsuarioDto;
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
