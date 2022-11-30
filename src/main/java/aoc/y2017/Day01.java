package aoc.y2017;

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
        var input = getInputLines().get(0);
        var half = input.length() / 2;

        for (int i = 0; i < input.length(); i++) {
            var c = input.charAt(i);
            var c2 = input.charAt((i + half) % input.length());
            if (c == c2) {
                result += c - '0';
            }
        }

        return result;
    }

    Object solveQ1() {
        var result = 0;
        var input = getInputLines().get(0);

        for (int i = 1; i < input.length(); i++) {
            if (input.charAt(i) == input.charAt(i -1)) {
                result += input.charAt(i) - '0';
            }
        }

        result += input.charAt(0) - '0';


        return result;
    }

}

