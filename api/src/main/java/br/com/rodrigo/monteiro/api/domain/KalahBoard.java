package br.com.rodrigo.monteiro.api.domain;

import br.com.rodrigo.monteiro.api.domain.enums.PLAYER_ID;
import java.io.Serializable;

/**
 * Created by monteiro on 9/14/16.
 */
public class KalahBoard implements Serializable {

    private static final long serialVersionUID = 7209669807573734302L;

    private String id;
    private PLAYER_ID currentPlayerId;
    private Player playerNumber1;
    private Player playerNumber2;

    public String getId() {
        return id;
    }

    public KalahBoard setId(String id) {
        this.id = id;
        return this;
    }

    public PLAYER_ID getCurrentPlayerId() {
        return currentPlayerId;
    }

    public KalahBoard setCurrentPlayerId(PLAYER_ID currentPlayerId) {
        this.currentPlayerId = currentPlayerId;
        return this;
    }

    public Player getPlayerNumber1() {
        return playerNumber1;
    }

    public KalahBoard setPlayerNumber1(Player playerNumber1) {
        this.playerNumber1 = playerNumber1;
        return this;
    }

    public Player getPlayerNumber2() {
        return playerNumber2;
    }

    public KalahBoard setPlayerNumber2(Player playerNumber2) {
        this.playerNumber2 = playerNumber2;
        return this;
    }

    @Override
    public String toString() {
        return "KalahBoard{" +
                "id='" + id + '\'' +
                ", playerNumber1=" + playerNumber1 +
                ", playerNumber2=" + playerNumber2 +
                ", currentPlayerId=" + currentPlayerId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KalahBoard that = (KalahBoard) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (playerNumber1 != null ? !playerNumber1.equals(that.playerNumber1) : that.playerNumber1 != null)
            return false;
        if (playerNumber2 != null ? !playerNumber2.equals(that.playerNumber2) : that.playerNumber2 != null)
            return false;
        return currentPlayerId == that.currentPlayerId;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (playerNumber1 != null ? playerNumber1.hashCode() : 0);
        result = 31 * result + (playerNumber2 != null ? playerNumber2.hashCode() : 0);
        result = 31 * result + (currentPlayerId != null ? currentPlayerId.hashCode() : 0);
        return result;
    }
}