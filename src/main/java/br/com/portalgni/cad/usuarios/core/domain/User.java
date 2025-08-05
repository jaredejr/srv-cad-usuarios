package br.com.portalgni.cad.usuarios.core.domain;

import br.com.portalgni.cad.usuarios.core.exception.DomainValidationException;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
public class Usuario {

    private String id;
    private String name;
    private Email email;
    private Password password;
    private Set<Endereco> enderecos;
    private Set<Documento> documentos;
    private Set<TipoUsuario> listaTipoUsuario;
    private LocalDateTime dataCriacao;
    private LocalDateTime  ultimoAcesso;
    private Status status;

    public Usuario(String email, String name, Set<Endereco> enderecos, Set<Documento> documentos, Set<TipoUsuario> listaTipoUsuario, String status) {

        this.email = new Email(email);
        validateAndSetName(name);
        this.enderecos = enderecos;
        this.documentos = documentos;
        this.listaTipoUsuario = listaTipoUsuario;
        this.dataCriacao = LocalDateTime.now();
        this.ultimoAcesso = LocalDateTime.now();
        this.status = new Status(status);
    }

    public Usuario(String id, String name, String email, String senha, Set<Endereco> enderecos, Set<Documento> documentos, Set<TipoUsuario> listaTipoUsuario, LocalDateTime dataCriacao, LocalDateTime ultimoAcesso, String status) {

        if (null == id || id.isEmpty()) throw new DomainValidationException("O id não deve ser nulo ou vazio");
        this.id = id;
        validateAndSetName(name);
        this.email = new Email(email);
        this.password = new Password(senha);
        this.enderecos = enderecos;
        this.documentos = documentos;
        this.listaTipoUsuario = listaTipoUsuario;
        this.dataCriacao = dataCriacao;
        this.ultimoAcesso = ultimoAcesso;
        this.status = new Status(status);
    }

    private void validateAndSetName(String name) {
        if (null == this.name || this.name.isEmpty()) throw new DomainValidationException("O nome não deve ser nulo ou vazio");
        this.name = name;
    }

    public void updateLastAccess(){
        this.ultimoAcesso = LocalDateTime.now();
    }

    public void setPassword(String value) {
        this.password = new Password(value);
    }
}
