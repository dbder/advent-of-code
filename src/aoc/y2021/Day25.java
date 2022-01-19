package aoc.y2021;

import aoc.AU;

import java.util.Arrays;
import java.util.List;

public class Day25 extends AU {

    public static void main(String[] args) {
        var input = getInputAsStream("src/aoc/y2021/input/day25")
                .toList();

        solveQ1(input);
    }

    /**
     * Find somewhere safe to land your submarine. What is the first step on which no sea cucumbers move?
     */
    static void solveQ1(List<String> input) {

        int rows = input.size();
        int cols = input.get(0).length();
        var grid = new int[rows][cols];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char ch = input.get(r).charAt(c);
                if (ch == '>') {
                    grid[r][c] = 1;
                } else if (ch == 'v') {
                    grid[r][c] = 2;
                }
            }
        }

        boolean changed = true;
        int count = 0;
        while (changed) {
            count++;
            changed = false;
            var grid2 = new int[rows][cols];
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (grid[r][c] == 1) {
                        if (grid[r][(c + 1) % cols] == 0) {
                            changed = true;
                            grid2[r][(c + 1) % cols] = 1;
                        } else {
                            grid2[r][c] = 1;
                        }
                    }
                }
            }

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    if (grid[r][c] == 2) {
                        if (grid[(r + 1) % rows][c] == 0 && grid2[(r + 1) % rows][c] == 0) {
                            changed = true;
                            grid2[(r + 1) % rows][c] = 2;
                        } else {
                            grid2[r][c] = 2;
                        }
                    }
                }
            }
            grid = grid2;

            for (int[] ints : grid) {
                println(Arrays.toString(ints));
            }
            System.out.println("-");
        }

        println("Day 25 Q1: " + count);
    }
}

