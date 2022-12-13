package aoc.y2021;

import aoc.AU;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day12 extends AU {

    @Override
    protected String getDay() {
        return "12";
    }

    public static void main(String[] args) {
        new Day12();
    }

    Day12() {
        var input = getInputLines();
        solveQ1(input);
        solveQ2(input);
    }

    /**
     * After reviewing the available paths, you realize you might have time to visit a single small cave twice.
     * Given these new rules, how many paths through this cave system are there?
     */
    static void solveQ2(List<String> input) {
        var map = buildGraph(input);
        var count = new int[1];
        map.get("start").forEach(s -> countPaths(map, s, new HashSet<>(), count, false));
        System.out.println("Day 12 Q2: " + count[0]);
    }

    /**
     * How many paths through this cave system are there that visit small caves at most once?
     */
    static void solveQ1(List<String> input) {
        var map = buildGraph(input);
        var count = new int[1];
        map.get("start").forEach(s -> countPaths(map, s, new HashSet<>(), count, true));
        System.out.println("Day 12 Q1: " + count[0]);
    }

    static Map<String, Set<String>> buildGraph(List<String> input) {
        var map = new HashMap<String, Set<String>>();

        for (var line : input) {
            var parts = line.split("-");
            var a = parts[0];
            var b = parts[1];
            map.computeIfAbsent(a, k -> new HashSet<>()).add(b);
            map.computeIfAbsent(b, k -> new HashSet<>()).add(a);
        }
        return map;
    }

    static void countPaths(Map<String, Set<String>> map, String current, Set<String> visited, int[] count, boolean visitedTwice) {
        if (current.equals("start")) return;
        if (current.equals("end")) {
            count[0]++;
            return;
        }

        if (current.toLowerCase().equals(current)) visited.add(current);

        var children = map.get(current);
        for (var child : children) {
            if (!visited.contains(child)) {
                countPaths(map, child, new HashSet<>(visited), count, visitedTwice);
            } else if (!visitedTwice) {
                countPaths(map, child, new HashSet<>(visited), count, true);
            }
        }
    }
}

