package aoc.y2020;

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

        var input = getInputLines();

        for (var l1 : input) {
            for (var l2 : input) {
                for (var l3 : input) {
                    if (toInt(l1) + toInt(l2) + toInt(l3) == 2020) {
                        return toInt(l1) * toInt(l2) * toInt(l3);
                    }
                }
            }
        }
        throw new AocException("No solution found");
    }

    Object solveQ1() {

        var input = getInputLines();

        for (var l1 : input) {
            for (var l2 : input) {
                if (toInt(l1) + toInt(l2) == 2020) {
                    return toInt(l1) * toInt(l2);
                }
            }
        }

        throw new AocException("No solution found");
    }

}

