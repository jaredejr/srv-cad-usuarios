package br.com.unumpeople.cad.users.config.auth;

import br.com.unumpeople.cad.users.core.domain.User;
import br.com.unumpeople.cad.users.core.ports.UserServicePort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserServicePort usuarioService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Irá buscar o usuário pelo username(email): ".concat(email));
        User user = null;
        user = usuarioService.getUserByEmail(email);
        usuarioService.updateLastAccess(user);
        return new br.com.unumpeople.cad.users.config.auth.UserDetails(user);
    }
}
