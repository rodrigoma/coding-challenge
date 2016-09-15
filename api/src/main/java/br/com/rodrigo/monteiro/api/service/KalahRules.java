package br.com.rodrigo.monteiro.api.service;

import br.com.rodrigo.monteiro.api.domain.KalahBoard;
import br.com.rodrigo.monteiro.api.domain.Pit;
import br.com.rodrigo.monteiro.api.domain.Player;
import br.com.rodrigo.monteiro.api.domain.enums.PIT_ID;
import br.com.rodrigo.monteiro.api.exception.InvalidPitException;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.rodrigo.monteiro.api.domain.enums.PIT_ID.getOpposite;
import static br.com.rodrigo.monteiro.api.utils.Utils.*;

/**
 * Created by monteiro on 9/14/16.
 */
@Service
public class KalahRules {

    public void referee(KalahBoard kalahBoard) {
        Player currentPlayer = getCurrentPlayer(kalahBoard);
        Player opponentPlayer = getOpponentPlayer(currentPlayer.getId(), kalahBoard);

        if (countStones(currentPlayer) == 0 || countStones(opponentPlayer) == 0) {
            clearPits(new Player[]{currentPlayer, opponentPlayer});

            throwTheWinner(currentPlayer, opponentPlayer);
        }
    }

    public void moveStones(final KalahBoard kalahBoard, final PIT_ID chosenPitId) {
        Player currentPlayer = getCurrentPlayer(kalahBoard);

        Pit chosenPit = getChosenPit(currentPlayer, chosenPitId);

        if (chosenPit.isValidPit()) {
            int chosenStones = chosenPit.getStones();
            chosenPit.clear();

            Player player = iterate(kalahBoard, currentPlayer, pits(currentPlayer, chosenPitId), chosenStones);

            boolean lastStoneOwnLargePit = willLastStoneInOwnLargePit(player, kalahBoard);

            if (!lastStoneOwnLargePit) {
                landedEmptyPit(player, kalahBoard);
            }

            referee(kalahBoard);

            setCurrentPlayer(kalahBoard, lastStoneOwnLargePit);
        } else  {
            throw new InvalidPitException(chosenPitId);
        }
    }

    private Player iterate(KalahBoard kalahBoard, Player player, List<Pit> pits, int stones) {
        for (Pit pit : pits) {
            if (stones == 0) {
                break;
            }

            pit.addStones(1);
            player.setLastPitMove(pit);
            player.setLastLagePit(false);
            stones--;
        }

        if (stones > 0 && player.getId().equals(kalahBoard.getCurrentPlayerId())) {
            player.getLargePit().addStones(1);
            player.setLastLagePit(true);
            stones--;
        }

        if (stones > 0) {
            player.setLastLagePit(false);
            Player opponentPlayer = getOpponentPlayer(player.getId(), kalahBoard);
            return iterate(kalahBoard, opponentPlayer, opponentPlayer.getPits(), stones);
        }

        return player;
    }

    private List<Pit> pits(Player player, PIT_ID pitId) {
        return player.getPits().subList(pitId.ordinal() + 1, 6);
    }

    private void landedEmptyPit(Player player, KalahBoard kalahBoard) {
        if (kalahBoard.getCurrentPlayerId().equals(player.getId())) {
            Pit pit = player.getLastPitMove();

            if (pit.getStones() == 1) {
                Player opponentPlayer = getOpponentPlayer(player.getId(), kalahBoard);

                Pit oppositePit = getChosenPit(opponentPlayer, getOpposite(pit.getId()));

                player.getLargePit()
                        .addStones(oppositePit.getStones())
                        .addStones(pit.getStones());

                oppositePit.clear();
                pit.clear();
            }
        }
    }
}