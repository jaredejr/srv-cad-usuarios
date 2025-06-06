package br.com.portalgni.cad.usuarios.adapter.infra.entity;

import br.com.portalgni.cad.usuarios.core.domain.Documento;
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
@Document(collection = "users")
public class UsuarioEntity {

    @Id
    private ObjectId id;
    private String nome;
    private String email;
    private String senha;
    private Set<EnderecoEntity> enderecos;
    private Set<DocumentoEntity> documentos;
    private Set<TipoUsuarioEntity> tipoUsuario;
    private LocalDateTime dataCriacao;
    private LocalDateTime ultimoAcesso;
    private String status;

}
