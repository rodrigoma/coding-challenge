package br.com.rodrigo.monteiro.api.utils;

import br.com.rodrigo.monteiro.api.domain.KalahBoard;
import br.com.rodrigo.monteiro.api.domain.Pit;
import br.com.rodrigo.monteiro.api.domain.Player;
import br.com.rodrigo.monteiro.api.domain.enums.PIT_ID;
import br.com.rodrigo.monteiro.api.domain.enums.PLAYER_ID;
import br.com.rodrigo.monteiro.api.exception.FinishedException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by monteiro on 9/15/16.
 */
public class UtilsTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testCountAndClearStones() {
        KalahBoard kalahBoard = Utils.createBoard();
        int stones = Utils.countAndClearStones(kalahBoard.getPlayerNumber1());

        assertEquals(36, stones);
        assertEquals(0, kalahBoard.getPlayerNumber1().getPits().get(0).getStones());
        assertEquals(0, kalahBoard.getPlayerNumber1().getPits().get(1).getStones());
        assertEquals(0, kalahBoard.getPlayerNumber1().getPits().get(2).getStones());
        assertEquals(0, kalahBoard.getPlayerNumber1().getPits().get(3).getStones());
        assertEquals(0, kalahBoard.getPlayerNumber1().getPits().get(4).getStones());
        assertEquals(0, kalahBoard.getPlayerNumber1().getPits().get(5).getStones());
    }

    @Test
    public void testCountStones() {
        KalahBoard kalahBoard = Utils.createBoard();
        int stones = Utils.countAndClearStones(kalahBoard.getPlayerNumber1());
        assertEquals(36, stones);
    }

    @Test
    public void testChosenPit() {
        KalahBoard kalahBoard = Utils.createBoard();
        Pit pit = Utils.getChosenPit(kalahBoard.getPlayerNumber1(), PIT_ID.PIT_2);
        assertEquals(pit.getId(), PIT_ID.PIT_2);
    }

    @Test
    public void testGetPlayers() {
        KalahBoard kalahBoard = Utils.createBoard();

        Player player1 = Utils.getCurrentPlayer(kalahBoard);
        assertEquals(PLAYER_ID.PLAYER_1, player1.getId());

        Player opponentPlayer1 = Utils.getOpponentPlayer(kalahBoard.getCurrentPlayerId(), kalahBoard);
        assertEquals(PLAYER_ID.PLAYER_2, opponentPlayer1.getId());

        kalahBoard.setCurrentPlayerId(PLAYER_ID.PLAYER_2);

        Player player2 = Utils.getCurrentPlayer(kalahBoard);
        assertEquals(PLAYER_ID.PLAYER_2, player2.getId());

        Player opponentPlayer2 = Utils.getOpponentPlayer(kalahBoard.getCurrentPlayerId(), kalahBoard);
        assertEquals(PLAYER_ID.PLAYER_1, opponentPlayer2.getId());
    }

    @Test
    public void testChosenPit_true() {
        KalahBoard kalahBoard = Utils.createBoard();
        kalahBoard.getPlayerNumber1().setLastLagePit(true);
        assertTrue(Utils.willLastStoneInOwnLargePit(kalahBoard.getPlayerNumber1(), kalahBoard));
    }

    @Test
    public void testChosenPit_false() {
        KalahBoard kalahBoard = Utils.createBoard();
        assertFalse(Utils.willLastStoneInOwnLargePit(kalahBoard.getPlayerNumber1(), kalahBoard));
    }

    @Test
    public void testWinner_player1() {
        expectedEx.expect(FinishedException.class);
        expectedEx.expectMessage("The winner with '40' stones was 'PLAYER_1'");

        KalahBoard kalahBoard = Utils.createBoard();
        kalahBoard.getPlayerNumber1().getLargePit().addStones(40);
        kalahBoard.getPlayerNumber2().getLargePit().addStones(32);

        Utils.throwTheWinner(kalahBoard.getPlayerNumber1(), kalahBoard.getPlayerNumber2());
    }

    @Test
    public void testWinner_gameTied() {
        expectedEx.expect(FinishedException.class);
        expectedEx.expectMessage("Game Tied");

        KalahBoard kalahBoard = Utils.createBoard();
        Utils.throwTheWinner(kalahBoard.getPlayerNumber1(), kalahBoard.getPlayerNumber2());
    }

    @Test
    public void testWinner_player2() {
        expectedEx.expect(FinishedException.class);
        expectedEx.expectMessage("The winner with '50' stones was 'PLAYER_2'");

        KalahBoard kalahBoard = Utils.createBoard();
        kalahBoard.getPlayerNumber1().getLargePit().addStones(22);
        kalahBoard.getPlayerNumber2().getLargePit().addStones(50);

        Utils.throwTheWinner(kalahBoard.getPlayerNumber1(), kalahBoard.getPlayerNumber2());
    }
}