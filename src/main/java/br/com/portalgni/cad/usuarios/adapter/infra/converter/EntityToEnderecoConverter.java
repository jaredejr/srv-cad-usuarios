package br.com.portalgni.cad.usuarios.adapter.infra.converter;

import br.com.portalgni.cad.usuarios.adapter.infra.entity.EnderecoEntity;
import br.com.portalgni.cad.usuarios.core.domain.Endereco;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EntityToEnderecoConverter implements Converter<EnderecoEntity, Endereco> {

    @Override
    public Endereco convert(EnderecoEntity endereco) {
        return new Endereco(
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
