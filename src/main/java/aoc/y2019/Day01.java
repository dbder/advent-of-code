package aoc.y2019;

import aoc.AU;

public class Day01 extends AU {

    @Override
    protected String getDay() {
        return "01";
    }


    public static void main(String[] args) {
        new Day01();
    }

    Day01() {
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + solveQ2());
    }

    Object solveQ2() {
        var result = 0;
        var input = getInputLines();

        for (var line : input) {
            var i = toInt(line);
            while (i > 0) {
                i = i / 3 - 2;
                if (i > 0) {
                    result += i;
                }
            }
            result += i / 3 - 2;
        }

        return result;
    }

    Object solveQ1() {
        var result = 0;
        var input = getInputLines();

        for (var line : input) {
            var i = toInt(line);
            result += i / 3 - 2;
        }

        return result;
    }

}

