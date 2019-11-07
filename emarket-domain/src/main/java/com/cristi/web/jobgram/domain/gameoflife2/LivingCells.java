package com.cristi.web.jobgram.domain.gameoflife2;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.cristi.web.jobgram.domain.gameoflife2.Cell.DEAD_CELL;
import static com.cristi.web.jobgram.domain.gameoflife2.State.ALIVE;
import static com.cristi.web.jobgram.domain.gameoflife2.State.DEAD;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class LivingCells {
    private final Map<Coord, Cell> aliveCells;
    private final int gridDimension;


    public LivingCells(Set<Cell> aliveCells, int gridDimension) {
        this.aliveCells = aliveCells.stream().filter(Cell::isAlive).collect(toMap(Cell::getCoord, identity()));
        if (gridDimension < minimumGridDimension()) {
            this.gridDimension = minimumGridDimension();
        } else {
            this.gridDimension = gridDimension;
        }
    }

    public boolean isALivingCell(Coord coord) {
        return aliveCells.getOrDefault(coord, DEAD_CELL).getState() == ALIVE;
    }

    public static LivingCells copyOf(LivingCells livingCells) {
        return new LivingCells(new HashSet<>(livingCells.aliveCells.values()), livingCells.gridDimension);
    }

    public int minimumGridDimension() {
        return aliveCells.keySet().stream().map(Coord::highestCoordNumber).max(Integer::compareTo).orElse(0);
    }

    public int getGridDimension() {
        return gridDimension;
    }

    public Set<Coord> toCoords() {
        return Collections.unmodifiableSet(aliveCells.keySet());
    }

    public Set<Cell> getCells() {
        return new HashSet<>(aliveCells.values());
    }

    public Cell getCell(Coord coord) {
        return aliveCells.getOrDefault(coord, new Cell(DEAD, coord));
    }

    public int noOfLivingCells() {
        return aliveCells.size();
    }
}
