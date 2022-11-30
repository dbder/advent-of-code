package aoc.y2015;

import aoc.AU;
import aoc.AocException;

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
        var input = getInputLines().get(0);

        int count = 0;
        for (var c : input.toCharArray()) {
            count++;
            if (c == '(') {
                result++;
            } else {
                result--;
            }
            if (result < 0) {
                return count;
            }
        }
        throw new AocException("Not found");
    }

    Object solveQ1() {
        var result = 0;
        var input = getInputLines().get(0);

        for (var c : input.toCharArray()) {
            if (c == '(') {
                result++;
            } else {
                result--;
            }
        }


        return result;
    }

}

