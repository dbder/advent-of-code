package aoc.y2015;

import aoc.AU;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.IntBinaryOperator;


public class Day09 extends AU {
    private static String day = "09";

    @Override
    public String getDay() {
        return day;
    }

    public static void main(String[] args) {

        new Day09();

    }

    Day09() {
        var input = getInputLines();
        println("Day " + day + " Q1: " + solve(input, AU::min));
        println("Day " + day + " Q2: " + solve(input, AU::max));
    }

    int solve(List<String> input, IntBinaryOperator picker) {

        Map<String, Map<String, Integer>> map = new HashMap<>();
        for (String s : input) {
            String[] split = s.split(" ");
            String from = split[0];
            String to = split[2];
            int dist = parseInt(split[4]);
            map.putIfAbsent(from, new HashMap<>());
            map.putIfAbsent(to, new HashMap<>());
            map.get(from).put(to, dist);
            map.get(to).put(from, dist);
        }

        Set<String> keys = map.keySet();

        var best = new int[]{300};

        for (String s : keys) {
            var visited = new HashSet<String>();
            visited.add(s);
            shortest(map, keys, 0, visited, best, s, picker);
        }
        return best[0];
    }

    void shortest(Map<String, Map<String, Integer>> map, Set<String> set, int sum, Set<String> visited, int[] best, String last, IntBinaryOperator picker) {
        if (visited.size() == set.size()) {
            best[0] = picker.applyAsInt(best[0], sum);
            return;
        }

        for (String s : map.get(last).keySet()) {
            if (visited.contains(s)) {
                continue;
            }

            var tmp = new HashSet<>(visited);
            tmp.add(s);
            shortest(map, set, sum + map.get(last).get(s), tmp, best, s, picker);
        }
    }
}

