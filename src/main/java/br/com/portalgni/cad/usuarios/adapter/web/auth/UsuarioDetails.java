package br.com.portalgni.cad.usuarios.adapter.web.auth;

import br.com.portalgni.cad.usuarios.core.domain.TipoUsuario;
import br.com.portalgni.cad.usuarios.core.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class UsuarioDetails implements UserDetails {

    final Usuario usuario;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        log.info("Entrei no metodo getAuthorities()");

        Set<TipoUsuario> listaTipoUsuario = usuario.getListaTipoUsuario();

        for (TipoUsuario tipoUsuario : listaTipoUsuario) {
            authorities.add(new SimpleGrantedAuthority(tipoUsuario.getRole().getNome()));
            log.info("Adicionando role ao usuário: ".concat(tipoUsuario.getRole().getNome()));
        }

        return authorities;
    }

    public Map<String, String >getContextMap(){
        return usuario.getListaTipoUsuario()
                .stream()
                .collect(Collectors.toMap(
                        tipoUsuario -> tipoUsuario.getRole().getNome(),
                        tipoUsuario -> ObjectUtils.anyNull(tipoUsuario.getContexto())
                                ? "" : tipoUsuario.getContexto()));
    }

    public String getUserId(){
        return usuario.getId();
    }

    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return usuario.getEmail();
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
