package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;
import aoc.misc.V2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class Day09 extends AU {

    Day09(List<String> testData, boolean q1) {
        switch ((q1 ? "q1" : "q2") + "-" + (testData == null ? "real" : "test")) {
            case "q1-test" -> println("Test Q1: " + solveQ1(testData));
            case "q1-real" -> println("Real Q1: " + solveQ1(getInputLines()));
            case "q2-test" -> println("Test Q2: " + solveQ2(testData));
            case "q2-real" -> println("Real Q2: " + solveQ2(getInputLines()));
            default -> throw new AocException("Unknown case");
        }
    }

    public static void main(String[] args) {
        if (!testData1.get().isEmpty()) new Day09(testData1.get(), true);
        if (!testData2.get().isEmpty()) new Day09(testData2.get(), true);
        new Day09(null, true);
        if (true) return;
        if (!testData1.get().isEmpty()) new Day09(testData1.get(), false);
        if (!testData2.get().isEmpty()) new Day09(testData2.get(), false);
        new Day09(null, false);
    }

    static Supplier<List<String>> testData1 = () -> """
            R 4
            U 4
            L 3
            D 1
            R 4
            D 1
            L 5
            R 2
            """.lines().collect(toList());

    static Supplier<List<String>> testData2 = () -> """
            R 5
            U 8
            L 8
            D 3
            R 17
            D 10
            L 25
            U 20
            """.lines().collect(toList());

    Object solveQ2(List<String> input) {
        var result = 0L;
        //DAY2 !!
        return result;
    }

    Object solveQ1(List<String> input) {
        var visited = new HashSet<V2>();
        V2[] knots = new V2[10];
        for (int i = 0; i < knots.length; i++) {
            knots[i] = new V2(0, 0);
        }


        for (var line : input) {
            var split = line.split(" ");
            var dir = split[0];
            var steps = Integer.parseInt(split[1]);

            for (int i = 0; i < steps; i++) {
                V2[] newknots = new V2[10];
                newknots[0] = knots[0].move(dir);
                for (int j = 1; j < knots.length; j++) {
                    var last = newknots[j - 1];
                    var cur = knots[j];


                    if (last.diffAbs(cur).row() == 2 || last.diffAbs(cur).col() == 2) {
                        newknots[j] = knots[j - 1];
                    } else {
                        newknots[j] = knots[j];
                    }
                    if (j == knots.length - 2) {
                        visited.add(newknots[j]);
                    }
                }
                knots = newknots;
            }


        }

        printGrid(visited);

        return visited.size();
    }

    static void printGrid(HashSet<V2> visited) {
        var minr = visited.stream().mapToInt(V2::row).min().orElse(0);
        var minc = visited.stream().mapToInt(V2::col).min().orElse(0);
        var maxr = visited.stream().mapToInt(V2::row).max().orElse(0);
        var maxc = visited.stream().mapToInt(V2::col).max().orElse(0);

        boolean[][] grid = new boolean[maxr + 1 - minr][maxc + 1 - minc];

        for (var v : visited) grid[v.row() - minr][v.col() - minc] = true;
        println(grid);
    }
}

