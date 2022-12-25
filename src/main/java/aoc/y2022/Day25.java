package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;

import java.math.BigInteger;
import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class Day25 extends AU {

    Day25(List<String> testData, boolean q1) {
        var startTime = System.currentTimeMillis();
        switch ((q1 ? "q1" : "q2") + "-" + (testData == null ? "real" : "test")) {
            case "q1-test" -> println("Test Q1: " + solveQ1(testData));
            case "q1-real" -> println("Real Q1: " + solveQ1(getInputLines()));
            default -> throw new AocException("Unknown case");
        }
        println("Time: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    public static void main(String[] args) {
        if (!testData1.get().isEmpty()) new Day25(testData1.get(), true);
        new Day25(null, true);
    }

    static Supplier<List<String>> testData1 = () -> """
            1=-0-2
            12111
            2=0=
            21
            2=01
            111
            20012
            112
            1=-1=
            1-12
            12
            1=
            122
            """.lines().collect(toList());

    static Supplier<List<String>> testData2 = () -> """
            """.lines().collect(toList());

    Object solveQ1(List<String> input) {
        return toSnafu(input.stream()
                .map(this::fromSnafu)
                .reduce(0L, Long::sum));
    }


    long fromSnafu(String s) {
        long total = 0;
        long mul = 1;
        for (int i = s.length() - 1; i >= 0; i--) {
            var ch = s.charAt(i);
            switch (ch) {
                case '0' -> total += 0;
                case '1' -> total += mul;
                case '2' -> total += mul * 2;
                case '-' -> total -= mul;
                case '=' -> total -= mul * 2;
                default -> throw new AocException("Unknown char: " + ch);
            }
            mul *= 5;
        }
        return total;
    }

    String toSnafu(long l) {
        var sb = new StringBuilder();
        while (l != 0) {
            var rem = l % 5;
            if (rem == 4) l++;
            if (rem == 3) l += 2;
            l /= 5;
            switch ((int) rem) {
                case 0 -> sb.append('0');
                case 1 -> sb.append('1');
                case 2 -> sb.append('2');
                case 3 -> sb.append('=');
                case 4 -> sb.append('-');
                default -> throw new AocException("Unknown rem: " + rem);
            }
        }
        return sb.reverse().toString();
    }

}

