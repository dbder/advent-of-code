package aoc.y2021;

import aoc.AU;

import java.util.Arrays;
import java.util.List;

public class Day11 extends AU {

    @Override
    protected String getDay() {
        return "11";
    }

    static int count = 0;

    public static void main(String[] args) {
        new Day11();
    }

    Day11() {
        var input = getInputLines();
        solveQ1(input);
        solveQ2(input);
    }

    /**
     *  What is the first step during which all octopuses flash?
     */
    void solveQ2(List<String> input) {
        int[][] mx = parse2DMatrixSingleDigit(input);

        int iterations = 1000;
        for (int x = 1; x <= iterations; x++) {

            if (incrementAll(mx)) {
                println("Day 11 Q2: " + x);
                return;
            }
        }
    }

    /**
     * Given the starting energy levels of the dumbo octopuses in your cavern, simulate 100 steps. How many total flashes are there after 100 steps?
     */
    void solveQ1(List<String> input) {
        var mx = parse2DMatrixSingleDigit(input);
        for (int x = 1; x <= 100; x++) {
            incrementAll(mx);
        }
        println("Day 11 Q1: " + count);
    }

    boolean incrementAll(int[][] mx) {
        for (int r = 0; r < mx.length; r++) {
            for (int c = 0; c < mx[0].length; c++) {
                increment(mx, r, c);
            }
        }
        return resetFlashed(mx);
    }

    boolean resetFlashed(int[][] mx) {
        boolean all = true;
        for (int r = 0; r < mx.length; r++) {
            for (int c = 0; c < mx[0].length; c++) {
                if (mx[r][c] == -1) {
                    mx[r][c] = 0;
                } else {
                    all = false;
                }
            }
        }
        return all;
    }

    void increment(int[][] mx, int r, int c) {
        if (!isInGrid(r,c,mx)) return;
        if (mx[r][c] != -1) {
            mx[r][c]++;
            if (mx[r][c] == 10) {
                count++;
                mx[r][c] = -1;
                incrementNeighbors(mx, r, c);
            }
        }
    }

    void incrementNeighbors(int[][] mx, int r, int c) {
        Arrays.stream(TPS8).forEach(m -> increment(mx, r + m[0], c + m[1]));
    }
}

