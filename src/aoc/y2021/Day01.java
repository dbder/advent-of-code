package aoc.y2021;

import aoc.AU;

public class Day01 extends AU {

    public static void main(String[] args) {

        var input = getInputAsStream("src/aoc/y2021/input/day01")
                .mapToInt(Integer::parseInt)
                .toArray();

        solveQ1(input);
        solveQ2(input);

    }

    private static void solveQ2(int[] input) {
        int count = 0;
        for (int x = 3; x < input.length; x++ ) {
            int x1 = input[x-3];
            int x2 = input[x-2];
            int x3 = input[x-1];
            int x4 = input[x];

            if (x1 + x2 + x3 < x2 + x3 + x4) {
                count++;
            }
        }

        print("Q2: " + count);
    }

    static void solveQ1(int[] input) {
        int count = 0;
        for (int x = 1; x < input.length; x++ ) {
            if (input[x-1] < input[x]) {
                count++;
            }
        }

        print("Q1: " + count);
    }
}

