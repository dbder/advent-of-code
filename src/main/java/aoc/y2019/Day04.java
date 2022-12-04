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

    int start = 356261;
    int end = 846303;

    Object solveQ1(List<String> input) {
        var result = 0L;


        for (int i = start; i <= end; i++) {
            if (isValid(i)) {
                result++;
            }
        }

        return result;
    }

    private boolean isValid(int i) {

        var s = String.valueOf(i);
        var map = mapCountChars(s);
        if (map.keySet().stream().filter(j -> j == 2).count() != 1) return false;

        boolean hasDouble = false;
        for (int k = 1; k < s.length(); k ++) {
            if (s.charAt(k) < s.charAt(k-1)) return false;
            if (s.charAt(k) == s.charAt(k-1)) hasDouble = true;
        }
        return hasDouble;
    }

}



