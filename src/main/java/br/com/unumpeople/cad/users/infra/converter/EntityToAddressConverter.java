package br.com.unumpeople.cad.users.infra.converter;

import br.com.unumpeople.cad.users.infra.entity.AddressEntity;
import br.com.unumpeople.cad.users.core.domain.Address;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntityToAddressConverter implements Converter<AddressEntity, Address> {

    @Override
    public Address convert(AddressEntity endereco) {
        return new Address(
                endereco.addressType(),
                endereco.street(),
                endereco.number(),
                endereco.addressComplement(),
                endereco.neighborhood(),
                endereco.city(),
                endereco.state(),
                endereco.zipCode()
        );
    }
}
