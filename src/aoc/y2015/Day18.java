package aoc.y2015;

import aoc.AU;

import java.util.Arrays;

public class Day18 extends AU {

    public static void main(String[] args) {
        new Day18();
    }

    Day18() {
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + solveQ2());
    }

    Object solveQ2() {
        return null;
    }

    Object solveQ1() {
        var input = getInputLines();

        var grid = input.stream().map(s -> s.chars().mapToObj(c -> c == '#').toArray(Boolean[]::new)).toArray(Boolean[][]::new);


        for (int i = 0; i < 100; i++) {
            grid = step(grid);
        }

        int count = 0;

        for (var row : grid) {
            for (var cell : row) {
                if (cell) {
                    count++;
                }
            }
        }

        var result = 0;

        return count;
    }

    private Boolean[][] step(Boolean[][] grid) {

        var newGrid = new Boolean[grid.length][grid[0].length];
        newGrid[0][0] = true;
        newGrid[0][newGrid[0].length - 1] = true;
        newGrid[newGrid.length - 1][0] = true;
        newGrid[newGrid.length - 1][newGrid[0].length - 1] = true;
        grid[0][0] = true;
        grid[0][newGrid[0].length - 1] = true;
        grid[newGrid.length - 1][0] = true;
        grid[newGrid.length - 1][newGrid[0].length - 1] = true;

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                var on = isOn(grid, x, y);
                var neighbours = countNeighbours(grid, x, y);

                if (on) {
                    newGrid[y][x] = neighbours == 2 || neighbours == 3;
                } else {
                    newGrid[y][x] = neighbours == 3;
                }
            }
        }
        newGrid[0][0] = true;
        newGrid[0][newGrid[0].length - 1] = true;
        newGrid[newGrid.length - 1][0] = true;
        newGrid[newGrid.length - 1][newGrid[0].length - 1] = true;
        return newGrid;
    }

    private int countNeighbours(Boolean[][] grid, int x, int y) {
        var count = 0;

        for (int[] tp : TPS8) {
            if (isOn(grid, x + tp[0], y + tp[1])) {
                count++;
            }
        }

        return count;
    }

    boolean isOn(Boolean[][] grid, int x, int y) {
        return x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y];
    }

    void printGrid(Boolean[][] grid) {
        for (var row : grid) {
            for (var cell : row) {
                print(cell ? '#' : '.');
            }
            println("");
        }
    }


    @Override
    public String getDay() { return "18";};

}

