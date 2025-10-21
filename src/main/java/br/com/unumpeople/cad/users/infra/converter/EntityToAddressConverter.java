package br.com.unumpeople.cad.users.infra.converter;

import br.com.unumpeople.cad.users.infra.entity.AddressEntity;
import br.com.unumpeople.cad.users.core.domain.Address;
import org.bson.types.ObjectId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntityToAddressConverter implements Converter<AddressEntity, Address> {

    @Override
    public Address convert(AddressEntity address) {
        return new Address(
                address.id().toHexString(),
                address.addressType(),
                address.street(),
                address.number(),
                address.addressComplement(),
                address.neighborhood(),
                address.city(),
                address.state(),
                address.zipCode()
        );
    }
}
