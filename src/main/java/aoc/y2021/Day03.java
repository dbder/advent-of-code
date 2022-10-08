package aoc.y2021;

import aoc.AU;

import java.util.List;

public class Day03 extends AU {

    @Override
    protected String getDay() {
        return "03";
    }

    public static void main(String[] args) {
        new Day03();
    }

    Day03() {
        var input = getInputLines();
        solveQ1(input);
    }

    static void solveQ1(List<String> input) {
        var ints = new int[input.get(0).length()];

        for (String str : input) {
            for (int x = 0; x < str.length(); x++) {
                if (str.charAt(x) == '1') {
                    ints[x]++;
                }
            }
        }

        int gamma = 0;
        int epsilon = 0;
        int bar = input.size() / 2;
        for (int i : ints) {
            gamma *= 2;
            epsilon *= 2;
            if (i > bar) {
                gamma++;
            }
            if (i < bar) {
                epsilon++;
            }
        }

        println("Day3 Q1: " + (gamma * epsilon));
    }
}

