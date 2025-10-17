package br.com.unumpeople.cad.users.web.converter;

import br.com.unumpeople.cad.users.core.domain.User;
import br.com.unumpeople.cad.users.web.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToDtoConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User user) {
        return new UserDto(user.getId(),
                user.getName(),
                user.getEmail().getValue(),
                user.getPassword().getValue(),
                user.getAddressList(),
                user.getDocuments(),
                user.getUserRoleContextList(),
                user.getCreationDate(),
                user.getLastAccess(),
                user.getStatus().getValue());
    }
}
