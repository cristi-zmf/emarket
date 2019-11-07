package com.cristi.web.jobgram.domain.gameoflife2;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static com.cristi.web.jobgram.domain.gameoflife2.State.ALIVE;
import static com.cristi.web.jobgram.domain.gameoflife2.State.DEAD;
import static java.lang.String.format;

@EqualsAndHashCode
@Getter
@ToString
public class Cell {
    private final State state;
    private final Coord coord;

    public Cell(State state, Coord coord) {
        this.state = state;
        this.coord = coord;
        if (coord.hasAnyNegativeCoords()) {
            throw new IllegalArgumentException(
                    format("Cell should have coords greater or equal than 0. Actual: x = %d, y = %d", coord.getX(), coord.getY())
            );
        }
    }


    public static final Cell DEAD_CELL = new Cell(State.DEAD, new Coord(0, 0));


    public boolean isAlive() {
        return state == ALIVE;
    }

    public Cell killCell() {
        return new Cell(DEAD, coord);
    }

    public Cell reviveCell() {
        return new Cell(ALIVE, coord);
    }

    public boolean isDead() {
        return state == DEAD;
    }
}
