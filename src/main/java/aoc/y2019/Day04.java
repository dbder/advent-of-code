package aoc.y2019;

import aoc.AU;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Day04 extends AU {

    public static void main(String[] args) {
        new Day04();
    }

    List<String> testData1 = """
            """.lines().collect(toList());
    List<String> testData2 = """
            """.lines().collect(toList());

    Day04() {
        if (!testData1.isEmpty()) println("Test Q1: " + solveQ1(testData1));
        if (!testData2.isEmpty()) println("Test Q1: " + solveQ1(testData2));
        println("Day " + getDay() + " Q1: " + solveQ1(getInputLines()));

        if (!testData1.isEmpty()) println("Test Q2: " + solveQ1(testData1));
        if (!testData2.isEmpty()) println("Test Q2: " + solveQ1(testData2));
        println("Day " + getDay() + " Q2: " + solveQ2(getInputLines()));
    }

    Object solveQ2(List<String> input) {
        var result = 0L;

        return result;
    }

    Object solveQ1(List<String> input) {
        var result = 0L;

        return result;
    }

}

