package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;
import aoc.misc.V2;

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
        var result = 0L;

        var current = new V2(0,0);
//        var tail = new V2(0,0);
        var visited = new HashSet<V2>();

        V2[] knots = new V2[10];
        for (int i = 0; i < knots.length; i++) {
            knots[i] = new V2(0,0);
        }


        for (var line : input) {
            var split =line.split(" ");
            var dir = split[0];
            var steps = Integer.parseInt(split[1]);

            for (int i = 0; i < steps; i++) {

                current = current.move(dir);
                knots[0] = current;
                for (int j = 1; j < knots.length; j++) {
                    var tail = knots[j];
                    var cur = knots[j-1];
                    int r = 0;
                    int c = 0;
                    int rd = Math.abs(cur.row() - tail.row());
                    int cd = Math.abs(cur.col() - tail.col());
                    boolean distanced = rd == 2 || cd == 2;
                    if (cur.col() == tail.col() && cur.row() == tail.row()) {
                        continue;
                    }
                    if (rd == 1 && cd == 1) {
                        continue;
                    }

                    if (cur.row() != tail.row() && (distanced || Math.abs(cur.row() - tail.row()) > 1)) {

                        if (tail.row() < cur.row()) {
                            r = 1;
                        } else {
                            r = -1;
                        }

                    }
                    if (cur.col() != tail.col() && (distanced || Math.abs(cur.col() - tail.col()) > 1)) {
                        if (tail.col() < cur.col()) {
                            c = 1;
                        } else {
                            c = -1;
                        }
                    }
                    var next = tail.add(r, c);
                    knots[j] = next;
                    if (j == knots.length-1) {
                        visited.add(tail);
                    }

                }
//                println(knots);
            }


        }

        System.out.println(visited);

        int[][] grid = new int[400][400];

//        for (var v : visited) {
//            grid[-v.row()+ 70][v.col() + 70] = 1;
//        }
//
//        println(grid);

        return visited.size();
    }
}

