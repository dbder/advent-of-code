package aoc.y2018;

import aoc.AU;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class Day05 extends AU {

    public static void main(String[] args) {
        new Day05();
    }

    List<String> testData1 = """
            dabAcCaCBAcCcaDA
            """.lines().collect(toList());
    List<String> testData2 = """
            """.lines().collect(toList());

    Day05() {
        if (!testData1.isEmpty()) println("Test Q1: " + solveQ1(testData1));
//        if (!testData2.isEmpty()) println("Test Q1: " + solveQ1(testData2));
        println("Day " + getDay() + " Q1: " + solveQ1(getInputLines()));

        if (!testData1.isEmpty()) println("Test Q2: " + solveQ2(testData1));
        if (!testData2.isEmpty()) println("Test Q2: " + solveQ2(testData2));
        println("Day " + getDay() + " Q2: " + solveQ2(getInputLines()));
    }

    Object solveQ2(List<String> input) {
        var result = 0L;

        return result;
    }

    Object solveQ1(List<String> input) {
        var result = Integer.MAX_VALUE;
        var str = input.get(0);
        int count = 0;

        for (int i = 'a'; i <= 'z'; i++) {
            var sb = new StringBuilder();

            for (int j = 0; j < str.length(); j++) {
                if (Character.toLowerCase(str.charAt(j)) == (char) i) {

                } else {
                    sb.append(str.charAt(j));
                }
            }

            result = min(result, getbest(sb.toString()));


        }

        return result;

    }

    int getbest(String str) {
        while (true) {
            var next = remove(str);
            if (next.equals(str)) {
                System.out.println(str);
                return str.length();
            }
            str = next;
        }

    }

    String remove(String s) {
        for (int i = 1; i < s.length(); i++) {
            var c1 = s.charAt(i - 1);
            var c2 = s.charAt(i);
            if (c1 == c2) continue;
            if (!(Character.toUpperCase(c1) == Character.toUpperCase(c2))) continue;
            var sb = new StringBuilder();
            for (int j = 0; j < s.length(); j++) {
                if (j != i && j != i-1) {
                    sb.append(s.charAt(j));
                }
            }
            return sb.toString();

        }
        return s;
    }

}

