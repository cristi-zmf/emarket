package com.cristi.web.jobgram.domain.gameoflife2;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.cristi.web.jobgram.domain.gameoflife2.State.ALIVE;
import static com.cristi.web.jobgram.domain.gameoflife2.State.DEAD;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class GridPrinterTest {
    private GridPrinter sut;

    private LivingCells biggerLivingCells() {
        Set<Cell> livingCellsInternals = new HashSet<>(asList(
                new Cell(ALIVE, new Coord(3, 1)),
                new Cell(ALIVE, new Coord(1, 1)),
                new Cell(ALIVE, new Coord(2, 2)),
                new Cell(ALIVE, new Coord(3, 2))
        ));
        return new LivingCells(livingCellsInternals, 4);
    }

    @Test
    public void when_living_cells_print_a_square_grid_containing_all_living_cells() {
        sut = new GridPrinter(biggerLivingCells());
        assertThat(sut.printGrid()).isEqualTo(
                        "[0 0 0 0]\n" +
                        "[0 1 0 0]\n" +
                        "[0 0 1 0]\n" +
                        "[0 1 1 0]"

        );
    }
}
