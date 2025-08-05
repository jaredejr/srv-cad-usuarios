package br.com.unumpeople.cad.users.web.converter;

import br.com.unumpeople.cad.users.core.domain.User;
import br.com.unumpeople.cad.users.web.dto.UsuarioDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToNewUsuarioConverter implements Converter<UsuarioDto, User> {
    @Override
    public User convert(UsuarioDto dto) {
        return new User(dto.nome(),
                dto.email(),
                dto.addresses(),
                dto.documents(),
                dto.listaUserRoleContext(),
                dto.status());
    }
}
