package br.com.unumpeople.cad.users.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record DocumentRequestDto(
        @Schema(description = "Document number")
        String number,
        @Schema(description = "Document type")
        String documentType,
        @Schema(description = "Issue Date")
        LocalDate issueDate,
        @Schema(description = "Expiration Date")
        LocalDate expirationDate,
        @Schema(description = "Issuer")
        String issuer
) {
}
