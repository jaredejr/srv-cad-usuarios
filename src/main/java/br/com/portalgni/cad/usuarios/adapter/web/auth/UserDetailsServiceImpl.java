package br.com.portalgni.cad.usuarios.adapter.web.auth;

import br.com.portalgni.cad.usuarios.core.domain.Usuario;
import br.com.portalgni.cad.usuarios.core.ports.UsuarioServicePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;

@Slf4j
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioServicePort usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Irá buscar o usuário pelo username(email): ".concat(username));
        Usuario usuario = null;
        try {
            usuario = usuarioService.buscarUsuarioPorEmail(username);
        } catch (InvalidAttributeValueException e) {
            log.error("Usuário não encontrado: ".concat(username));
            log.error(e.getMessage());
            throw new UsernameNotFoundException(e.getMessage());
        }
        return new UsuarioDetails(usuario);
    }
}
