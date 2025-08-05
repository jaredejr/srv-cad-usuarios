package br.com.portalgni.cad.usuarios.core.validation;


import br.com.portalgni.cad.usuarios.core.exception.DomainValidationException;

import javax.management.InvalidAttributeValueException;

public interface ValidationStrategy<T> {

    public void validate(T obj) throws DomainValidationException;

}
