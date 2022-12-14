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
        new Day14(null, true);
        if (!testData1.get().isEmpty()) new Day14(testData1.get(), false);
        new Day14(null, false);
    }

    static Supplier<List<String>> testData1 = () -> """
            498,4 -> 498,6 -> 496,6
            503,4 -> 502,4 -> 502,9 -> 494,9
            """.lines().collect(toList());

    Object solveQ2(List<String> input) {
        var result = 0L;

        var set = new HashSet<V2>();

        for (var line : input) {
            var split = line.split(" -> ");
            var last = toInts(split[0]);
            var lastV = V2.of(last[1], last[0]);

            set.add(lastV);
            for (var part : split) {
                var nextints = toInts(part);
                var nextV = V2.of(nextints[1], nextints[0]);

                if (nextV.equals(lastV)) continue;


                int minr = min(lastV.row(), nextV.row());
                int maxr = Math.max(lastV.row(), nextV.row());
                int minc = min(lastV.col(), nextV.col());
                int maxc = Math.max(lastV.col(), nextV.col());

                for (int r = minr; r <= maxr; r++) {
                    for (int c = minc; c <= maxc; c++) {
                        set.add(V2.of(r, c));
                    }
                }
                lastV = nextV;
            }
        }

        var max = set.stream()
                .mapToInt(V2::row)
                .max()
                .orElse(0) + 2;

        for (int c = -1000; c <= 1000; c++) set.add(V2.of(max, c));

        while (true) {
            var sand = new V2(0, 500);
            if (set.contains(sand)) return result;
            boolean moved = true;
            result++;
            while (moved) {
                var down = sand.up();
                if (!set.contains(down)) {
                    sand = down;
                } else if (!set.contains(down.left())) {
                    sand = down.left();
                } else if (!set.contains(down.right())) {
                    sand = down.right();
                } else {
                    moved = false;
                }
            }
            set.add(sand);
        }
    }

    Object solveQ1(List<String> input) {
        var result = 0L;

        var set = new HashSet<V2>();

        for (var line : input) {
            var split = line.split(" -> ");
            var last = toInts(split[0]);
            var lastV = V2.of(last[1], last[0]);

            set.add(lastV);
            for (var part : split) {
                var nextints = toInts(part);
                var nextV = V2.of(nextints[1], nextints[0]);

                if (nextV.equals(lastV)) continue;


                int minr = min(lastV.row(), nextV.row());
                int maxr = Math.max(lastV.row(), nextV.row());
                int minc = min(lastV.col(), nextV.col());
                int maxc = Math.max(lastV.col(), nextV.col());

                for (int r = minr; r <= maxr; r++) {
                    for (int c = minc; c <= maxc; c++) {
                        set.add(V2.of(r, c));
                    }
                }
                lastV = nextV;
            }
        }

        while (true) {
            var sand = new V2(0, 500);
            boolean moved = true;
            result++;
            while (moved) {
                if (sand.row() > 1000) return result -1;
                var down = sand.up();
                if (!set.contains(down)) {
                    sand = down;
                } else if (!set.contains(down.left())) {
                    sand = down.left();
                } else if (!set.contains(down.right())) {
                    sand = down.right();
                } else {
                    moved = false;
                }
            }
            set.add(sand);
        }
    }
}

