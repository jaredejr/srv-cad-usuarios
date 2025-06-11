package br.com.portalgni.cad.usuarios.web.dto;

import br.com.portalgni.cad.usuarios.core.domain.Documento;
import br.com.portalgni.cad.usuarios.core.domain.Endereco;
import br.com.portalgni.cad.usuarios.core.domain.TipoUsuario;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "UsuarioDto", description = "Representação de um usuário")
public class UsuarioDto {

    @Schema(description = "ID do usuários (ObjectId em formato hexadecimal)", hidden = true)
    private String id;

    @Schema(description = "Nome do usuário")
    private String nome;

    @Schema(description = "Email do usuário")
    private String email;

    @Schema(description = "Senha do usuário")
    private String senha;

    @Schema(description = "Endereços do usuário")
    private Set<Endereco> enderecos;

    @Schema(description = "Documentos do usuário")
    private Set<Documento> documentos;

    @Schema(description = "Tipo de acesso do usuário")
    private Set<TipoUsuario> listaTipoUsuario;

    @Schema(description = "Data de criação do usuário")
    private LocalDateTime dataCriacao;

    @Schema(description = "Ultimo acesso do usuário")
    private LocalDateTime  ultimoAcesso;

    @Schema(description = "Status do usuário")
    private String status;

}
