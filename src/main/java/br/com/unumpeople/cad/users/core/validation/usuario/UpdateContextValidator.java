package br.com.unumpeople.cad.users.core.validation.usuario;

import br.com.unumpeople.cad.users.core.domain.Operation;
import br.com.unumpeople.cad.users.core.domain.UserRoleContext;
import br.com.unumpeople.cad.users.core.domain.User;
import br.com.unumpeople.cad.users.core.ports.UserRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
public class UpdateContextValidator {

    private final UserRepositoryPort usuarioRepository;

    private static final Map<String, List<String>> AUTHORIZED_RELATIONS = Map.of(
            "SITE_ADMIN",List.of("USUARIO_SITE","SITE_ADMIN"));

    public Boolean validate(String requesterId, String editedUserId) {

        if (isUserEditingHimself(requesterId, editedUserId)) return Boolean.TRUE;

        User requester = usuarioRepository.buscarUsuarioPorId(requesterId).orElseThrow();
        if (isUserSystemAdmin(requester)) return Boolean.TRUE;

        User editedUser = usuarioRepository.buscarUsuarioPorId(editedUserId).orElseThrow();

        for (String key: AUTHORIZED_RELATIONS.keySet()){
            List<String> requesterContext = requester.getUserRoleContextList()
                    .stream()
                    .filter(tipoUsuario -> tipoUsuario.getRole().getName().equals(key))
                    .map(UserRoleContext::getContext).toList();

            List<String> editedUserContext = editedUser.getUserRoleContextList()
                    .stream()
                    .filter(tipoUsuario -> AUTHORIZED_RELATIONS.get(key).contains(tipoUsuario.getRole().getName()))
                    .map(UserRoleContext::getContext).toList();

            if (requesterContext.stream().anyMatch(editedUserContext::contains)) return Boolean.TRUE;
        }
        return Boolean.FALSE;

    }

    private Boolean isUserEditingHimself(String requesterId, String editedUserId){
        if (requesterId.equals(editedUserId)) return Boolean.TRUE;
        return Boolean.FALSE;
    }

    private Boolean isUserSystemAdmin(User requester) {
        if (requester.getUserRoleContextList().stream().anyMatch(tipoUsuario -> tipoUsuario.getRole().getName().equals("SYSTEM_ADMIN"))){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    private Boolean isEditingInTheSameContex(User requester, User editedUser, String operation){
        List<String> editorContextList = requester.getUserRoleContextList()
                .stream()
                .filter(tipoUsuario -> tipoUsuario.getRole().getOperations().stream().map(Operation::getName).toList().contains(operation))
                .map(UserRoleContext::getContext).toList();

        for (String editorContext: editorContextList) {
            if (editedUser.getUserRoleContextList().stream()
                    .anyMatch(tipoUsuario -> tipoUsuario.getContext().equals(editorContext))) {
                return Boolean.TRUE;
            }
        }


        return Boolean.FALSE;
    }

}
