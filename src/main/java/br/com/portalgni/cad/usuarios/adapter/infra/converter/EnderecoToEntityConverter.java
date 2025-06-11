package br.com.portalgni.cad.usuarios.adapter.infra.converter;

import br.com.portalgni.cad.usuarios.core.domain.Endereco;
import br.com.portalgni.cad.usuarios.adapter.infra.entity.EnderecoEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EnderecoToEntityConverter implements Converter<Endereco, EnderecoEntity> {

    @Override
    public EnderecoEntity convert(Endereco endereco) {
        return new EnderecoEntity(
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
