package br.com.portalgni.cad.usuarios.core.validation.usuario;

import br.com.portalgni.cad.usuarios.core.domain.Usuario;
import br.com.portalgni.cad.usuarios.core.ports.UsuarioRepositoryPort;
import br.com.portalgni.cad.usuarios.core.validation.Validation;
import br.com.portalgni.cad.usuarios.core.validation.util.Utils;
import lombok.AllArgsConstructor;

import javax.management.InvalidAttributeValueException;

@AllArgsConstructor
public class UsuarioIdValidator implements Validation<String, Usuario> {

    UsuarioRepositoryPort usuarioRepository;

    @Override
    public Usuario validate(String id) throws InvalidAttributeValueException {
        if (Boolean.FALSE.equals(Utils.isValidHex(id)))
            throw new InvalidAttributeValueException("O formato do id do Usuário é inválido.");
        return findAndThrowIfNotFound(id);
    }

    private Usuario findAndThrowIfNotFound(String id) throws InvalidAttributeValueException {
        return usuarioRepository.buscarUsuarioPorId(id)
                .orElseThrow(()-> new InvalidAttributeValueException("Usuário não encontrado"));
    }
}
