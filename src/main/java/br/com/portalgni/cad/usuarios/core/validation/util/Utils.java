package br.com.portalgni.cad.usuarios.core.validation.util;

import org.bson.types.ObjectId;

public class Utils {

    public static boolean isValidHex(String id) {
        try {
            new ObjectId(id);
        } catch (IllegalArgumentException e){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
