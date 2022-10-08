package aoc.y2015;

import aoc.AU;


public class Day10 extends AU {

    @Override
    public String getDay() {
        return "10";
    }

    private static String day = "10";


    public static void main(String[] args) {
        new Day10();
    }

    Day10() {
        solveQ1("1113122113");
        solveQ2("1113122113");
    }

    static void solveQ2(String str) {
        for (int x = 0; x < 50; x++) {
            str = expand(str);
        }
        println("Day " + day + " Q2: " + str.length());
    }

    static void solveQ1(String str) {
        for (int x = 0; x < 40; x++) {
            str = expand(str);
        }
        println("Day " + day + " Q1: " + str.length());
    }


    static String expand(String string) {
        var sb = new StringBuilder();
        char current = string.charAt(0);
        int count = 1;
        for (int i = 1; i < string.length(); i++) {
            char tmp = string.charAt(i);
            if (tmp == current) {
                count++;
            } else {
                sb.append(count).append(current);
                current = tmp;
                count = 1;
            }
        }
        sb.append(count).append(current);
        return sb.toString();
    }
}

