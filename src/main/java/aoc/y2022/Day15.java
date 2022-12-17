package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;
import aoc.misc.V2;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class Day15 extends AU {

    Day15(List<String> testData, boolean q1) {
        switch ((q1 ? "q1" : "q2") + "-" + (testData == null ? "real" : "test")) {
            case "q1-test" -> println("Test Q1: " + solveQ1(testData));
            case "q1-real" -> println("Real Q1: " + solveQ1(getInputLines()));
            case "q2-test" -> println("Test Q2: " + solveQ2(testData));
            case "q2-real" -> println("Real Q2: " + solveQ2(getInputLines()));
            default -> throw new AocException("Unknown case");
        }
    }

    public static void main(String[] args) {
//        if (!testData1.get().isEmpty()) new Day15(testData1.get(), true);
        new Day15(null, true);
        if (true) return;
        if (!testData1.get().isEmpty()) new Day15(testData1.get(), false);
        new Day15(null, false);
    }

    static Supplier<List<String>> testData1 = () -> """
            Sensor at x=2, y=18: closest beacon is at x=-2, y=15
            Sensor at x=9, y=16: closest beacon is at x=10, y=16
            Sensor at x=13, y=2: closest beacon is at x=15, y=3
            Sensor at x=12, y=14: closest beacon is at x=10, y=16
            Sensor at x=10, y=20: closest beacon is at x=10, y=16
            Sensor at x=14, y=17: closest beacon is at x=10, y=16
            Sensor at x=8, y=7: closest beacon is at x=2, y=10
            Sensor at x=2, y=0: closest beacon is at x=2, y=10
            Sensor at x=0, y=11: closest beacon is at x=2, y=10
            Sensor at x=20, y=14: closest beacon is at x=25, y=17
            Sensor at x=17, y=20: closest beacon is at x=21, y=22
            Sensor at x=16, y=7: closest beacon is at x=15, y=3
            Sensor at x=14, y=3: closest beacon is at x=15, y=3
            Sensor at x=20, y=1: closest beacon is at x=15, y=3
            """.lines().collect(toList());

    static Supplier<List<String>> testData2 = () -> """
            """.lines().collect(toList());

    Object solveQ2(List<String> input) {
        var result = 0L;
        //DAY2 !!
        return result;
    }

    Object solveQ1(List<String> input) {

        long start = System.currentTimeMillis();
        var map = new HashMap<V2, Integer>();

        for (var line : input) {
            var ints = toInts(line);
            var va = new V2(ints[1], ints[0]);
            var vb = new V2(ints[3], ints[2]);
            var dist = va.manhattan(vb);
            map.put(va, dist);
        }


        for (var va : map.keySet()) {
            var dist = map.get(va);

            var top = va.up(dist + 1);
            var bottom = va.down(dist + 1);
            var left = va.left(dist + 1);
            var right = va.right(dist + 1);


            var tmp = top;

            while (!tmp.equals(left)) {
                int out = isWithinBounds(tmp);
                int range = 0;
                if (out == 0) {
                    range = isWithin(map, tmp);
                }

                if (out == 0 && range == -1) {
                    System.out.println("time: " + (System.currentTimeMillis() - start) + "ms");
                    return calc(tmp);
                }
                if (out > 0) range = out;
                range /= 2;
                if (range == 0) {
                    tmp = tmp.left().down();
                    continue;
                }
                if (tmp.col() - range <= left.col()) {
                    tmp = left;
                } else {
                    tmp = tmp.add(-range, -range);
                }
            }
            while (!tmp.equals(bottom)) {
                int out = isWithinBounds(tmp);
                int range = 0;
                if (out == 0) {
                    range = isWithin(map, tmp);
                }

                if (out == 0 && range == -1) {
                    System.out.println("time: " + (System.currentTimeMillis() - start) + "ms");
                    return calc(tmp);
                }
                if (out > 0) range = out;
                range /= 2;
                if (range == 0) {
                    tmp = tmp.right().down();
                    continue;
                }
                if (tmp.row() - range <= bottom.row()) {
                    tmp = bottom;
                } else {
                    tmp = tmp.add(-range, range);
                }
            }
            while (!tmp.equals(right)) {
                int out = isWithinBounds(tmp);
                int range = 0;
                if (out == 0) {
                    range = isWithin(map, tmp);
                }

                if (out == 0 && range == -1) {
                    System.out.println("time: " + (System.currentTimeMillis() - start) + "ms");
                    return calc(tmp);
                }
                if (out > 0) range = out;
                range /= 2;
                if (range == 0) {
                    tmp = tmp.right().up();
                    continue;
                }
                if (tmp.col() + range >= right.col()) {
                    tmp = right;
                } else {
                    tmp = tmp.add(range, range);
                }
            }
            while (!tmp.equals(top)) {
                int out = isWithinBounds(tmp);
                int range = 0;
                if (out == 0) {
                    range = isWithin(map, tmp);
                }

                if (out == 0 && range == -1) {
                    System.out.println("time: " + (System.currentTimeMillis() - start) + "ms");
                    return calc(tmp);
                }
                if (out > 0) range = out;
                range /= 2;
                if (range == 0) {
                    tmp = tmp.left().up();
                    continue;
                }
                if (tmp.row() + range <= top.row()) {
                    tmp = top;
                } else {
                    tmp = tmp.add(range, -range);
                }
            }

        }

        System.out.println("time: " + (System.currentTimeMillis() - start) + "ms");

        return 0;
    }

    int isWithinBounds(V2 v) {
        int dist = 0;
        if (v.row() < -1) dist += -v.row();
        if (v.col() < -1) dist += -v.col();
        if (v.row() > 4000001) dist += v.row() - 4000000;
        if (v.col() > 4000001) dist += v.col() - 4000000;
        return dist;
    }

    BigInteger calc(V2 v) {
        System.out.println(v);
        return BigInteger.valueOf(v.col()).multiply(BigInteger.valueOf(4000000)).add(BigInteger.valueOf(v.row()));
    }

    int isWithin(HashMap<V2, Integer> map, V2 v2) {
        int dist = -1;
        for (var entry : map.entrySet()) {
            if (v2.manhattan(entry.getKey()) <= entry.getValue()) {
                dist = Math.max(dist, entry.getValue() - v2.manhattan(entry.getKey()));
            }
        }
        return dist;
    }

}

//7556396000000 too low

//    Object solveQ1(List<String> input) {
//        var result = 0L;
//
//        var set = new HashSet<V2>();
//
//        Set<V2> list = new HashSet<>();
//
////        for (int c = -10000000; c < 30000000; c++) {
////            list.add(new V2(10, c));
//////            list.add(new V2(2000000, c));
////        }
//
//
//        boolean[][] grid = new boolean[4000000][4000000];
//
//        for (int r = 0; r <= 4000000; r++) {
////            for (int c = 0; c <= 4000000; c++) {
////                list.add(new V2(r, c));
////            }
//            list.add(new V2(r, 0));
//        }
//        System.out.println("list.size() = " + list.size());
//        var beacons = new ArrayList<V2>();
//
//        for (var line : input) {
//
//            var ints = toInts(line);
//            var va = new V2 (ints[1], ints[0]);
//            var vb = new V2 (ints[3], ints[2]);
//            beacons.add(va);
//            beacons.add(vb);
//            var dist = va.manhattan(vb);
//
//            var buffer = new HashSet<V2>();
//            for (var v : list) {
//
//                if (v.manhattan(va) <= dist) {
//                    buffer.add(v);
//                }
//            }
//            list.removeAll(buffer);
//
//
//
//        }
//
//        System.out.println(list);
//
//        return set.size();
//    }