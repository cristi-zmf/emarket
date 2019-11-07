package com.cristi.web.jobgram.domain.gameoflife2;

import java.util.Arrays;

public class GridPrinter {
    private final Integer [][] grid;

    public GridPrinter(LivingCells cells) {
        grid = new Integer[cells.getGridDimension()][cells.getGridDimension()];
        Arrays.stream(grid).forEach(a -> Arrays.fill(a, 0));
        markLivingCells(cells);
    }

    public GridPrinter newGrid(LivingCells cells) {
        return new GridPrinter(cells);
    }

    private void markLivingCells(LivingCells cells) {
        cells.toCoords().forEach(this::markGrid);
    }

    private void markGrid(Coord coord) {
        grid[coord.getX()][coord.getY()] = 1;
    }

    public String printGrid() {
        StringBuilder builder = new StringBuilder();
        for (Integer[] integers : grid) {
            builder.append(Arrays.deepToString(integers)).append('\n');
        }
        String commaStrippedGrid = builder.toString().replaceAll(",", "");
        return commaStrippedGrid.substring(0, commaStrippedGrid.length() - 1); //elimante new line at the end
    }
}
