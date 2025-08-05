package br.com.unumpeople.cad.users.web.auth;

import br.com.unumpeople.cad.users.core.domain.Operation;
import br.com.unumpeople.cad.users.core.domain.UserRoleContext;
import br.com.unumpeople.cad.users.core.domain.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class UsuarioDetails implements UserDetails {

    final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        log.info("Entrei no metodo getAuthorities()");

        Set<Operation> operations = user.getUserRoleContextList().stream()
                .map(UserRoleContext::getRole)
                .flatMap(role -> role.getOperations().stream())
                .collect(Collectors.toSet());

        for (Operation operation : operations) {
            authorities.add(new SimpleGrantedAuthority(operation.getName()));
            log.info("Adicionando role ao usuário: ".concat(operation.getName()));
        }

        return authorities;
    }

    public Map<String, String >getContextMap(){
        return user.getUserRoleContextList()
                .stream()
                .collect(Collectors.toMap(
                        tipoUsuario -> tipoUsuario.getRole().getName(),
                        tipoUsuario -> null == tipoUsuario.getContext() ? "" : tipoUsuario.getContext()));
    }

    public String getUserId(){
        return user.getId();
    }

    @Override
    public String getPassword() {
        return user.getPassword().getValue();
    }

    @Override
    public String getUsername() {
        return user.getEmail().getValue();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
