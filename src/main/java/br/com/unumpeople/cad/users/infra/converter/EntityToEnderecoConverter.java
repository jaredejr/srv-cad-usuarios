package br.com.unumpeople.cad.users.infra.converter;

import br.com.unumpeople.cad.users.infra.entity.EnderecoEntity;
import br.com.unumpeople.cad.users.core.domain.Address;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntityToEnderecoConverter implements Converter<EnderecoEntity, Address> {

    @Override
    public Address convert(EnderecoEntity endereco) {
        return new Address(
                endereco.getTipo(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado(),
                endereco.getCep()
        );
    }
}
