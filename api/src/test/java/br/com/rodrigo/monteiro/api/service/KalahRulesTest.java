package br.com.rodrigo.monteiro.api.service;

import br.com.rodrigo.monteiro.api.domain.KalahBoard;
import br.com.rodrigo.monteiro.api.exception.FinishedException;
import br.com.rodrigo.monteiro.api.utils.Utils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by monteiro on 9/15/16.
 */
public class KalahRulesTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testReferee_winnerPlayer1() {
        expectedEx.expect(FinishedException.class);
        expectedEx.expectMessage("The winner with '40' stones was 'PLAYER_1'");

        KalahBoard kalahBoard = Utils.createBoard();
        Utils.countAndClearStones(kalahBoard.getPlayerNumber1());
        kalahBoard.getPlayerNumber1().getLargePit().addStones(40);
        kalahBoard.getPlayerNumber2().getLargePit().addStones(32);

        Utils.throwTheWinner(kalahBoard.getPlayerNumber1(), kalahBoard.getPlayerNumber2());
    }

    @Test
    public void testWinner_gameTied() {
        expectedEx.expect(FinishedException.class);
        expectedEx.expectMessage("Game Tied");

        KalahBoard kalahBoard = Utils.createBoard();
        Utils.countAndClearStones(kalahBoard.getPlayerNumber1());
        Utils.throwTheWinner(kalahBoard.getPlayerNumber1(), kalahBoard.getPlayerNumber2());
    }

    @Test
    public void testWinner_player2() {
        expectedEx.expect(FinishedException.class);
        expectedEx.expectMessage("The winner with '50' stones was 'PLAYER_2'");

        KalahBoard kalahBoard = Utils.createBoard();
        Utils.countAndClearStones(kalahBoard.getPlayerNumber1());
        kalahBoard.getPlayerNumber1().getLargePit().addStones(22);
        kalahBoard.getPlayerNumber2().getLargePit().addStones(50);

        Utils.throwTheWinner(kalahBoard.getPlayerNumber1(), kalahBoard.getPlayerNumber2());
    }
}