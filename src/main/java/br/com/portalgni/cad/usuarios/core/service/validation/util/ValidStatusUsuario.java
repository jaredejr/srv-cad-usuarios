package br.com.portalgni.cad.usuarios.core.service.validation.util;

public enum ValidStatusUsuario {

    ATIVO,
    INATIVO;

    public static boolean isValid(String value) {
        for (ValidStatusUsuario status : ValidStatusUsuario.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

}
