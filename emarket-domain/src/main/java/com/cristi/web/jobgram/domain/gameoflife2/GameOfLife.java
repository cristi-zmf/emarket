package com.cristi.web.jobgram.domain.gameoflife2;

import java.util.HashSet;
import java.util.Set;

import static com.cristi.web.jobgram.domain.gameoflife2.State.ALIVE;
import static java.util.Arrays.asList;

public class GameOfLife {
    public static void main(String[] args) {
        int noOfGenerations = 3;
        LivingCells population = someLivingCells();
        GridPrinter printer = new GridPrinter(population);
        PopulationEvolver evolver = new PopulationEvolver();
        System.out.println(printer.printGrid());
        System.out.println("Starting evolution...");
        for (int i = 0; i < noOfGenerations; i++) {
            System.out.println("Generation " + i);
            population = evolver.evolve(population);
            printer = printer.newGrid(population);
            System.out.println(printer.printGrid());

        }
    }

    private static LivingCells someLivingCells() {
        Set<Cell> livingCellsInternals = new HashSet<>(asList(
                new Cell(ALIVE, new Coord(0, 1)),
                new Cell(ALIVE, new Coord(1, 1)),
                new Cell(ALIVE, new Coord(2, 1))
        ));
        return new LivingCells(livingCellsInternals, 4);
    }
}
