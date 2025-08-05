package br.com.unumpeople.cad.users.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Document {
    private String number;
    private String documentType;
    private LocalDate issueDate;
    private LocalDate expirationDate;
    private String issuer;
}
