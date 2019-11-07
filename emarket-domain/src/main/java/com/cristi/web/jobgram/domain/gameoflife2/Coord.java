package com.cristi.web.jobgram.domain.gameoflife2;

import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;

@EqualsAndHashCode
public class Coord {
    public static final Set<Coord> NORMALIZED_NEIGHBOURS = normalizedNeighbours();

    private final int x;

    private final int y;
    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean hasAnyNegativeCoords() {
        return x < 0 || y < 0;
    }

    public int highestCoordNumber() {
        return x >= y ? x : y;
    }

    @Override
    public String toString() {
        return "Coord{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
    private Coord applyNormalizedNeighbour(Coord neighbour, int gridDimension) {
        if (!NORMALIZED_NEIGHBOURS.contains(neighbour)) {
            throw new IllegalArgumentException(format("Not a normalized neighbour %s", neighbour));
        }
        int newX = this.x + neighbour.getX();
        int newY = this.y + neighbour.getY();
        return new Coord(handleBounds(newX, gridDimension), handleBounds(newY, gridDimension));
    }

    private int handleBounds(int axe, int gridDimension) {
        if (axe < 0) {
            axe += gridDimension;
        }
        if (axe >= gridDimension) {
            axe = axe % gridDimension;
        }
        return axe;
    }

    public Set<Coord> neighbours(int gridDimension) {
        Set<Coord> computedNeighbours = NORMALIZED_NEIGHBOURS.stream()
                .map(n -> applyNormalizedNeighbour(n, gridDimension))
//                .peek(System.out::println)
                .collect(toSet());
        if (computedNeighbours.size() < 8) {
            throw new IllegalStateException("Numbers of neighbours must be 8, actual " + computedNeighbours.size() + " for cell " + this + "and grid of size " + gridDimension);
        }
//        System.out.println("Computed neighbours " + computedNeighbours + " with size " + computedNeighbours.size() + " for cell " + this);
        return computedNeighbours;
    }

    private static Set<Coord> normalizedNeighbours() {
        return new HashSet<>(asList(
                new Coord(-1, -1),
                new Coord(-1, 0),
                new Coord(-1, 1),
                new Coord(0, -1),
                new Coord(1, -1),
                new Coord(1, 0),
                new Coord(1, 1),
                new Coord(0, 1)
        ));
    }
}
