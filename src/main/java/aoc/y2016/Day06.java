package aoc.y2016;

import aoc.AU;

public class Day06 extends AU {

    @Override
    protected String getDay() {
        return "06";
    }

    public static void main(String[] args) {
        new Day06();
    }

    Day06() {
        println("Day " + getDay() + " Q1: " + solve(false));
        println("Day " + getDay() + " Q2: " + solve(true));
    }

    Object solveQ2() {
        return null;
    }

    Object solve(boolean q2) {
        var input = getInputLines();
        char[][] arr = new char[8]['z' + 1];

        for (int i = 0; i < input.size(); i++) {
            var line = input.get(i);
            for (int j = 0; j < line.length(); j++) {
                arr[j][line.charAt(j)]++;
            }
        }
        var sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            var max = q2 ? 200 : 0;
            var maxChar = ' ';
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] > 0
                        && (q2 ? arr[i][j] < max : arr[i][j] > max)) {
                    max = arr[i][j];
                    maxChar = (char) j;
                }
            }
            sb.append(maxChar);
        }
        return sb.toString();
    }

}

