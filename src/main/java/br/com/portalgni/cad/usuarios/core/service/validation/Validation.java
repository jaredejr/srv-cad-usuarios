package br.com.portalgni.cad.usuarios.core.service.validation;


import javax.management.InvalidAttributeValueException;

public interface Validation<T> {

    public void validate(T obj) throws InvalidAttributeValueException;

}
