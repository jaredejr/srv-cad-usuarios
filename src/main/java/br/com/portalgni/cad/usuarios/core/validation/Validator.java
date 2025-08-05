package br.com.portalgni.cad.usuarios.core.validation.util;

import br.com.portalgni.cad.usuarios.core.validation.ValidationStrategy;

import java.util.List;

public class Validator<T> {
    private final List<ValidationStrategy<T>> strategies;

    public Validator(List<ValidationStrategy<T>> strategies) {
        this.strategies = strategies;
    }
    public void validate(T obj) {
        strategies.forEach(strategy -> strategy.validate(obj));
    }
}
