package aoc.y2021;

import aoc.AU;

import java.util.Arrays;

public class Day06 extends AU {

    @Override
    protected String getDay() {
        return "06";
    }

    public static void main(String[] args) {

        new Day06();

    }

    Day06() {
        var input = Arrays.stream(getInputLine()
                        .split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
        solveQ1(input);
        solveQ1(input);
    }

    static void solveQ2(int[] input) {
        println("Day 06 Q2: " + fishAfterNDays(input, 256));
    }

    static void solveQ1(int[] input) {
        println("Day 06 Q1: " + fishAfterNDays(input, 80));
    }

    static long fishAfterNDays(int[] input, int n) {
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

