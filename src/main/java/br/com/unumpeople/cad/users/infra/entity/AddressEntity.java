package br.com.unumpeople.cad.users.infra.entity;


import org.bson.types.ObjectId;

public record AddressEntity(
        ObjectId id,
        String description,
        String addressType,
        String street,
        String number,
        String addressComplement,
        String neighborhood,
        String city,
        String state,
        String zipCode,
        String country
) {
}
