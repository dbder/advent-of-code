package aoc.y2015;

import aoc.AU;

import java.util.List;


public class Day10 extends AU {
    private static String day = "10";

    public static void main(String[] args) {

        solveQ1(null);
//        solveQ2(input);

    }
//    static void solveQ2(List<String> input) {
////        println("Day " + day +" Q2: " + "");
//    }

    static void solveQ1(List<String> input) {
//        String str = "1";
        String str = "1113122113";
        for (int x = 0; x < 50; x++) {
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

    ;
}

