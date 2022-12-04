package aoc.y2018;

import aoc.AU;
import aoc.misc.AocException;

public class Day02 extends AU {

    public static void main(String[] args) {
        new Day02();
    }

    Day02() {
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + solveQ2());
    }

    Object solveQ2() {
        var input = getInputLines();

        for (var a : input) {
            for (var b : input) {
                if (differ1(a, b)) {
                    return overlap(a, b);
                }
            }
        }
        throw  new AocException("No match found");
    }




    Object solveQ1() {
        var input = getInputLines();

        var twos = input.stream().map(this::mapCountChars).filter(m -> m.get(2) != null).count();
        var threes = input.stream().map(this::mapCountChars).filter(m -> m.get(3) != null).count();

        return twos * threes;
    }


    String overlap(String a, String b) {
        var result = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == b.charAt(i)) {
                result.append(a.charAt(i));
            }
        }
        return result.toString();
    }

    boolean differ1(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                count++;
            }
        }
        return count == 1;
    }
}

