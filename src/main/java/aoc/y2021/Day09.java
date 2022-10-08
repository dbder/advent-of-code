package aoc.y2021;

import aoc.AU;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day09 extends AU {

    @Override
    protected String getDay() {
        return "09";
    }

    static ArrayList<Integer> list = new ArrayList<>();


    public static void main(String[] args) {
        new Day09();

    }

    Day09() {
        var input = getInputLines();
        solveQ1(input);
        solveQ2(input);
    }

    /**
     * A basin is all locations that eventually flow downward to a single low point.
     * What do you get if you multiply together the sizes of the three largest basins?
     */
    void solveQ2(List<String> input) {
        var mx = parse2DMatrixSingleDigit(input);
        boolean[][] visited = new boolean[mx.length][mx[0].length];


        for (int x = 0; x < 9; x++) {
            checkNumber(x, mx, visited);
        }

        Collections.sort(list, (a, b) -> b - a);
        println("Day 09 Q2: " + list.get(0) * list.get(1) * list.get(2));
    }


    /**
     * Your first goal is to find the low points - the locations that are lower than any of its adjacent locations.
     * Find all of the low points on your heightmap. What is the sum of the risk levels of all low points on your heightmap?
     */
    void solveQ1(List<String> input) {
        var grid = parse2DMatrixSingleDigit(input);

        int count = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid.length; c++) {
                if (isLowest(grid, r, c)) {
                    count += getVal(grid, r, c) + 1;
                }
            }
        }
        println("Day 09 Q1: " + count);
    }

    int checkNumber(int nr, int[][] mx, boolean[][] vis) {
        int count = 0;

        for (int r = 0; r < mx.length; r++) {
            for (int c = 0; c < mx[0].length; c++) {
                int tmp = mx[r][c];
                if (vis[r][c] || tmp != nr) continue;
                int[] cnt = new int[1];
                getPuddle(r, c, tmp - 1, mx, vis, cnt);
                list.add(cnt[0]);
            }
        }

        return count;
    }


    void getPuddle(int r, int c, int depth, int[][] mx, boolean[][] vis, int[] count) {
        if (!isInGrid(r, c, mx)) return;
        if (vis[r][c]) return;
        if (mx[r][c] <= depth) return;
        if (mx[r][c] == 9) return;
        count[0]++;
        vis[r][c] = true;
        depth = mx[r][c];

        getPuddle(r - 1, c, depth, mx, vis, count);
        getPuddle(r + 1, c, depth, mx, vis, count);
        getPuddle(r, c - 1, depth, mx, vis, count);
        getPuddle(r, c + 1, depth, mx, vis, count);
    }

    int getVal(int[][] grid, int r, int c) {
        if (!isInGrid(r, c, grid)) {
            return 9;
        }
        return grid[r][c];
    }

    boolean isLowest(int[][] grid, int r, int c) {
        int low = getVal(grid, r, c);
        for (int[] ints : TPS4) {
            if (getVal(grid, r + ints[0], c + ints[1]) <= low) {
                return false;

            }
        }
        return true;
    }
}

