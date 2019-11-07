package com.cristi.web.jobgram.domain.gameoflife2;

import org.junit.Test;

public class CellTest {

    @Test(expected = IllegalArgumentException.class)
    public void when_negative_coords_reject_creation() {
        new Cell(State.ALIVE, new Coord(-1, 0));
    }

    @Test
    public void when_non_negative_coords_accept_creation() {
        new Cell(State.ALIVE, new Coord(1, 0));
    }
}
