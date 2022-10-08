package aoc.y2015;

import aoc.AU;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Day13 extends AU {

    public static void main(String[] args) {
        var day = new Day13();
        day.solveQ1();
        day.solveQ2();
    }


    Map<String, Map<String, Integer>> map = new HashMap<>();

    void solveQ2() {

        map = createMap(getInputLines());
        map.put("Me", new HashMap<>());

        var max = getPermutations(map.keySet()).stream()
                .mapToInt(this::seatingToScore)
                .max()
                .orElse(0);

        println("Day " + getDay() + " Q2: " + max);
    }


    void solveQ1() {
        map = createMap(getInputLines());

        var max = getPermutations(map.keySet()).stream()
                .mapToInt(this::seatingToScore)
                .max()
                .orElse(0);

        println("Day " + getDay() + " Q1: " + max);
    }

    private static Map<String, Map<String, Integer>> createMap(List<String> input) {
        Map<String, Map<String, Integer>> map = new HashMap<>();
        for (var s : input) {
            var split = s.split(" ");
            var p1 = split[0];
            var p2 = split[10].substring(0, split[10].length() - 1);
            int mul = 1;
            if (split[2].equals("lose")) mul = -1;
            var value = Integer.parseInt(split[3]) * mul;
            map.putIfAbsent(p1, new HashMap<>());
            map.putIfAbsent(p2, new HashMap<>());
            map.get(p1).put(p2, value);
        }
        return map;
    }


    int seatingToScore(String[] input) {
        int sum = 0;
        for (int i = 0; i < input.length; i++) {
            int prev = i - 1;
            int next = i + 1;
            if (prev < 0) prev = input.length - 1;
            if (next >= input.length) next = 0;

            String pp = input[prev];
            String np = input[next];
            String cp = input[i];

            sum += map.get(cp).getOrDefault(pp, 0);
            sum += map.get(cp).getOrDefault(np, 0);
        }
        return sum;
    }


    @Override
    public String getDay() {
        return "13";
    }

}

