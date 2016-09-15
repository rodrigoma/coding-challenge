package br.com.rodrigo.monteiro.api.exception;

import br.com.rodrigo.monteiro.api.domain.enums.PIT_ID;

import static java.lang.String.format;

/**
 * Created by monteiro on 9/14/16.
 */
public class InvalidPitException extends RuntimeException {

    public InvalidPitException() {
        super("The pit you have chosen is invalid.");
    }

    public InvalidPitException(PIT_ID pitId) {
        super(format("The pit '%s' you have chosen is invalid.", pitId.name()));
    }
}