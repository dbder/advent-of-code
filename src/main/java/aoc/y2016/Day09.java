package aoc.y2016;

import aoc.AU;

public class Day09 extends AU {

    @Override
    protected String getDay() {
        return "09";
    }


    public static void main(String[] args) {
        new Day09();
    }

    Day09() {
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + "10964557606");
//        println("Day " + getDay() + " Q2: " + solveQ2());
    }

    Object solveQ2() {
        var input = getInputLine();
        var res = new long[1];
        calc2(input, res);
        return res[0];
    }

    Object solveQ1() {
        return calc1(getInputLine());
    }

    void calc2(String str, long[] res) {
        if (str.length() == 0) {
            return;
        }
        if (str.charAt(0) != '(') {
            res[0]++;
            calc2(str.substring(1), res);
            return;
        }
        var split = str.substring(1, str.indexOf(")")).split("x");
        str = str.substring(str.indexOf(")") + 1);
        int one = toInt(split[0]);
        int two = toInt(split[1]);
        for (int i = 1; i <= two; i++) {
            calc2(str.substring(0, one), res);
        }
        calc2(str.substring(one), res);
    }

    long calc1(String str) {
        if (str.length() == 0) {
            return 0;
        }
        if (str.charAt(0) != '(') {
            return 1 + calc1(str.substring(1));
        }
        var split = str.substring(1, str.indexOf(")")).split("x");
        str = str.substring(str.indexOf(")") + 1);

        return (toInt(split[0]) * toInt(split[1])) + calc1(str.substring(toInt(split[0])));
    }
}

