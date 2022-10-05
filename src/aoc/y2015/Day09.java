package aoc.y2015;

import aoc.AU;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class Day09 extends AU {
    private static String day = "09";

    public static void main(String[] args) {

//        var input = getInputAsString("src/aoc/y2015/input/day" + day + "t");
        var input = getInputAsStream("src/aoc/y2015/input/day" + day).toList();

        solveQ1(input);
        solveQ2(input);

    }

    static void solveQ2(List<String> input) {


//        println("Day " + day +" Q2: " + "");
    }


    static void shortest(Map<String, Map<String, Integer>> map, Set<String> set, int sum, Set<String> visited, int[] best, String last) {
        System.out.println(visited);
        System.out.println(set);
        System.out.println();
        if (visited.size() == set.size()) {
            best[0] = Math.max(best[0], sum);
            return;
        }

        for (String s : map.get(last).keySet()) {
            if (visited.contains(s)) {
                continue;
            }

            var tmp = new HashSet<>(visited);
            tmp.add(s);


            shortest(map, set, sum + map.get(last).get(s), tmp, best, s);

        }
    }


    static void solveQ1(List<String> input) {
        var result = 0;
        Map<String, Map<String, Integer>> map = new HashMap<>();
        for (String s : input) {
            String[] split = s.split(" ");
            String from = split[0];
            String to = split[2];
            int dist = Integer.parseInt(split[4]);
            map.putIfAbsent(from, new HashMap<>());
            map.putIfAbsent(to, new HashMap<>());
            map.get(from).put(to, dist);
            map.get(to).put(from, dist);
        }

        Set<String> keys = map.keySet();
        System.out.println(keys);
        println("");
        println(map);


        var best = new int[]{0};

        for (String s : keys) {
            var visited = new HashSet<String>();
            visited.add(s);
            shortest(map, keys, 0, visited, best, s);
        }

        println("Day " + day + " Q1: " + best[0]);
    }
    // 125 too high
}

