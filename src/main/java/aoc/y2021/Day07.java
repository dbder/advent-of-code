package aoc.y2021;

import aoc.AU;

import java.util.Arrays;
import java.util.List;

public class Day07 extends AU {

    public static void main(String[] args) {
        var input = Arrays.stream(getInputAsString("src/aoc/y2021/input/day07-2").split(","))
                .map(Integer::parseInt).sorted().toList();


        solveQ1(input);
        solveQ2(input);

    }

    static void solveQ2(List<Integer> input) {

        int[] counts = new int[2000];

        for (Integer i : input) {
            counts[i]++;
        }

        int min = Integer.MAX_VALUE;
        for (int x = 0; x < counts.length; x++) {
            if (counts[x] != 0) {
                int tmp = 0;
                for (int y = 0; y < counts.length; y++) {
                    if (y != x && counts[y] != 0) {
                        int dist = Math.abs(y - x);
                        tmp += ((dist * (dist + 1)) / 2) * counts[y];
                    }
                }
                if (tmp < min) {
                    min = tmp;
                }
            }
        }
        println("Day 07 Q2: " + min);
    }


    static void solveQ1(List<Integer> input) {

        int[] counts = new int[2000];

        for (Integer i : input) {
            counts[i]++;
        }

        int p1 = 0;
        int p2 = 1999;
        int count = 0;

        while (p1 != p2) {
            if (counts[p1] < counts[p2]) {
                count += counts[p1];
                counts[p1 + 1] += counts[p1++];
            } else {
                count += counts[p2];
                counts[p2 - 1] += counts[p2--];
            }
        }

        println("Day 07 Q1: " + count);
    }
}

