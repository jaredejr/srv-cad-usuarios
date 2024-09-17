package br.com.portalgni.cad.usuarios.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    private String id;
    private String nome;
    private String email;
    private String senha;
    private Set<TipoUsuario> listaTipoUsuario;
    private String idImobiliaria;
    private LocalDateTime dataCriacao;
    private LocalDateTime  ultimoAcesso;
    private String status;

}
