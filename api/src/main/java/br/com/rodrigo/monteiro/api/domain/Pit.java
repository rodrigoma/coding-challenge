package br.com.rodrigo.monteiro.api.domain;

import br.com.rodrigo.monteiro.api.domain.enums.PIT_ID;

import java.io.Serializable;

/**
 * Created by monteiro on 9/14/16.
 */
public class Pit implements Serializable {

    private static final long serialVersionUID = -8160224474621987459L;

    private PIT_ID id;
    private int stones;

    public PIT_ID getId() {
        return id;
    }

    public Pit setId(PIT_ID id) {
        this.id = id;
        return this;
    }

    public int getStones() {
        return stones;
    }

    public Pit setStones(int stones) {
        this.stones = stones;
        return this;
    }

    public Pit addStones(int stones) {
        this.stones += stones;
        return this;
    }

    public void clear() {
        this.stones = 0;
    }

    public boolean isValidPit() {
        return this.stones > 0;
    }

    @Override
    public String toString() {
        return "Pit{" +
                "id=" + id +
                ", stones=" + stones +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pit pit = (Pit) o;

        if (stones != pit.stones) return false;
        return id == pit.id;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + stones;
        return result;
    }
}