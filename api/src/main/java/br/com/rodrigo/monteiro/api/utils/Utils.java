package br.com.rodrigo.monteiro.api.utils;

import br.com.rodrigo.monteiro.api.domain.KalahBoard;
import br.com.rodrigo.monteiro.api.domain.Pit;
import br.com.rodrigo.monteiro.api.domain.Player;
import br.com.rodrigo.monteiro.api.domain.enums.PIT_ID;
import br.com.rodrigo.monteiro.api.domain.enums.PLAYER_ID;
import br.com.rodrigo.monteiro.api.exception.FinishedException;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static br.com.rodrigo.monteiro.api.domain.enums.PLAYER_ID.PLAYER_1;
import static br.com.rodrigo.monteiro.api.domain.enums.PLAYER_ID.PLAYER_2;

/**
 * Created by monteiro on 9/14/16.
 */
public class Utils {

    public static int countAndClearStones(final Player player) {
        int count = countStones(player);
        player.getPits().forEach(pit -> pit.clear());
        return count;
    }

    public static int countStones(final Player player) {
        AtomicInteger count = new AtomicInteger();
        player.getPits().forEach(pit -> count.addAndGet(pit.getStones()));
        return count.get();
    }

    public static Player getCurrentPlayer(KalahBoard board) {
        return (board.getCurrentPlayerId().equals(PLAYER_1)) ? board.getPlayerNumber1() : board.getPlayerNumber2();
    }

    public static Player getOpponentPlayer(PLAYER_ID currentPlayerId, KalahBoard board) {
        return (!currentPlayerId.equals(PLAYER_1)) ? board.getPlayerNumber1() : board.getPlayerNumber2();
    }

    public static Pit getChosenPit(Player player, PIT_ID chosenPitId) {
        return player.getPits().get(chosenPitId.ordinal());
    }

    public static void throwTheWinner(Player currentPlayer, Player opponentPlayer) {
        if (currentPlayer.getLargePit().getStones() > opponentPlayer.getLargePit().getStones()) {
            throw new FinishedException(currentPlayer.getId(), currentPlayer.getLargePit().getStones());
        } else if (currentPlayer.getLargePit().getStones() < opponentPlayer.getLargePit().getStones()) {
            throw new FinishedException(opponentPlayer.getId(), opponentPlayer.getLargePit().getStones());
        } else {
            throw new FinishedException();
        }
    }

    public static boolean willLastStoneInOwnLargePit(Player player, KalahBoard kalahBoard) {
        return (kalahBoard.getCurrentPlayerId().equals(player.getId()) && player.isLastLagePit());
    }

    public static void clearPits(Player[] players) {
        for (Player player : players) {
            int stones = countAndClearStones(player);
            player.getLargePit().addStones(stones);
        }
    }

    //// CREATE

    public static KalahBoard createBoard() {
        KalahBoard kalahBoard = new KalahBoard()
                .setId(UUID.randomUUID().toString())
                .setPlayerNumber1(createPlayer(PLAYER_1))
                .setPlayerNumber2(createPlayer(PLAYER_2));

        setCurrentPlayer(kalahBoard, false);

        return kalahBoard;
    }

    private static Player createPlayer(PLAYER_ID playerId) {
        return new Player()
                .setId(playerId)
                .setLargePit(new Pit())
                .setPits(createPitsWith6Stones());
    }

    private static List<Pit> createPitsWith6Stones() {
        List<Pit> pits = new LinkedList();

        for (int i = 0; i < PIT_ID.values().length; i++) {
            pits.add(i,
                    new Pit()
                            .setId(PIT_ID.values()[i])
                            .setStones(6));
        }
        return pits;
    }

    public static void setCurrentPlayer(KalahBoard kalahBoard, boolean lastStoneOwnLargePit) {
        if (!lastStoneOwnLargePit) {
            if (kalahBoard.getCurrentPlayerId() == null || kalahBoard.getCurrentPlayerId().equals(PLAYER_2)) {
                kalahBoard.setCurrentPlayerId(PLAYER_1);
            } else {
                kalahBoard.setCurrentPlayerId(PLAYER_2);
            }
        }
    }
}