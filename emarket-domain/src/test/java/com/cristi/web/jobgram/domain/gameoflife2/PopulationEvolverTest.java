package com.cristi.web.jobgram.domain.gameoflife2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PopulationEvolverTest {

    private final LivingCellsMotherObject livingCellsMotherObject = new LivingCellsMotherObject();

    @Test
    public void evolve() {
        LivingCells someLivingCells = livingCellsMotherObject.someLivingCells();
        PopulationEvolver evolver = new PopulationEvolver();
        LivingCells evolvedLivingCells = evolver.evolve(someLivingCells);
        assertThat(evolvedLivingCells.getCells()).isEmpty();
    }
}
