package com.cristi.web.jobgram.domain.gameoflife2;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class CoordTest {

    @Test
    public void equals() {
        assertThat(new Coord(1, 0)).isNotEqualTo(new Coord(0, 1));
        assertThat(new Coord(1, 0).hashCode()).isNotEqualTo(new Coord(0, 1));
    }



    @Test
    public void hasAnyNegativeCoords() {
        assertThat(new Coord(0, 0).hasAnyNegativeCoords()).isFalse();
        assertThat(new Coord(-1, 0).hasAnyNegativeCoords()).isTrue();
        assertThat(new Coord(2, -234343).hasAnyNegativeCoords()).isTrue();
    }

    @Test
    public void highestInt() {
        assertThat(new Coord( 3, 3).highestCoordNumber()).isEqualTo(3);
        assertThat(new Coord( 3, 7).highestCoordNumber()).isEqualTo(7);
        assertThat(new Coord( 3, -1).highestCoordNumber()).isEqualTo(3);

    }

    @Test
    public void computeNeighbours() {
        int gridDimension = 3;
        Coord coord = new Coord(0, 1);
        Set<Coord> expectedNeighbours = new HashSet<>(asList(
                new Coord(2, 0),
                new Coord(2, 1),
                new Coord(2, 2),
                new Coord(0, 0),
                new Coord(1, 0),
                new Coord(1, 1),
                new Coord(1, 2),
                new Coord(0, 2)
        ));
        assertExpectedNeighbours(gridDimension, coord, expectedNeighbours);
    }

    @Test
    public void computeNeighbours2() {
        int gridDimension = 4;
        Coord coord = new Coord(2, 1);
        Set<Coord> expectedNeighbours = new HashSet<>(asList(
                new Coord(1, 0),
                new Coord(1, 1),
                new Coord(1, 2),
                new Coord(2, 0),
                new Coord(3, 0),
                new Coord(3, 1),
                new Coord(3, 2),
                new Coord(2, 2)
        ));
        assertExpectedNeighbours(gridDimension, coord, expectedNeighbours);
    }

    private void assertExpectedNeighbours(int gridDimension, Coord coord, Set<Coord> expectedNeighbours) {
        Set<Coord> actualNeighbours = coord.neighbours(gridDimension);
        assertThat(actualNeighbours).hasSize(8);
        assertThat(actualNeighbours).containsExactlyInAnyOrderElementsOf(expectedNeighbours);
    }
}
