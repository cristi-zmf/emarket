package com.cristi.web.jobgram.domain.gameoflife2;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.stream.Collectors.toSet;

public class PopulationEvolver {

    public LivingCells evolve(LivingCells livingCells) {
        Set<Cell> cellsOfInterest = new HashSet<>(livingCells.getCells());
        Set<Cell> deadCellsAdjacentToLivingCells = findDeadCellsAdjacentToLivingCells(livingCells);
        cellsOfInterest.addAll(deadCellsAdjacentToLivingCells);
        Set<Cell> evolvedCells = cellsOfInterest.stream()
                .map(c -> evolveCell(c, livingCells))
                .filter(Cell::isAlive)
                .collect(toSet());
        return new LivingCells(evolvedCells, livingCells.getGridDimension());
    }

    private Cell evolveCell(Cell cell, LivingCells livingCells) {
        Set<Coord> neighbours = computeCellNeighbours(cell, livingCells.getGridDimension());
        Set<Coord> notNearbyLivingCells = new HashSet<>(livingCells.toCoords());
        notNearbyLivingCells.removeAll(neighbours);
        int livingNeighbours = livingCells.noOfLivingCells() - notNearbyLivingCells.size();
        return evaluateCellSurvival(cell, livingNeighbours);
    }

    private Cell evaluateCellSurvival(Cell cell, int livingNeighbours) {
//        System.out.println(format("Evaluating survival for %s with %d living neighbours", cell, livingNeighbours));
        if (cell.isAlive() && livingNeighbours >=2 && livingNeighbours <= 3) {
            return cell.reviveCell();
        } else if (cell.isDead() && livingNeighbours == 3) {
            return cell.reviveCell();
        } else {
            return cell.killCell();
        }
    }

    private Set<Coord> computeCellNeighbours(Cell cell, int gridDimension) {
        return cell.getCoord().neighbours(gridDimension);
    }

    private Set<Cell> findDeadCellsAdjacentToLivingCells(LivingCells livingCells) {
        return livingCells.getCells().stream()
                .flatMap(c -> computeCellNeighbours(c, livingCells.getGridDimension()).stream())
                .map(c -> livingCells.getCell(c))
                .filter(Cell::isDead)
                .collect(Collectors.toSet());
    }
}
