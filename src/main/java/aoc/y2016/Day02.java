package aoc.y2016;

import aoc.AU;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Day02 extends AU {
    private static final Logger log = LogManager.getLogger(Day02.class);

    @Override
    protected String getDay() {
        return "02";
    }


    public static void main(String[] args) {
        new Day02();
    }

    Day02() {
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + solveQ2());
    }

    Object solveQ2() {
        var input = getInputLines();

        var arr = new char[][]{
                {0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 2, 3, 4, 0, 0},
                {0, 5, 6, 7, 8, 9, 0},
                {0, 0, 'A', 'B', 'C', 0, 0},
                {0, 0, 0, 'D', 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0},

        };

        int row = 3;
        int col = 1;
        StringBuilder sb = new StringBuilder();
        for (var line : input) {
            for (var c : line.toCharArray()) {
                switch (c) {
                    case 'U' -> {
                        if (arr[row-1][col] != 0) {
                            row--;
                        }
                    }
                    case 'D' -> {
                        if (arr[row+1][col] != 0) {
                            row++;
                        }
                    }
                    case 'L' -> {
                        if (arr[row][col-1] != 0) {
                            col--;
                        }
                    }
                    case 'R' -> {
                        if (arr[row][col+1] != 0) {
                            col++;
                        }
                    }
                }
            }
            if (arr[row][col] < 10) {
                sb.append((char)('0' +arr[row][col]));
            } else {
                sb.append(arr[row][col]);
            }
        }
        return sb.toString();
    }

    Object solveQ1() {
        var input = getInputLines();

        var arr = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int row = 1;
        int col = 1;
        StringBuilder sb = new StringBuilder();
        for (var line : input) {
            for (var c : line.toCharArray()) {
                switch (c) {
                    case 'U' -> {
                        if (row > 0) {
                            row--;
                        }
                    }
                    case 'D' -> {
                        if (row < 2) {
                            row++;
                        }
                    }
                    case 'L' -> {
                        if (col > 0) {
                            col--;
                        }
                    }
                    case 'R' -> {
                        if (col < 2) {
                            col++;
                        }
                    }
                }
            }
            sb.append(arr[row][col]);
        }
        return sb.toString();
    }

}

