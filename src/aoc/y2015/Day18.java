package aoc.y2015;

import aoc.AU;

import java.util.Arrays;

public class Day18 extends AU {

    public static void main(String[] args) {
        new Day18();
    }

    Day18() {
        println("Day " + getDay() + " Q1: " + solve(false));
        println("Day " + getDay() + " Q2: " + solve(true));
    }

    Object solve(boolean q2) {

        var grid = getInputLines()
                .stream()
                .map(s -> s.chars().mapToObj(c -> c == '#').toArray(Boolean[]::new))
                .toArray(Boolean[][]::new);

        fillCorners(q2, grid);

        for (int i = 0; i < 100; i++) {
            grid = step(grid, q2);
        }

        return Arrays.stream(grid)
                .mapToInt(a -> (int) Arrays.stream(a).filter(b -> b).count())
                .sum();
    }

    private void fillCorners(boolean q2, Boolean[][] grid) {
        if (q2) {
            grid[0][0] = true;
            grid[0][grid[0].length - 1] = true;
            grid[grid.length - 1][0] = true;
            grid[grid.length - 1][grid[0].length - 1] = true;
        }
    }

    private Boolean[][] step(Boolean[][] grid, boolean q2) {

        var newGrid = new Boolean[grid.length][grid[0].length];

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {

                var neighbours = countNeighbours(grid, x, y);
                if (isOn(grid, x, y)) {
                    newGrid[y][x] = neighbours == 2 || neighbours == 3;
                } else {
                    newGrid[y][x] = neighbours == 3;
                }
            }
        }
        fillCorners(q2, newGrid);
        return newGrid;
    }

    private int countNeighbours(Boolean[][] grid, int x, int y) {
        return (int) Arrays.stream(TPS8).filter(tp -> isOn(grid, x + tp[0], y + tp[1])).count();
    }

    boolean isOn(Boolean[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y];
    }

    @Override
    public String getDay() { return "18";};

}

