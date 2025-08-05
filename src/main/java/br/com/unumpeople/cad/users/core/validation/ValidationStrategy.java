package br.com.unumpeople.cad.users.core.validation;


import br.com.unumpeople.cad.users.core.exception.DomainValidationException;

import javax.management.InvalidAttributeValueException;

public interface ValidationStrategy<T> {

    void validate(T obj) throws DomainValidationException;

}
