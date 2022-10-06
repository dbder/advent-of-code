package aoc.y2015;

import aoc.AU;


public class Day11 extends AU {
    private static String day = "11";

    public static void main(String[] args) {

        var answer = solveQ1("hepxcrrq");
        solveQ2(answer);

    }

    static void solveQ2(String input) {
        var answer = solve(input);
        println("Day " + day + " Q2: " + answer);
    }

    static String solveQ1(String input) {
        var answer = solve(input);
        println("Day " + day + " Q1: " + answer);
        return answer;
    }

    static String solve(String input) {
        var arr = increment(input.toCharArray(), input.length() - 1);
        while (!isValid(new String(arr))) {
            increment(arr, arr.length - 1);
        }
        return new String(arr);
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

    static String values = "abcdefghjkmnpqrstuvwxyz";

    static boolean isValid(String input) {
        if (input.contains("i") || input.contains("o") || input.contains("l")) {
            return false;
        }
        boolean foundTriple = false;
        for (int x = 0; x < values.length() - 2; x++) {
            if (input.contains(values.substring(x, x + 3))) {
                foundTriple = true;
            }
        }

        if (!foundTriple) {
            return false;
        }
        int count = 0;
        char last = ' ';
        for (int x = 0; x < input.length() - 1; x++) {
            if (input.charAt(x) == input.charAt(x + 1) && input.charAt(x) != last) {
                count++;
                last = input.charAt(x);

            }
        }
        return count >= 2;
    }
}

