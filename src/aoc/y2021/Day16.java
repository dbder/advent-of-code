package aoc.y2021;

import aoc.AU;

import java.util.*;
import java.util.stream.Collectors;

public class Day16 extends AU {

    static Map<String, String> map = new HashMap<>();

    static {
        map.put("0", "0000");
        map.put("1", "0001");
        map.put("2", "0010");
        map.put("3", "0011");
        map.put("4", "0100");
        map.put("5", "0101");
        map.put("6", "0110");
        map.put("7", "0111");
        map.put("8", "1000");
        map.put("9", "1001");
        map.put("A", "1010");
        map.put("B", "1011");
        map.put("C", "1100");
        map.put("D", "1101");
        map.put("E", "1110");
        map.put("F", "1111");
    }

    public static void main(String[] args) {
        var input = getInputAsString("src/aoc/y2021/input/day16");

        solveQ1(input);
        solveQ2(input);

    }

    static void solveQ2(String input) {

        var binary = new StringBuilder(Arrays.stream(input.split("")).map(map::get).collect(Collectors.joining()));
        println("Day 16 Q2: " + traverse(binary));
    }

    static void solveQ1(String input) {

        var binary = new StringBuilder(Arrays.stream(input.split("")).map(map::get).collect(Collectors.joining()));
        traverse(binary);
        println("Day 16 Q1: " + versions);
    }

    static int versions = 0;

    static long traverse(StringBuilder s) {
        versions += Long.parseLong(s.substring(0, 3), 2);
        long t = Long.parseLong(s.substring(3, 6), 2);
        s.delete(0, 6);
        if (t == 4) {
            var sb = new StringBuilder();
            while (s.charAt(0) == '1') {
                sb.append(s.substring(1, 5));
                s.delete(0, 5);
            }
            sb.append(s.substring(1, 5));
            s.delete(0, 5);
            return Long.parseLong(sb.toString(), 2);
        } else {
            boolean toggle = s.charAt(0) == '0';
            s.delete(0, 1);
            List<Long> numbers = new ArrayList<>();
            if (toggle) {
                long len = Long.parseLong(s.substring(0, 15), 2);
                s.delete(0, 15);
                long target = s.length() - len;
                while (s.length() > target) {
                    numbers.add(traverse(s));
                }
            } else {
                long nr = Long.parseLong(s.substring(0, 11), 2);
                s.delete(0, 11);
                while (nr-- > 0) {
                    numbers.add(traverse(s));
                }
            }
            return calc(t, numbers);
        }
    }

    static long calc(long type, List<Long> numbers) {
        return switch ((int) type) {
            case 0 -> numbers.stream().mapToLong(i -> i).sum();
            case 1 -> numbers.stream().mapToLong(i -> i).reduce(1, (a, b) -> a * b);
            case 2 -> numbers.stream().mapToLong(i -> i).min().orElse(0L);
            case 3 -> numbers.stream().mapToLong(i -> i).max().orElse(0L);
            case 5 -> numbers.get(0) > numbers.get(1) ? 1 : 0;
            case 6 -> numbers.get(1) > numbers.get(0) ? 1 : 0;
            case 7 -> numbers.get(1).equals(numbers.get(0)) ? 1 : 0;
            default -> throw new IllegalStateException("Unexpected value: " + (int) type);
        };
    }
}

