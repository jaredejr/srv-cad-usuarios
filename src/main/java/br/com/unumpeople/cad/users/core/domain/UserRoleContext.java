package br.com.unumpeople.cad.users.core.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleContext {

    private Role role;
    private String context;
}
