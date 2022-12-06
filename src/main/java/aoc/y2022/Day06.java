package aoc.y2022;

import aoc.AU;

import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class Day06 extends AU {

    public static void main(String[] args) {


        if (!testData1.get().isEmpty()) new Day06(testData1.get(), true);
        if (!testData2.get().isEmpty()) new Day06(testData2.get(), true);
        new Day06(null, true);
        if (!testData1.get().isEmpty()) new Day06(testData1.get(), false);
        if (!testData2.get().isEmpty()) new Day06(testData2.get(), false);
        new Day06(null, false);
    }

    static Supplier<List<String>> testData1 = () -> """
            bvwbjplbgvbhsrlpgdmjqwftvncz
            """.lines().collect(toList());

    static Supplier<List<String>> testData2 = () -> """
            nppdvjthqldpwncqszvftbrmjlhg
            """.lines().collect(toList());

    Day06(List<String> testData, boolean q1) {
        solveQ1(getInputLines());

        switch ((q1 ? "q1" : "q2") + "-" + (testData == null ? "real" : "test")) {
            case "q1-test" -> println("Test Q1: " + solveQ1(testData));
            case "q1-real" -> println("Real Q1: " + solveQ1(getInputLines()));
            case "q2-test" -> println("Test Q2: " + solveQ2(testData));
            case "q2-real" -> println("Real Q2: " + solveQ2(getInputLines()));
        }
    }

    Object solveQ2(List<String> input) {
        var result = 0L;
        var str = input.get(0);
        for( int i = 14; i <= str.length(); i++) {
            var tmp = str.substring(i - 14, i);
            var c = mapCharCount(tmp).keySet().size();
            if (c == 14) {
                return i;
            }
        }
        return result;
    }

    Object solveQ1(List<String> input) {
        var result = 0L;
        var str = input.get(0);
        for( int i = 4; i <= str.length(); i++) {
            if (mapCharCount(str.substring(i - 4, i)).keySet().size() == 4) return i;
        }
        return result;
    }
}

