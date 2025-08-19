package br.com.unumpeople.cad.users.infra.converter;

import br.com.unumpeople.cad.users.core.domain.Address;
import br.com.unumpeople.cad.users.infra.entity.AddressEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressToEntityConverter implements Converter<Address, AddressEntity> {

    @Override
    public AddressEntity convert(Address address) {
        return new AddressEntity(
                address.getAddressType(),
                address.getStreet(),
                address.getNumber(),
                address.getAddressComplement(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getZipCode()
        );
    }
}
