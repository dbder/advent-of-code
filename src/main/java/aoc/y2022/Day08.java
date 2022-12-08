package aoc.y2022;

import aoc.AU;

import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class Day08 extends AU {

    public static void main(String[] args) {


//        if (!testData1.get().isEmpty()) new Day08(testData1.get(), true);
//        if (!testData2.get().isEmpty()) new Day08(testData2.get(), true);
        new Day08(null, true);
        if (true) return;
        if (!testData1.get().isEmpty()) new Day08(testData1.get(), false);
        if (!testData2.get().isEmpty()) new Day08(testData2.get(), false);
        new Day08(null, false);
    }

    static Supplier<List<String>> testData1 = () -> """
            30373
            25512
            65332
            33549
            35390
            """.lines().collect(toList());

    static Supplier<List<String>> testData2 = () -> """
            """.lines().collect(toList());

    Day08(List<String> testData, boolean q1) {
        switch ((q1 ? "q1" : "q2") + "-" + (testData == null ? "real" : "test")) {
            case "q1-test" -> println("Test Q1: " + solveQ1(testData));
            case "q1-real" -> println("Real Q1: " + solveQ1(getInputLines()));
            case "q2-test" -> println("Test Q2: " + solveQ2(testData));
            case "q2-real" -> println("Real Q2: " + solveQ2(getInputLines()));
        }
    }


    Object solveQ2(List<String> input) {
        var result = 0L;


        //DAY2 !!
        return result;
    }

    Object solveQ1(List<String> input) {
        var result = 0L;

        var grid = intGrid(input);

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {


                int left = getLeft(grid, r, c);
                int right = getRight(grid, r, c);
                int up = getUp(grid, r, c);
                int down = getDown(grid, r, c);
                int mul = left * right * up * down;
//                System.out.print(left + "-" + right + "-" + up + "-" + down + " mul " + mul + "|");

                result = Math.max(result, mul);

            }
            println("");
        }

        return result;
    }

    private int getLeft(int[][] grid, int r, int c) {
        if (c == 0) return 0;
        int count = 0;
        int height = grid[r][c];
        int i = c - 1;
        while (i >= 0) {
            count++;
            if (grid[r][i] >= height) return count;
            i--;
        }
        return count;
    }

    private int getRight(int[][] grid, int r, int c) {
        if (c == 0) return 0;
        int count = 0;
        int height = grid[r][c];
        int i = c + 1;
        while (i < grid[0].length) {
            count++;
            if (grid[r][i] >= height) return count;
            i++;
        }
        return count;
    }

    private int getUp(int[][] grid, int r, int c) {
        if (c == 0) return 0;
        int count = 0;
        int height = grid[r][c];
        int i = r - 1;
        while (i >= 0) {
            count++;
            if (grid[i][c] >= height) return count;
            i--;
        }
        return count;
    }

    private int getDown(int[][] grid, int r, int c) {
        if (c == 0) return 0;
        int count = 0;
        int height = grid[r][c];
        int i = r + 1;
        while (i < grid.length) {
            count++;
            if (grid[i][c] >= height) return count;
            i++;
        }
        return count;
    }


}

