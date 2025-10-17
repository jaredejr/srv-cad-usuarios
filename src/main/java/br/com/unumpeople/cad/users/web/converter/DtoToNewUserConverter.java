package br.com.unumpeople.cad.users.web.converter;

import br.com.unumpeople.cad.users.core.domain.User;
import br.com.unumpeople.cad.users.web.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToNewUserConverter implements Converter<UserDto, User> {
    @Override
    public User convert(UserDto dto) {
        return new User(dto.name(),
                dto.email(),
                dto.addresses(),
                dto.documents(),
                dto.userRoleContextList(),
                dto.status());
    }
}
