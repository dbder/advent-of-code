package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.toList;

public class Day16 extends AU {

    Day16(List<String> testData, boolean q1) {
        switch ((q1 ? "q1" : "q2") + "-" + (testData == null ? "real" : "test")) {
            case "q1-test" -> println("Test Q1: " + solveQ1(testData));
            case "q1-real" -> println("Real Q1: " + solveQ1(getInputLines()));
            case "q2-test" -> println("Test Q2: " + solveQ2(testData));
            case "q2-real" -> println("Real Q2: " + solveQ2(getInputLines()));
            default -> throw new AocException("Unknown case");
        }
    }

    public static void main(String[] args) {
//        if (!testData1.get().isEmpty()) new Day16(testData1.get(), true);
        if (!testData2.get().isEmpty()) new Day16(testData2.get(), true);
        new Day16(null, true);
        if (true) return;
        if (!testData1.get().isEmpty()) new Day16(testData1.get(), false);
        if (!testData2.get().isEmpty()) new Day16(testData2.get(), false);
        new Day16(null, false);
    }

    static Supplier<List<String>> testData1 = () -> """
            Valve AA has flow rate=0; tunnels lead to valves DD, II, BB
            Valve BB has flow rate=13; tunnels lead to valves CC, AA
            Valve CC has flow rate=2; tunnels lead to valves DD, BB
            Valve DD has flow rate=20; tunnels lead to valves CC, AA, EE
            Valve EE has flow rate=3; tunnels lead to valves FF, DD
            Valve FF has flow rate=0; tunnels lead to valves EE, GG
            Valve GG has flow rate=0; tunnels lead to valves FF, HH
            Valve HH has flow rate=22; tunnel leads to valve GG
            Valve II has flow rate=0; tunnels lead to valves AA, JJ
            Valve JJ has flow rate=21; tunnel leads to valve II
            """.lines().collect(toList());

    static Supplier<List<String>> testData2 = () -> """
            """.lines().collect(toList());

    Object solveQ2(List<String> input) {
        var result = 0L;
        //DAY2 !!
        return result;
    }

    int maxtotal = 0;

    Object solveQ1(List<String> input) {
        var result = 0L;

        for (var line : input) {
            var split = line.replaceAll(",", "").split(" ");
            for (int i = 9; i < split.length; i++) {
                addUni(map, split[1], split[i]);

            }
            rates.put(split[1], toInts(line)[0]);

        }


        targets = rates.entrySet().stream().filter(e -> e.getValue() > 0).map(Map.Entry::getKey).collect(Collectors.toSet());


        for (var from : map.keySet()) {
            for (var to : map.keySet()) {
                distances.put(new Pair(from, to), 1 + getNextStepTo(map, from, to));
            }
        }


//        var c = new Combo(List.of("JJ", "BB", "CC"), List.of("DD", "HH", "EE"), new HashSet<>());
//        var sc = scorev2(c);
//        System.out.println("sc = " + sc);

        getBestOne(new Combo(new ArrayList<>(), new ArrayList<>(), new HashSet<>()));
        return maxtotal;
    }


    record Pair(String from, String to) {
    }

    record Combo(List<String> p1, List<String> p2, Set<String> common) {

    }

    Set<Combo> visited = new HashSet<>();
    Map<Set<String>, Integer> scoremap = new HashMap<>();
    int maxminutes = 30;

    void getBestOne(Combo combo) {



        if (getPotency(combo) < maxtotal) return;
//
        if (visited.contains(combo)) return;
        visited.add(combo);

        int d1 = getDist(combo.p1);
        int d2 = getDist(combo.p2);

        if (combo.common.size() == targets.size() || (d1 >= maxminutes && d2 >= maxminutes)) {
            var score = scorev2(combo);
            if (score > maxtotal) {
                maxtotal = score;
                println("New best: " + combo + " " + score);
            }
        }
//
//        for (var t : targets) {
//            if (combo.common.contains(t)) continue;
//            var newp2 = new ArrayList<>(combo.p2);
//            newp2.add(t);
//            var newcommon = new HashSet<>(combo.common);
//            newcommon.add(t);
//            getBestOne(new Combo(combo.p1, newp2, newcommon));
//        }

        for (var t : targets) {
            if (combo.common.contains(t)) continue;
            var newp1 = new ArrayList<>(combo.p1);
            newp1.add(t);
            var newcommon = new HashSet<>(combo.common);
            newcommon.add(t);
            getBestOne(new Combo(newp1, combo.p2, newcommon));
        }


    }

    int getPotency(Combo combo) {
        Map<Integer, List<String>> nodes = new HashMap<>();
        var cur = "AA";
        int totaldist1 = 0;
        for (var p1 : combo.p1) {
            var dist = distances.get(new Pair(cur, p1));
            totaldist1 += dist;
            nodes.computeIfAbsent(totaldist1, k -> new ArrayList<>()).add(p1);
            cur = p1;
        }

        cur = "AA";
        var totaldist2 = 0;
        for (var p2 : combo.p2) {
            var dist = distances.get(new Pair(cur, p2));
            totaldist2 += dist;
            nodes.computeIfAbsent(totaldist2, k -> new ArrayList<>()).add(p2);
            cur = p2;
        }
        var left = targets.stream().collect(Collectors.toSet());
        left.removeAll(combo.common);

        var totaldist3 = 0;
        if (totaldist1 < totaldist2) {
            totaldist3 = totaldist1;
        } else {
            totaldist3 = totaldist2;
        }
        int minsleft = maxminutes - totaldist3;
        for (var p3 : left) {
            nodes.computeIfAbsent(totaldist3, k -> new ArrayList<>()).add(p3);
            totaldist3 += 2;
        }

        return score(nodes);

    }

    int scorev2(Combo combo) {
        Map<Integer, List<String>> nodes = new HashMap<>();
        var cur = "AA";
        int totaldist = 0;
        for (var p1 : combo.p1) {
            var dist = distances.get(new Pair(cur, p1));
            totaldist += dist;
            nodes.computeIfAbsent(totaldist, k -> new ArrayList<>()).add(p1);
            cur = p1;
        }

//        cur = "AA";
//        totaldist = 0;
//        for (var p2 : combo.p2) {
//            var dist = distances.get(new Pair(cur, p2));
//            totaldist += dist;
//            nodes.computeIfAbsent(totaldist, k -> new ArrayList<>()).add(p2);
//            cur = p2;
//        }

        return score(nodes);
    }

    int getDist(List<String> path) {
        int total = 0;
        String cur = "AA";
        for (int i = 0; i < path.size() - 1; i++) {
            total += distances.get(new Pair(cur, path.get(i)));
            cur = path.get(i);
        }
        return total;
    }

    int score(Map<Integer, List<String>> nodes) {
        var score = 0;
        var base = 0;
        for (int i = 0; i < maxminutes; i++) {
            var list = nodes.computeIfAbsent(i, k -> new ArrayList<>());
            for (var node : list) {
                base += rates.get(node);
            }
            score += base;
        }
        return score;
    }

    Map<Pair, Integer> distances = new HashMap<>();
    Set<String> targets = new HashSet<>();
    Map<String, Set<String>> map = new HashMap<>();
    Map<String, Integer> rates = new HashMap<>();


}

