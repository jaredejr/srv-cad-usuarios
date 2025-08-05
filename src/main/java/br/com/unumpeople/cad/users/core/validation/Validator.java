package br.com.unumpeople.cad.users.core.validation;

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
