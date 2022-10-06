package aoc.y2015;

import aoc.AU;

import java.util.List;


public class Day11 extends AU {
    private static String day = "11";

    public static void main(String[] args) {
//        eeppcrst
        //aabbcrst
        // abcxxrra
        //abcxxrra
        //efgxxrrt
//        var input = getInputAsString("src/aoc/y2015/input/day" + day);
        var input = getInputAsStream("src/aoc/y2015/input/day" + day + "t").toList();

//        solveQ1("hepxcrrq");
        solveQ1("hepxxyzz");

    }

    static void solveQ2(List<String> input) {
//        println("Day " + day +" Q2: " + "");
    }

    static void solveQ1(String input) {
        var arr = increment(input.toCharArray(), input.length() - 1);
        var result = 0;
//        var arr = input.toCharArray();
        while (!isValid(new String(arr))) {
            increment(arr, arr.length - 1);
            System.out.println(new String(arr));
        }



        println("Day " + day + " Q1: " + new String(arr));
    }

    static char[] increment(char[] input, int index) {

        char c = input[index];

        if (c == 'z') {
            input[index] = 'a';
            increment(input, index - 1);
        } else {
            input[index] = (char) (c + 1);
        }
        return input;
    }
    static String  values = "abcdefghjkmnpqrstuvwxyz";
    static boolean isValid(String input) {
        if (input.contains("i") || input.contains("o") || input.contains("l")) {
            return false;
        }
        boolean foundTriple = false;
        for (int x = 0; x < values.length() - 2; x++) {
            if (input.contains(values.substring(x, x + 3))) {
                System.out.println(values.substring(x, x + 3));
                foundTriple = true;
            }
        }

        if (!foundTriple) {
            return false;
        }
        int count = 0;
        char last = ' ';
        for (int x = 0; x < input.length() - 1; x++) {
            if (input.charAt(x) == input.charAt(x + 1)) {
                if (input.charAt(x) != last) {
                    count++;
                    last = input.charAt(x);
                }
            }
        }

        return count >= 2;
    }
}

