package aoc.utils;

import java.util.List;

public interface GridUtil {

    int[][] TPS8 = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}, {-1, -1}, {1, 1}, {1, -1}, {-1, 1}};
    int[][] TPS4 = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    default boolean isInGrid(int row, int col, int[][] mx) {
        int rows = mx.length;
        int cols = mx[0].length;
        return row >= 0 && col >= 0 && row < rows && col < cols;
    }

    default char[][] charGrid(List<String> lines) {
        char[][] grid = new char[lines.size()][lines.get(0).length()];
        for (int r = 0; r < lines.size(); r++) {
            for (int c = 0; c < lines.get(r).length(); c++) {
                grid[r][c] = lines.get(r).charAt(c);
            }
        }
        return grid;
    }

    default int[][] intGrid(List<String> lines) {
        int[][] grid = new int[lines.size()][lines.get(0).length()];
        for (int r = 0; r < lines.size(); r++) {
            for (int c = 0; c < lines.get(r).length(); c++) {
                grid[r][c] = lines.get(r).charAt(c) - '0';
            }
        }
        return grid;
    }

    default void print(char[][] grid) {
        System.out.println();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                System.out.print(grid[r][c]);
            }
            System.out.println();
        }
    }

    default void print(int[][] grid) {
        System.out.println();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                System.out.print(grid[r][c]);
            }
            System.out.println();
        }
    }
}
