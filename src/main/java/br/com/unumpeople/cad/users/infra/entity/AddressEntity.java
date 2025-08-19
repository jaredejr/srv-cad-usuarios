package br.com.unumpeople.cad.users.infra.entity;

public record AddressEntity(
        String addressType,
        String street,
        String number,
        String addressComplement,
        String neighborhood,
        String city,
        String state,
        String zipCode
) {
}
