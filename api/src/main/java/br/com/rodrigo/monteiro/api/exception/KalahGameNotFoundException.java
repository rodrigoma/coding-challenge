package br.com.rodrigo.monteiro.api.exception;

/**
 * Created by monteiro on 9/14/16.
 */
public class KalahGameNotFoundException extends RuntimeException {

    public KalahGameNotFoundException() {
        super("The kalah game was not found.");
    }
}