package br.com.unumpeople.cad.users.core.domain;

import br.com.unumpeople.cad.users.core.exception.DomainValidationException;
import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class Email {

    private final String value;

    private static final String EMAIL_PATTERN
            = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public Email(String email) {
        if (null == email || email.isEmpty()) throw new DomainValidationException("O e-mail não deve ser nulo ou vazio.");
        if(!isEmailPatternValid(email)) throw new DomainValidationException("O formato do e-mail é inválido.");
        this.value = email;
    }

    private Boolean isEmailPatternValid(String value) {
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
