package br.com.unumpeople.cad.users.infra.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DocumentEntity {
    private String number;
    private String documentType;
    private LocalDate issueDate;
    private LocalDate expirationDate;
    private String issuer;
}
