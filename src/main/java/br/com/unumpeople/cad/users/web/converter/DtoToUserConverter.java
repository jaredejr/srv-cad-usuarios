package br.com.unumpeople.cad.users.web.converter;

import br.com.unumpeople.cad.users.core.domain.User;
import br.com.unumpeople.cad.users.web.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToUserConverter implements Converter<UserDto, User> {
    @Override
    public User convert(UserDto dto) {
        return new User(dto.id(),
                dto.name(),
                dto.email(),
                null,
                dto.addresses(),
                dto.documents(),
                dto.userRoleContextList(),
                dto.creationDate(),
                dto.lastAccess(),
                dto.status());
    }
}
