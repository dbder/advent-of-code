package aoc.utils;

import aoc.misc.V2;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface GridUtil {

    int[][] TPS8 = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}, {-1, -1}, {1, 1}, {1, -1}, {-1, 1}};
    int[][] TPS4 = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    List<V2> TPS4List = Arrays.stream(TPS4).map(V2::of).collect(Collectors.toList());
    List<V2> TPS8List = Arrays.stream(TPS8).map(V2::of).collect(Collectors.toList());

    default boolean isIN(int row, int col, int[][] mx) {
        int rows = mx.length;
        int cols = mx[0].length;
        return row >= 0 && col >= 0 && row < rows && col < cols;
    }

    default boolean isIN(V2 v2, int[][] mx) {
        return isIN(v2.row(), v2.col(), mx);
    }

    default char[][] charGrid(List<String> lines) {
        int max = lines.stream().mapToInt(String::length).max().orElse(0);
        char[][] grid = new char[lines.size()][max];
        for (int r = lines.size()-1; r >=0 ; r--) {
            Arrays.fill(grid[r], ' ');
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

    default void println(char[][] grid) {
        System.out.println();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                System.out.print(grid[r][c]);
            }
            System.out.println();
        }
    }

    default void println(int[][] grid) {
        System.out.println();
        for (int r = 0; r < grid.length; r++) {
            System.out.println(Arrays
                    .stream(grid[r])
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(""))
            );
        }
    }

    default void print3d(int[][][] grid) {
        System.out.println();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                System.out.print(Arrays.toString(grid[r][c]));

            }
            System.out.println();
        }
    }



}
