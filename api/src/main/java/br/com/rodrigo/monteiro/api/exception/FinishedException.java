package br.com.rodrigo.monteiro.api.exception;

import br.com.rodrigo.monteiro.api.domain.enums.PLAYER_ID;

/**
 * Created by monteiro on 9/14/16.
 */
public class FinishedException extends RuntimeException {

    public FinishedException() {
        super("Game Tied");
    }

    public FinishedException(PLAYER_ID playerId, Integer stones) {
        super(String.format("The winner with '%s' stones was '%s'", stones.toString(), playerId.name().toUpperCase()));
    }
}
