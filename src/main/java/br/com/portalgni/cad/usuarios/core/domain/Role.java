package br.com.portalgni.cad.usuarios.core.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    private String id;
    private String nome;
    private String descricao;

}
