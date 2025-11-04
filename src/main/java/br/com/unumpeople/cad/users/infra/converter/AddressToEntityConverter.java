package br.com.unumpeople.cad.users.infra.converter;

import br.com.unumpeople.cad.users.core.domain.Address;
import br.com.unumpeople.cad.users.infra.entity.AddressEntity;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressToEntityConverter implements Converter<Address, AddressEntity> {

    @Override
    public AddressEntity convert(Address address) {
        return new AddressEntity(
                address.getId() != null ? new ObjectId(address.getId()) : new ObjectId(),
                address.getDescription(),
                address.getAddressType(),
                address.getStreet(),
                address.getNumber(),
                address.getAddressComplement(),
                address.getNeighborhood(),
                address.getCity(),
                address.getState(),
                address.getZipCode(),
                address.getCountry()
        );
    }
}
