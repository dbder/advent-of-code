package aoc.y2021;

import aoc.AU;
import aoc.AocException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day08 extends AU {

    public static void main(String[] args) {
        var input = getInputAsStream("src/aoc/y2021/input/day08")
                .toList();

        solveQ1(input);
        solveQ2(input);

    }

    /**
     * For each entry, determine all of the wire/segment connections and decode the four-digit output values.
     * What do you get if you add up all of the output values?
     */
    static void solveQ2(List<String> input) {
        int total = 0;

        for (String line : input) {
            String[] split = line.split(" \\| ");

            var set1 = new HashSet<Character>();
            var set4 = new HashSet<Character>();
            for (String lp : split[0].split(" ")) {
                int len = lp.length();
                switch (len) {
                    case 2 -> toSet(lp, set1);
                    case 4 -> toSet(lp, set4);
                    default -> throw new AocException("Unexpected value: " + len);
                }
            }

            int count = 0;
            for (String rp : split[1].split(" ")) {
                count = parseDigits(set1, set4, count, rp);
            }
            total += count;
        }
        println("Day 08 Q2: " + total);
    }

    /**
     * In the output values, how many times do digits 1, 4, 7, or 8 appear?
     */
    static void solveQ1(List<String> input) {
        int count = 0;
        for (String str : input) {
            var parts = str.substring(str.indexOf("|")).split("[ ]+");
            for (String part : parts) {
                if (part.length() == 2 || part.length() == 4 || part.length() == 3 || part.length() == 7) {
                    count++;
                }
            }
        }
        println("Day 08 Q1: " + count);
    }

    private static void toSet(String s, Set<Character> set) {
        for (char c : s.toCharArray()) set.add(c);
    }

    private static Set<Character> toChars(String s) {
        Set<Character> chars = new HashSet<>();
        for (char c : s.toCharArray()) chars.add(c);
        return chars;
    }

    private static int parseDigits(HashSet<Character> set1, HashSet<Character> set4, int count, String rp) {
        int len = rp.length();
        count *= 10;
        count += switch (len) {
            case 2 -> 1;
            case 3 -> 7;
            case 4 -> 4;
            case 5 -> {
                var chars = toChars(rp);
                if (chars.containsAll(set1)) {
                    yield 3;
                }
                chars.retainAll(set4);
                if (chars.size() == 2) {
                    yield 5;
                } else {
                    yield 2;
                }
            }
            case 6 -> {
                var chars = toChars(rp);
                if (chars.containsAll(set4)) {
                    yield 9;
                }
                yield 0;
            }
            case 7 -> 8;
            default -> throw new AocException("Unexpected value: " + len);
        };
        return count;
    }
}

