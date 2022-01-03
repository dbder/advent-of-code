package aoc.y2021;

import aoc.AU;

import java.util.Arrays;

public class Day06 extends AU {

    public static void main(String[] args) {

        var input = Arrays.stream(getInputAsString("src/aoc/y2021/input/day06")
                .split(","))
                .mapToInt(Integer::parseInt)
                .toArray();

        solveQ1(input);
        solveQ2(input);

    }

    static void solveQ2(int[] input) {
        print("Day 06 Q2: " + fishAfterNdays(input, 256));
    }

    static void solveQ1(int[] input) {
        print("Day 06 Q1: " + fishAfterNdays(input, 80));
    }

    static long fishAfterNdays(int[] input, int n) {
        long[] count = new long[9];

        for (int x : input) {
            count[x]++;
        }

        for (int x = 0; x < n; x++) {
            long tmp = count[0];
            System.arraycopy(count, 1, count, 0, count.length - 1);
            count[count.length - 1] = tmp;
            count[6] += tmp;
        }

        long total = 0;
        for (long c : count) {
            total += c;
        }

        return total;
    }
}

