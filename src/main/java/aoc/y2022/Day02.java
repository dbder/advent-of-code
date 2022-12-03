package aoc.y2022;

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
        var result = 0;
        var input = getInputLines();

        for (var line : input) {

            var split = line.split(" ");

            var opp = split[0];
            var me = split[1];

            if ("X".equals(me)) result += 0;
            if ("Y".equals(me)) result += 3;
            if ("Z".equals(me)) result += 6;

            switch (opp) {
                case "A":
                    if ("X".equals(me)) result += 3;
                    if ("Y".equals(me)) result += 1;
                    if ("Z".equals(me)) result += 2;
                    break;
                case "B":
                    if ("X".equals(me)) result += 1;
                    if ("Y".equals(me)) result += 2;
                    if ("Z".equals(me)) result += 3;
                    break;
                case "C":
                    if ("X".equals(me)) result += 2;
                    if ("Y".equals(me)) result += 3;
                    if ("Z".equals(me)) result += 1;
                    break;
            }
        }
        return result;
    }

    Object solveQ1() {
        var result = 0;
        var input = getInputLines();

        for (var line : input) {
            var split = line.split(" ");

            var opp = split[0];
            var me = split[1];

            if ("X".equals(me)) result++;
            if ("Y".equals(me)) result += 2;
            if ("Z".equals(me)) result += 3;

            switch (opp) {
                case "A":
                    if ("X".equals(me)) result += 3;
                    if ("Y".equals(me)) result += 6;
                    break;
                case "B":
                    if ("Y".equals(me)) result += 3;
                    if ("Z".equals(me)) result += 6;
                    break;
                case "C":
                    if ("Z".equals(me)) result += 3;
                    if ("X".equals(me)) result += 6;
                    break;
            }
        }
        return result;
    }

}

