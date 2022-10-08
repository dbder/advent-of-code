package aoc.y2021;

import aoc.AU;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class Day14 extends AU {

    public static void main(String[] args) {
        var input = getInputAsStream("src/aoc/y2021/input/day14")
                .toList();

        solveQ1(input);
        solveQ2(input);

    }

    /**
     * Apply 40 steps of pair insertion to the polymer template and find the most and least common elements in the result.
     * What do you get if you take the quantity of the most common element and subtract the quantity of the least common element?
     */
    static void solveQ2(List<String> input) {
        var stats = getStats(input, 40);

        println("Day 14 Q2: " + (stats.getMax() - stats.getMin()));
    }

    /**
     * What do you get if you take the quantity of the most common element and subtract the quantity of the least common element?
     */
    static void solveQ1(List<String> input) {
        var stats = getStats(input, 10);

        println("Day 14 Q1: " + (stats.getMax() - stats.getMin()));
    }

    private static LongSummaryStatistics getStats(List<String> input, int iterations) {
        var template = input.get(0);

        var newMap = input.stream()
                .skip(2)
                .collect(
                        toMap(s -> s.substring(0, 2), i -> 0L)
                );

        var lookup = input.stream()
                .skip(2)
                .collect(
                        toMap(s -> s.substring(0, 2), s -> s.substring(6, 7))
                );


        for (int x = 2; x < template.length() + 1; x++) {
            final Long aLong = newMap.get(template.substring(x - 2, x));
            newMap.put(template.substring(x - 2, x), aLong + 1);
        }

        long[] count = new long[100];
        for (char c : template.toCharArray()) {
            count[c]++;
        }

        for (int x = 0; x < iterations; x++) {
            newMap = doIteration(newMap, lookup, count);
        }
        return Arrays.stream(count)
                .filter(l -> l != 0)
                .summaryStatistics();
    }

    private static Map<String, Long> doIteration(Map<String, Long> newMap, Map<String, String> lookup, long[] count) {
        Map<String, Long> tmp = new HashMap<>();

        for (var entry : newMap.entrySet()) {
            var key = entry.getKey();
            var val = entry.getValue();

            final String s = lookup.get(key);
            String s1 = key.charAt(0) + s;
            String s2 = s + key.charAt(1);

            long l1 = tmp.computeIfAbsent(s1, i -> 0L);
            count[s.charAt(0)] += val;
            tmp.put(s1, l1 + val);
            long l2 = tmp.computeIfAbsent(s2, i -> 0L);
            tmp.put(s2, l2 + val);
        }
        newMap = tmp;
        return newMap;
    }

}

