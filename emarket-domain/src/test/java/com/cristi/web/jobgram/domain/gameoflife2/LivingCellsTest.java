package com.cristi.web.jobgram.domain.gameoflife2;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.cristi.web.jobgram.domain.gameoflife2.State.ALIVE;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class LivingCellsTest {

    private LivingCells sut = someLivingCells();

    @Test
    public void when_coord_matches_a_living_cell_retun_true() {
        sut = someLivingCells();
        Coord aliveCoord = new Coord(1, 1);
        Coord deadCoord = new Coord(1, 0);
        assertThat(sut.isALivingCell(aliveCoord)).isTrue();
        assertThat(sut.isALivingCell(deadCoord)).isFalse();
    }

    @Test
    public void highestCoordNumber() {
        sut = biggerLivingCells();
        assertThat(sut.minimumGridDimension()).isEqualTo(583);
    }

    private LivingCells someLivingCells() {
        Set<Cell> livingCellsInternals = new HashSet<>(asList(
                new Cell(ALIVE, new Coord(1, 1)),
                new Cell(ALIVE, new Coord(1, 2))
        ));
        return new LivingCells(livingCellsInternals, 3);
    }

    private LivingCells biggerLivingCells() {
        Set<Cell> livingCellsInternals = new HashSet<>(asList(
                new Cell(ALIVE, new Coord(43, 5)),
                new Cell(ALIVE, new Coord(583, 42)),
                new Cell(ALIVE, new Coord(7, 5)),
                new Cell(ALIVE, new Coord(65, 3)),
                new Cell(ALIVE, new Coord(443, 25)),
                new Cell(ALIVE, new Coord(43, 2))
        ));
        return new LivingCells(livingCellsInternals, 589);
    }
}
