package aoc.y2017;

import aoc.AU;

import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class Day07 extends AU {

    public static void main(String[] args) {

        if (!testData1.get().isEmpty()) new Day07(testData1.get(), true);
        if (!testData2.get().isEmpty()) new Day07(testData2.get(), true);
        new Day07(null, true);
        if (true) return;
        if (!testData1.get().isEmpty()) new Day07(testData1.get(), false);
        if (!testData2.get().isEmpty()) new Day07(testData2.get(), false);
        new Day07(null, false);
    }

    static Supplier<List<String>> testData1 = () -> """
            """.lines().collect(toList());

    static Supplier<List<String>> testData2 = () -> """
            """.lines().collect(toList());

    Day07(List<String> testData, boolean q1) {
        switch ((q1 ? "q1" : "q2") + "-" + (testData == null ? "real" : "test")) {
            case "q1-test" -> println("Test Q1: " + solveQ1(testData));
            case "q1-real" -> println("Real Q1: " + solveQ1(getInputLines()));
            case "q2-test" -> println("Test Q2: " + solveQ2(testData));
            case "q2-real" -> println("Real Q2: " + solveQ2(getInputLines()));
        }
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

