package br.com.portalgni.cad.usuarios.core.service.validation.usuario;

import br.com.portalgni.cad.usuarios.core.domain.Role;
import br.com.portalgni.cad.usuarios.core.domain.Usuario;
import br.com.portalgni.cad.usuarios.core.ports.RoleRepositoryPort;
import br.com.portalgni.cad.usuarios.core.ports.UsuarioRepositoryPort;
import br.com.portalgni.cad.usuarios.core.service.validation.Validation;
import br.com.portalgni.cad.usuarios.core.service.validation.util.Utils;
import lombok.AllArgsConstructor;

import javax.management.InvalidAttributeValueException;
import java.util.Optional;

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
