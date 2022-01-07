package aoc.y2021;

import aoc.AU;
import aoc.AocException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.LongPredicate;

public class Day10 extends AU {

    private static final LongPredicate isPositive = i -> i > 0;
    private static final Map<Character, Character> map1 = Map.of(')', '(', ']', '[', '}', '{', '>', '<');
    private static final Map<Character, Integer> map2 = Map.of('(', 1, '[', 2, '{', 3, '<', 4);


    public static void main(String[] args) {

        var input = getInputAsStream("src/aoc/y2021/input/day10")
                .toList();

        solveQ1(input);
        solveQ2(input);

    }

    /**
     * Find the completion string for each incomplete line, score the completion strings, and sort the scores. What is the middle score?
     */
    static void solveQ2(List<String> input) {

        var values = input.stream()
                .mapToLong(Day10::getValueQ2)
                .filter(isPositive)
                .sorted()
                .toArray();

        System.out.println(Arrays.toString(values));

        println("Day 10 Q2: " + values[values.length / 2]);
    }


    /**
     * Find the first illegal character in each corrupted line of the navigation subsystem. What is the total syntax error score for those errors?
     */
    static void solveQ1(List<String> input) {
        println("Day 10 Q1: " + input.stream().mapToInt(Day10::getValueQ1).sum());
    }

    private static long getValueQ2(final String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(', '[', '{', '<' -> sb.append(c);
                default -> {
                    char tmp = sb.charAt(sb.length() - 1);
                    if (tmp != map1.get(c)) continue;
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
        return sb.chars()
                .mapToLong(i -> map2.get((char) i))
                .reduce(0, (a, b) -> (a * 5) + b);
    }


    private static int getValueQ1(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            switch (c) {
                case '(', '[', '{', '<' -> sb.append(c);
                default -> {
                    char tmp = sb.charAt(sb.length() - 1);
                    if (tmp != map1.get(c)) {
                        return switch (c) {
                            case ')' -> 3;
                            case ']' -> 57;
                            case '}' -> 1197;
                            case '>' -> 25137;
                            default -> throw new AocException("Unexpected value: " + tmp);
                        };
                    }
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
        return 0;
    }
}

