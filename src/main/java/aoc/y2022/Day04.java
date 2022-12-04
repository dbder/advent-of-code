package aoc.y2022;

import aoc.AU;

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
            if (ints[1] >= ints[2] && ints[0] <= ints[3]) {
                result++;
            }
        }
        return result;
    }

    Object solveQ1(List<String> input) {
        var result = 0L;
        for (var ln : input) {
            var ints = toInts(ln.replace("-", " "));
            if (ints[0] <= ints[2] && ints[1] >= ints[3]) {
                result++;
            } else
            if (ints[2] <= ints[0] && ints[3] >= ints[1]) {
                result++;
            }
        }
        return result;
    }

}

