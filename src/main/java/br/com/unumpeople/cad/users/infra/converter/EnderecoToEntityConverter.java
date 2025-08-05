package br.com.portalgni.cad.usuarios.infra.converter;

import br.com.portalgni.cad.usuarios.core.domain.Address;
import br.com.portalgni.cad.usuarios.infra.entity.EnderecoEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EnderecoToEntityConverter implements Converter<Address, EnderecoEntity> {

    @Override
    public EnderecoEntity convert(Address address) {
        return new EnderecoEntity(
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
