package aoc.y2020;

import aoc.AU;

public class Day02 extends AU {

    public static void main(String[] args) {
        new Day02();
    }

    Day02() {
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + solveQ2());
    }

    Object solveQ2() {
        var result = 0L;
        var input = getInputLines();

        for (var line : input) {
            var split = line.replace("-", " ").replace(":", "").split(" ");

            var str = split[3];
            var min = toInt(split[0]);
            var max = toInt(split[1]);
            var letter = split[2];

            boolean isMin = false;
            boolean isMax = false;

            if (str.charAt(min - 1) == letter.charAt(0)) isMin = true;
            if (str.charAt(max - 1) == letter.charAt(0)) isMax = true;
            if (isMin ^ isMax) result++;

        }

        return result;
    }

    Object solveQ1() {
        var result = 0L;
        var input = getInputLines();

        for (var line : input) {

            var split = line.replace("-", " ").replace(":", "").split(" ");
            var low = toInt(split[0]);
            var high = toInt(split[1]);
            var letter = split[2];
            var str = split[3];
            var count = mapCharCount(str).get(letter);
            if (count != null && count >= low && count <= high) {
                result++;
            }
        }
        return result;
    }

}

