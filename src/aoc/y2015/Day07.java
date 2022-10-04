package aoc.y2015;

import aoc.AU;

import java.util.List;


public class Day07 extends AU {
    private static String day = "07";
    public static void main(String[] args) {

//        var input = getInputAsString("src/aoc/y2015/input/day" + day);
        var input = getInputAsStream("src/aoc/y2015/input/day" + day).toList();

        solveQ1(input);
        solveQ2(input);

    }
    static void solveQ2(List<String> input) {
//        println("Day " + day +" Q2: " + "");
    }

    static void solveQ1(List<String> input) {
        var result = 0;


        println("Day " + day +" Q1: " + result);
    }
}

