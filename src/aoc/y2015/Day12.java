package aoc.y2015;

import aoc.AU;

import java.util.Arrays;
import java.util.List;


public class Day12 extends AU {
    private static String day = "12";

    public static void main(String[] args) {

        var input = getInputAsString("src/aoc/y2015/input/day" + day);
//        var input = getInputAsStream("src/aoc/y2015/input/day" + day + "t").toList();

        solveQ1(input);
//        solveQ2(input);

    }

    static void solveQ2(List<String> input) {
//        println("Day " + day +" Q2: " + "");
    }

    static void solveQ1(String input) {
        var result = 0;

        var split = input.split("[,:\\]}\\[]");

        for (var s : split) {
            try {
                if (s.matches(".*\\d+.*")) {
                    result += Integer.parseInt(s);
                }
            } catch (NumberFormatException ignored) {
                System.out.println("catch: " + s);
            }
        }

        System.out.println(Arrays.toString(split));


        println("Day " + day + " Q1: " + result);
    }
}

