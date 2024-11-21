package br.com.portalgni.cad.usuarios.core.validation.usuario;

import br.com.portalgni.cad.usuarios.core.domain.TipoUsuario;
import br.com.portalgni.cad.usuarios.core.domain.Usuario;
import br.com.portalgni.cad.usuarios.core.ports.UsuarioRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.management.InvalidAttributeValueException;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
public class UpdateContextValidator {

    private final UsuarioRepositoryPort usuarioRepository;

    private static final Map<String, String> AUTHORIZED_RELATIONS = Map.of(
            "SITE_ADMIN","USUARIO_SITE");

    public Boolean validate(String requesterId, String editedUserId) throws InvalidAttributeValueException {

        if (requesterId.equals(editedUserId)) return Boolean.TRUE;

        Usuario requester = usuarioRepository.buscarUsuarioPorId(requesterId).orElseThrow();
        Usuario editedUser = usuarioRepository.buscarUsuarioPorId(editedUserId).orElseThrow();

        for (String key: AUTHORIZED_RELATIONS.keySet()){
            List<String> requesterContext = requester.getListaTipoUsuario()
                    .stream()
                    .filter(tipoUsuario -> tipoUsuario.getRole().getNome().equals(key))
                    .map(TipoUsuario::getContexto).toList();

            List<String> editedUserContext = editedUser.getListaTipoUsuario()
                    .stream()
                    .filter(tipoUsuario -> tipoUsuario.getRole().getNome().equals(AUTHORIZED_RELATIONS.get(key)))
                    .map(TipoUsuario::getContexto).toList();

            if (requesterContext.stream().anyMatch(editedUserContext::contains)) return Boolean.TRUE;
        }
        return Boolean.FALSE;

    }
}
