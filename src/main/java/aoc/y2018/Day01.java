package aoc.y2018;

import aoc.AU;

import java.util.HashSet;
import java.util.Set;

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
        Set<Integer> seen = new HashSet<>();
        seen.add(0);
        while (true) {
            for (var line : input) {
                result += Integer.parseInt(line);
                if (seen.contains(result)) {
                    return result;
                }
                seen.add(result);
            }
        }
    }

    Object solveQ1() {
        var result = 0;
        var input = getInputLines();

        for (var line : input) {
            result += Integer.parseInt(line);
        }

        return result;
    }

}

