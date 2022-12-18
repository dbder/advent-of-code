package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;

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
        if (!testData2.get().isEmpty()) new Day18(testData2.get(), true);
        new Day18(null, true);
        if (true) return;
        if (!testData1.get().isEmpty()) new Day18(testData1.get(), false);
        if (!testData2.get().isEmpty()) new Day18(testData2.get(), false);
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

    static Supplier<List<String>> testData2 = () -> """
            """.lines().collect(toList());

    Object solveQ2(List<String> input) {
        var result = 0L;
        //DAY2 !!
        return result;
    }

    Set<Cube> cubes = new HashSet<>();

    Object solveQ1(List<String> input) {
        var result = 0L;

        for (var line : input) {
            var ints = toInts(line);
            var cube = new Cube(ints[0], ints[1], ints[2]);
            cubes.add(cube);
        }

        System.out.println(cubes.size());

        for (int x = 0 ; x <=20; x++) {
            for (int y = 0 ; y <=20; y++) {
                for (int z = 0 ; z <=20; z++) {
                    var cube = new Cube(x, y, z);
                    if (isinDrop(cube)) {
                        cubes.add(cube);
                        System.out.println("drop " + cube);
                    } else {

                    }
                }
            }
        }

        System.out.println(cubes.size());

        for (var cube : cubes) {
            var top = new Cube(cube.x, cube.y + 1, cube.z);
            var bottom = new Cube(cube.x, cube.y - 1, cube.z);
            var left = new Cube(cube.x - 1, cube.y, cube.z);
            var right = new Cube(cube.x + 1, cube.y, cube.z);
            var front = new Cube(cube.x, cube.y, cube.z - 1);
            var back = new Cube(cube.x, cube.y, cube.z + 1);
            if (!cubes.contains(top)) result++;
            if (!cubes.contains(bottom)) result++;
            if (!cubes.contains(left)) result++;
            if (!cubes.contains(right)) result++;
            if (!cubes.contains(front)) result++;
            if (!cubes.contains(back)) result++;


        }
//
//        for (var cube : cubes) {
//            var in = isinDrop(cube);
//            if (!in) {
//                System.out.println("Not in: " + cube);
//            }
//        }


        return result;
    }

    boolean isinDrop(Cube cube) {
        if (cube.equals(new Cube(19, 19, 19))) {
            System.out.println("HERE");
        }
        var level = new HashSet<Cube>();
        Set<Cube> visited = new HashSet<>();
        level.add(cube);
        while (!level.isEmpty()) {
            var nextLevel = new HashSet<Cube>();
            for (var c : level) {
                if (visited.contains(c)) continue;
                if (!c.equals(cube) && cubes.contains(c)) continue;
                if (c.x < 0 || c.y < 0 || c.z < 0) return false;
                if (c.x > 20 || c.y > 20 || c.z > 20) return false;
                var top = new Cube(c.x, c.y + 1, c.z);
                var bottom = new Cube(c.x, c.y - 1, c.z);
                var left = new Cube(c.x - 1, c.y, c.z);
                var right = new Cube(c.x + 1, c.y, c.z);
                var front = new Cube(c.x, c.y, c.z - 1);
                var back = new Cube(c.x, c.y, c.z + 1);
                nextLevel.add(top);
                nextLevel.add(bottom);
                nextLevel.add(left);
                nextLevel.add(right);
                nextLevel.add(front);
                nextLevel.add(back);
            }
            visited.addAll(level);
            level = nextLevel;
            if (level.size() >7000) return false;
        }


        return true;
    }


    record Cube(int x, int y, int z) {

    }


}
//
//    Set<Cube> cubes = new HashSet<>();
//        for (var line : input) {
//                var ints = toInts(line);
//                var cube = new Cube(ints[0], ints[1], ints[2]);
//                cubes.add(cube);
//                }
//
//                System.out.println(cubes);
//
//                for (var cube : cubes) {
//                var top = new Cube(cube.x, cube.y + 1, cube.z);
//                var bottom = new Cube(cube.x, cube.y - 1, cube.z);
//                var left = new Cube(cube.x - 1, cube.y, cube.z);
//                var right = new Cube(cube.x + 1, cube.y, cube.z);
//                var front = new Cube(cube.x, cube.y, cube.z - 1);
//                var back = new Cube(cube.x, cube.y, cube.z + 1);
//                if (!cubes.contains(top)) result++;
//                if (!cubes.contains(bottom)) result++;
//                if (!cubes.contains(left)) result++;
//                if (!cubes.contains(right)) result++;
//                if (!cubes.contains(front)) result++;
//                if (!cubes.contains(back)) result++;
//
//
//                }
