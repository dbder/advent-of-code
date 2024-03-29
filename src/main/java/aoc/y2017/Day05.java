package aoc.y2017;

import aoc.AU;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Day05 extends AU {

    public static void main(String[] args) {
        new Day05();
    }

    List<String> testData1 = """
            0
            3
            0
            1
            -3
            """.lines().collect(toList());

    Day05() {
        if (!testData1.isEmpty()) println("Test Q1: " + solveQ1(testData1));
        println("Day " + getDay() + " Q1: " + solveQ1(getInputLines()));

        if (!testData1.isEmpty()) println("Test Q2: " + solveQ2(testData1));
        println("Day " + getDay() + " Q2: " + solveQ2(getInputLines()));
    }

    Object solveQ2(List<String> input) {
        var ints = input.stream().mapToInt(Integer::parseInt).toArray();

        int count = 0;
        int i = 0;
        while (i >=0 && i < ints.length) {
            count++;
            var cur = ints[i];
            if (cur == 0) {
                ints[i] = 1;
                continue;
            }
            if (cur > 2) {
                ints[i] = cur - 1;
            } else {
                ints[i] = cur + 1;
            }

            i += cur;
        }
        return count;
    }

    Object solveQ1(List<String> input) {

        var ints = input.stream().mapToInt(Integer::parseInt).toArray();

        int count = 0;
        int i = 0;
        while (i >=0 && i < ints.length) {
            count++;
            var cur = ints[i];
            if (cur == 0) {
                ints[i] = 1;
                continue;
            }

            ints[i] = cur + 1;
            i += cur;
        }


        return count;
    }

}

