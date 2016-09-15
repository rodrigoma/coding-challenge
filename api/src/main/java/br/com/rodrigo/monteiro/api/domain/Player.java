package br.com.rodrigo.monteiro.api.domain;

import br.com.rodrigo.monteiro.api.domain.enums.PLAYER_ID;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by monteiro on 9/14/16.
 */
public class Player implements Serializable {

    private static final long serialVersionUID = -3421345045070690175L;

    private PLAYER_ID id;
    private List<Pit> pits = new LinkedList();
    private Pit largePit;
    private Pit lastPitMove;
    private boolean lastLagePit;

    public PLAYER_ID getId() {
        return id;
    }

    public Player setId(PLAYER_ID id) {
        this.id = id;
        return this;
    }

    public List<Pit> getPits() {
        return pits;
    }

    public Player setPits(List<Pit> pits) {
        this.pits = pits;
        return this;
    }

    public Pit getLargePit() {
        return largePit;
    }

    public Player setLargePit(Pit largePit) {
        this.largePit = largePit;
        return this;
    }

    public Pit getLastPitMove() {
        return lastPitMove;
    }

    public void setLastPitMove(Pit lastPit) {
        this.lastPitMove = lastPit;
    }

    public boolean isLastLagePit() {
        return lastLagePit;
    }

    public void setLastLagePit(boolean lastLagePit) {
        this.lastLagePit = lastLagePit;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", pits=" + pits +
                ", largePit=" + largePit +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (id != player.id) return false;
        if (pits != null ? !pits.equals(player.pits) : player.pits != null) return false;
        return largePit != null ? largePit.equals(player.largePit) : player.largePit == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (pits != null ? pits.hashCode() : 0);
        result = 31 * result + (largePit != null ? largePit.hashCode() : 0);
        return result;
    }
}
