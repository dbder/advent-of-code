package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;
import aoc.misc.V2;

import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class Day14 extends AU {

    Day14(List<String> testData, boolean q1) {
        switch ((q1 ? "q1" : "q2") + "-" + (testData == null ? "real" : "test")) {
            case "q1-test" -> println("Test Q1: " + solveQ1(testData));
            case "q1-real" -> println("Real Q1: " + solveQ1(getInputLines()));
            case "q2-test" -> println("Test Q2: " + solveQ2(testData));
            case "q2-real" -> println("Real Q2: " + solveQ2(getInputLines()));
            default -> throw new AocException("Unknown case");
        }
    }

    public static void main(String[] args) {
        if (!testData1.get().isEmpty()) new Day14(testData1.get(), true);
        if (!testData2.get().isEmpty()) new Day14(testData2.get(), true);
        new Day14(null, true);
        if (true) return;
        if (!testData1.get().isEmpty()) new Day14(testData1.get(), false);
        if (!testData2.get().isEmpty()) new Day14(testData2.get(), false);
        new Day14(null, false);
    }

    static Supplier<List<String>> testData1 = () -> """
            498,4 -> 498,6 -> 496,6
            503,4 -> 502,4 -> 502,9 -> 494,9
            """.lines().collect(toList());

    static Supplier<List<String>> testData2 = () -> """
            """.lines().collect(toList());

    Object solveQ2(List<String> input) {
        var result = 0L;
        //DAY2 !!
        return result;
    }

    Object solveQ1(List<String> input) {
        var starttime = System.currentTimeMillis();
        var result = 0L;

        var set = new HashSet<V2>();

        for (var line : input) {
            var split = line.split(" -> ");
            var last = toInts(split[0]);
            var lastC = last[0];
            var lastR = last[1];
            var lastV = V2.of(lastR, lastC);

            set.add(lastV);
            for (var part : split) {
                var nextints =toInts(part);
                var nextV = V2.of(nextints[1], nextints[0]);

                if (nextV.equals(lastV)) continue;


                int minr = Math.min(lastV.row(), nextV.row());
                int maxr = Math.max(lastV.row(), nextV.row());
                int minc = Math.min(lastV.col(), nextV.col());
                int maxc = Math.max(lastV.col(), nextV.col());

                for (int r = minr; r <= maxr; r++) {
                    for (int c = minc; c <= maxc; c++) {
                        set.add(V2.of(r, c));
                    }
                }
                lastV = nextV;

            }
        }

        var max = set.stream().mapToInt(v -> v.row()).max().orElse(0) + 2;

        var maxrowa = V2.of(max, -1000);
        var maxrowb = V2.of(max, 1000);
        int minr = Math.min(maxrowa.row(), maxrowb.row());
        int maxr = Math.max(maxrowa.row(), maxrowb.row());
        int minc = Math.min(maxrowa.col(), maxrowb.col());
        int maxc = Math.max(maxrowa.col(), maxrowb.col());


        for (int r = minr; r <= maxr; r++) {
            for (int c = minc; c <= maxc; c++) {
                set.add(V2.of(r, c));
            }
        }
//        printGrid(set);

        while (true) {

            var sand = new V2(0, 500);
            if (set.contains(sand)) {
                System.out.println("time: " + (System.currentTimeMillis() - starttime));
                return result;
            }
            boolean moved = true;
            result++;
            while (moved) {
//                if (Math.abs(sand.row()) >= 1000) {
//                    return result;
//                }
                moved = false;
                var down = sand.up();
                if (!set.contains(down)) {
                    sand = down;
                    moved = true;

                    continue;
                }
                var left = down.left();
                if (!set.contains(left)) {
                    sand = left;
                    moved = true;
                    continue;
                }
                var right = down.right();
                if (!set.contains(right)) {
                    sand = right;
                    moved = true;
                    continue;
                }
            }
            set.add(sand);


        }



//        return result;
    }

    private static void printGrid(HashSet<V2> visited) {
        var minr = visited.stream().mapToInt(V2::row).min().orElse(0);
        var minc = visited.stream().mapToInt(V2::col).min().orElse(0);
        var maxr = visited.stream().mapToInt(V2::row).max().orElse(0);
        var maxc = visited.stream().mapToInt(V2::col).max().orElse(0);

        boolean[][] grid = new boolean[maxr + 1 - minr][maxc + 1 - minc];

        for (var v : visited) grid[v.row() - minr][v.col() - minc] = true;
        println(grid);
    }
}

