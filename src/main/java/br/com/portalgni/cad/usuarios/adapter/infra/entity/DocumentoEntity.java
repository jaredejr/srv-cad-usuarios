package br.com.portalgni.cad.usuarios.adapter.infra.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DocumentoEntity {
    private String numero;
    private String tipoDocumento;
    private LocalDate emissao;
    private LocalDate validade;
    private String emissor;
}
