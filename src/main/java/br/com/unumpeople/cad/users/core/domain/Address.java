package br.com.portalgni.cad.usuarios.core.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String addressType;
    private String street;
    private String number;
    private String addressComplement;
    private String neighborhood;
    private String city;
    private String state;
    private String zipCode;
}
