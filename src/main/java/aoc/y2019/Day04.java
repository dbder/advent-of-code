package aoc.y2019;

import aoc.AU;

public class Day04 extends AU {

    public static void main(String[] args) {
        new Day04();
    }

    Day04() {
        println("Day " + getDay() + " Q1: " + solve(true));
        println("Day " + getDay() + " Q2: " + solve(false));
    }

    private static final int START = 356261;
    private static final int END = 846303;

    Object solve(boolean q1) {
        var result = 0L;


        for (int i = START; i <= END; i++) {
            if (isValid(i, q1)) {
                result++;
            }
        }

        return result;
    }

    private boolean isValid(int i, boolean q1) {

        var s = String.valueOf(i);
        var map = mapCountChars(s);

        if (q1) {
            if (map.keySet().stream().noneMatch(j -> j > 1)) return false;
        } else {
            if (map.keySet().stream().filter(j -> j == 2).count() != 1) return false;
        }

        boolean hasDouble = false;
        for (int k = 1; k < s.length(); k++) {
            if (s.charAt(k) < s.charAt(k - 1)) return false;
            if (s.charAt(k) == s.charAt(k - 1)) hasDouble = true;
        }
        return hasDouble;
    }

}



