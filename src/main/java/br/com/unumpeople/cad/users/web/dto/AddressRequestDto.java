package br.com.unumpeople.cad.users.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AddressRequestDto(
        @Schema(description = "Street name")
        String street,
        @Schema(description = "Address Type")
        String addressType,
        @Schema(description = "Number")
        String number,
        @Schema(description = "Address Complement")
        String addressComplement,
        @Schema(description = "Neighborhood")
        String neighborhood,
        @Schema(description = "City")
        String city,
        @Schema(description = "State")
        String state,
        @Schema(description = "Postal code")
        String postalCode
) {
}
