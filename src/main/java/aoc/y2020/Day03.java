package aoc.y2020;

import aoc.AU;
import aoc.V2;

import java.util.ArrayList;

public class Day03 extends AU {

    public static void main(String[] args) {
        new Day03();
    }



    Day03() {
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + solveQ2());
    }

    Object solveQ2() {
        var input = getInputLines();

        for (int i = 0; i < input.size(); i++) {
            input.set(i, input.get(i).repeat(100));
        }

        int[][] ints = new int[][]{{1, 1}, {3, 1}, {5, 1}, {7, 1}, {1, 2}};
        var list = new ArrayList<Integer>();
        for (var arr : ints) {
            int count = 0;

            var grid = charGrid(input);

            var pos = V2.of(0, 0);

            while (pos.isIN(grid)) {
                if (grid[pos.row()][pos.col()] == '#') {
                    count++;
                }
                pos = pos.add(arr[1], arr[0]);
            }
            list.add(count);
        }

        return list.stream().mapToLong(i -> i).reduce(1L, (a, b) -> a * b);
    }

    Object solveQ1() {
        var result = 0L;
        var input = getInputLines();

        input.replaceAll(s -> s.repeat(100));

        var grid = charGrid(input);
        var pos = V2.of(0, 0);

        while (pos.isIN(grid)) {
            if (grid[pos.row()][pos.col()] == '#') {
                result++;
            }
            pos = pos.add(1, 3);
        }

        return result;
    }

}

