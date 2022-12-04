package aoc.y2019;

import aoc.AU;
import aoc.misc.V2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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
        var set = new HashMap<V2, Integer>();
        var last = new V2(0, 0);
        var ps = input.get(0).split(",");

        // plot first wire with stepcount
        int steps = 0;
        for (var p : ps) {

            var dir = p.charAt(0);
            var len = toIntList(p).get(0);

            for (int i = 1; i <= len; i++) {
                steps++;
                last = last.move(dir);
                if (i != len && set.get(last) == null) set.put(last, steps);
            }

        }

        // get intersections with stepcount
        var list = new ArrayList<Integer>();
        steps = 0;
        ps = input.get(1).split(",");
        last = new V2(0, 0);
        for (var p : ps) {
            var dir = p.charAt(0);
            var len = toIntList(p).get(0);

            for (int i = 1; i <= len; i++) {
                steps++;
                last = last.move(dir);
                if (set.get(last) != null) list.add(steps + set.get(last));
            }
        }

        return min(list);
    }

    Object solveQ1() {
        var input = getInputLines();
        var lineSegments = new HashSet<V2>();
        var current = new V2(0, 0);
        var instructions = input.get(0).split(",");

        // plot line 1
        for (var in : instructions) {
            var dir = in.charAt(0);
            var len = toIntList(in).get(0);

            for (int i = 1; i <= len; i++) {
                current = current.move(dir);
                if (i != len) lineSegments.add(current);
            }
        }

        // get intersections
        var list = new ArrayList<Integer>();
        instructions = input.get(1).split(",");
        current = new V2(0, 0);
        for (var in : instructions) {
            var dir = in.charAt(0);
            var len = toIntList(in).get(0);

            for (int i = 1; i <= len; i++) {
                current = current.move(dir);
                if (lineSegments.contains(current)) list.add(current.manhattan());
            }
        }

        return min(list);
    }

}

