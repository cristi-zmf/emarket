package com.cristi.web.jobgram.domain.gameoflife2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.cristi.web.jobgram.domain.gameoflife2.State.ALIVE;

public class LivingCellsMotherObject {
    public LivingCellsMotherObject() {
    }


    /*
    * 0 0 0
    * 0 1 1
    * 0 0 0
    * */
    LivingCells someLivingCells() {
        Set<Cell> livingCellsInternals = new HashSet<Cell>(Arrays.asList(
                new Cell(ALIVE, new Coord(1, 1)),
                new Cell(ALIVE, new Coord(1, 2))
        ));
        return new LivingCells(livingCellsInternals, 3);
    }
}
