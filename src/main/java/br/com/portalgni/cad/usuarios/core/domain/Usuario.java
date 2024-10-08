package br.com.portalgni.cad.usuarios.core.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    private String id;
    private String nome;
    private String email;
    private String senha;
    private Set<Endereco> enderecos;
    private Set<Documento> documentos;
    private Set<TipoUsuario> listaTipoUsuario;
    private LocalDateTime dataCriacao;
    private LocalDateTime  ultimoAcesso;
    private String status;

}
