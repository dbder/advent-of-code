package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;
import aoc.misc.V3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class Day18 extends AU {

    Day18(List<String> testData, boolean q1) {
        switch ((q1 ? "q1" : "q2") + "-" + (testData == null ? "real" : "test")) {
            case "q1-test" -> println("Test Q1: " + solveQ1(testData));
            case "q1-real" -> println("Real Q1: " + solveQ1(getInputLines()));
            case "q2-test" -> println("Test Q2: " + solveQ2(testData));
            case "q2-real" -> println("Real Q2: " + solveQ2(getInputLines()));
            default -> throw new AocException("Unknown case");
        }
    }

    public static void main(String[] args) {
        if (!testData1.get().isEmpty()) new Day18(testData1.get(), true);
        new Day18(null, true);
        if (!testData1.get().isEmpty()) new Day18(testData1.get(), false);
        new Day18(null, false);
    }

    static Supplier<List<String>> testData1 = () -> """
            2,2,2
            1,2,2
            3,2,2
            2,1,2
            2,3,2
            2,2,1
            2,2,3
            2,2,4
            2,2,6
            1,2,5
            3,2,5
            2,1,5
            2,3,5
            """.lines().collect(toList());

    Set<V3> cubes = new HashSet<>();
    V3 fieldsize = new V3(20, 20, 20);

    void parseCubes(List<String> input) {
        for (var line : input) {
            var ints = toInts(line);
            var cube = new V3(ints[0], ints[1], ints[2]);
            cubes.add(cube);
        }
    }

    Object solveQ2(List<String> input) {

        parseCubes(input);

        for (int x = 0; x <= 20; x++) {
            for (int y = 0; y <= 20; y++) {
                for (int z = 0; z <= 20; z++) {
                    var cube = new V3(x, y, z);
                    if (isinDrop(cube)) cubes.add(cube);
                }
            }
        }

        return countVisibleFaces();
    }


    Object solveQ1(List<String> input) {

        parseCubes(input);

        return countVisibleFaces();
    }

    long countVisibleFaces() {
        return cubes.stream()
                .map(V3::getNeighbours6)
                .mapToLong(n -> n.stream().filter(c -> !cubes.contains(c)).count())
                .sum();
    }

    boolean isinDrop(V3 cube) {
        var level = new HashSet<V3>();
        Set<V3> visited = new HashSet<>();
        level.add(cube);
        while (!level.isEmpty()) {
            var nextLevel = new HashSet<V3>();
            for (var c : level) {
                if (visited.contains(c) || cubes.contains(c)) continue;
                if (!fieldsize.contains(c)) return false;
                nextLevel.addAll(c.getNeighbours6());
            }
            visited.addAll(level);
            level = nextLevel;
        }
        return true;
    }
}
