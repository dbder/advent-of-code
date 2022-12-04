package aoc.y2022;

import aoc.AU;
import aoc.misc.Range1d;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Day04 extends AU {

    public static void main(String[] args) {new Day04();}

    List<String> testData1 = """
            2-4,6-8
            2-3,4-5
            5-7,7-9
            2-8,3-7
            6-6,4-6
            2-6,4-8
            """.lines().collect(toList());

    Day04() {
        if (!testData1.isEmpty()) println("Test Q1: " + solveQ1(testData1));
        println("Day " + getDay() + " Q1: " + solveQ1(getInputLines()));

        if (!testData1.isEmpty()) println("Test Q2: " + solveQ1(testData1));
        println("Day " + getDay() + " Q2: " + solveQ2(getInputLines()));
    }

    Object solveQ2(List<String> input) {
        var result = 0L;
        for (var ln : input) {
            var ints = toInts(ln.replace("-", " "));
            if (Range1d.of(ints[0], ints[1]).overlaps(Range1d.of(ints[2], ints[3]))) result++;
        }
        return result;
    }

    Object solveQ1(List<String> input) {
        var result = 0L;
        for (var ln : input) {
            var ints = toInts(ln.replace("-", " "));
            var v1 = Range1d.of(ints[0], ints[1]);
            var v2 = Range1d.of(ints[2], ints[3]);
            if (v1.contains(v2) || v2.contains(v1)) result++;
        }
        return result;
    }

}

