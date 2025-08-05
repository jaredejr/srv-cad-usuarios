package br.com.portalgni.cad.usuarios.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Documento {
    private String numero;
    private String tipoDocumento;
    private LocalDate emissao;
    private LocalDate validade;
    private String emissor;
}
