package br.com.portalgni.cad.usuarios.core.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleContext {

    private Role role;
    private String context;
}
