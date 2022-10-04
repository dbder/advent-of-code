package aoc.y2015;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;
import static java.lang.Math.min;


import aoc.AU;

public class Day05 extends AU {

    public static void main(String[] args) {
//        var input = getInputAsString("src/aoc/y2021/input/day");
        var input = getInputAsStream("src/aoc/y2015/input/day05")
                .toList();

//        input = List.of(
//                "qjhvhtzxzqqjkmpb",
//                "xxyxx",
//                "uurcxstgmygtbstg",
//                "ieodomkazucvgmuy"
//        );

//        solveQ1(input);
        solveQ2(input);

    }

    static void solveQ2(List<String> input) {
        int count = 0;

        for (var str : input) {
            boolean found = false;
            for (int i = 0; i < str.length() - 3; i++) {
                for (int j = i + 2; j < str.length() - 1; j++) {
                    if (str.substring(i, i + 2).equals(str.substring(j, j + 2))) found = true;
                }
            }
            if (!found) continue;

            found = false;
            for (int i = 2; i < str.length(); i++) {
                if (str.charAt(i) == str.charAt(i - 2)) found = true;


            }
            if (!found) continue;
            System.out.println(str);
            count++;

        }


        println("Day 05 Q1: " + count);
    }

    static void solveQ1(List<String> input) {
        System.out.println(input);
        int count = 0;

        for (var str : input) {
            if (str.indexOf("ab") >= 0) continue;
            if (str.indexOf("cd") >= 0) continue;
            if (str.indexOf("pq") >= 0) continue;
            if (str.indexOf("xy") >= 0) continue;

            boolean found = false;
            for (int i = 1; i < str.length(); i++) {
                if (str.charAt(i) == str.charAt(i - 1)) {
                    found = true;
                }
            }
            if (!found) continue;

            var vowels = new HashSet<Character>();
            vowels.add('a');
            vowels.add('e');
            vowels.add('i');
            vowels.add('u');
            vowels.add('o');
            int vowelc = 0;
            for (var c : str.toCharArray()) {
                if (vowels.contains(c)) vowelc++;
            }
            if (vowelc < 3) continue;
            System.out.println(str);
            count++;
        }

        println("Day 05 Q1: " + count);
    }
}

