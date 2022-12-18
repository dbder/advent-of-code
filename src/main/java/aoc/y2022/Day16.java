package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
//        new Day16(null, true);
//        if (true) return;
//        if (!testData1.get().isEmpty()) new Day16(testData1.get(), false);
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


    Object solveQ2(List<String> input) {
        var result = 0L;
        init(input, 26);


        var t = h.targets.stream().toList();

        var level = addLevel1(t);
        System.out.println("1 " + level.size());
        int l= 2;

        level = addLevel2P1(t, level);
        level = addLevel2P2(t, level);
        var max = new int[]{0};
        max[0] = level.stream().mapToInt(c -> h.scorev2(c)).max().orElse(0);
        println((l++) + " size:" + level.size() + " max: " + max[0]);

        level = addLevel2P1(t, level);
        level = level.parallelStream().filter(c -> h.scorev2(c) > max[0] - 400).collect(toList());
        level = addLevel2P2(t, level);
        level = level.parallelStream().filter(c -> h.scorev2(c) > max[0] - 400).collect(toList());
        max[0] = level.stream().mapToInt(c -> h.scorev2(c)).max().orElse(0);
        println((l++) + " size:" + level.size() + " max: " + max[0]);


        level = addLevel2P1(t, level);
        level = level.parallelStream().filter(c -> h.scorev2(c) > max[0] - 400).collect(toList());
        level = addLevel2P2(t, level);
        level = level.parallelStream().filter(c -> h.scorev2(c) > max[0] - 400).collect(toList());
        max[0] = level.stream().mapToInt(c -> h.scorev2(c)).max().orElse(0);
        println((l++) + " size:" + level.size() + " max: " + max[0]);


        level = addLevel2P1(t, level);
        level = level.parallelStream().filter(c -> h.scorev2(c) > max[0] - 400).collect(toList());
        level = addLevel2P2(t, level);
        level = level.parallelStream().filter(c -> h.scorev2(c) > max[0] - 400).collect(toList());
        max[0] = level.stream().mapToInt(c -> h.scorev2(c)).max().orElse(0);
        println((l++) + " size:" + level.size() + " max: " + max[0]);

        level = addLevel2P1(t, level);
        level = level.parallelStream().filter(c -> h.scorev2(c) > max[0] - 400).collect(toList());
        level = addLevel2P2(t, level);
        level = level.parallelStream().filter(c -> h.scorev2(c) > max[0] - 400).collect(toList());
        max[0] = level.stream().mapToInt(c -> h.scorev2(c)).max().orElse(0);
        println((l++) + " size:" + level.size() + " max: " + max[0]);

        level = addLevel2P1(t, level);
        level = level.parallelStream().filter(c -> h.scorev2(c) > max[0] - 400).collect(toList());
        level = addLevel2P2(t, level);
        level = level.parallelStream().filter(c -> h.scorev2(c) > max[0] - 400).collect(toList());
        max[0] = level.stream().mapToInt(c -> h.scorev2(c)).max().orElse(0);
        println((l++) + " size:" + level.size() + " max: " + max[0]);

        return result;
    }


    List<Combo> addLevel2P1(List<String> t, List<Combo> prev) {
        var combos = new ArrayList<Combo>();
        for (var combo : prev) {
            for (var t1 : t) {
                if (combo.common().contains(t1)) continue;
                var p1 = new ArrayList<>(combo.p1());
                var newcommon = new HashSet<>(combo.common());
                p1.add(t1);
                var p1d = h.getDist(p1);
                if (p1d > h.maxminutes) {
                    var p2d = h.getDist(combo.p2());
                    if (p2d > h.maxminutes) {
                        continue;
                    } else {
                        combos.add(combo);
                        continue;
                    }
                }
                newcommon.add(t1);
                combos.add(new Combo(p1, combo.p2, newcommon));
            }
        }

        return combos;
    }

    List<Combo> addLevel2P2(List<String> t, List<Combo> prev) {
        var combos = new ArrayList<Combo>();
        int skip = 0;
        for (var combo : prev) {
            for (var target : t) {
                if (combo.common().contains(target)) continue;
                var p2 = new ArrayList<>(combo.p2());
                var newcommon = new HashSet<>(combo.common());
                p2.add(target);
                var p2d = h.getDist(p2);
                if (p2d > h.maxminutes) {
                    var p1d = h.getDist(combo.p1());
                    if (p1d > h.maxminutes) {
                        skip++;
                        continue;
                    } else {
                        combos.add(combo);
                        continue;
                    }
                }
                newcommon.add(target);
                combos.add(new Combo(combo.p1, p2, newcommon));
            }
        }

        return combos;
    }


    List<Combo> addLevel1(List<String> t) {
        var combos = new ArrayList<Combo>();
        for (int i = 0; i < t.size(); i++) {
            for (int j = i + 1; j < t.size(); j++) {
                if (i == j) continue;
                var p1 = t.get(i);
                var p2 = t.get(j);
                var common = new HashSet<String>();
                common.add(p1);
                common.add(p2);
                var combo = new Combo(List.of(p1), List.of(p2), common);
                combos.add(combo);
            }
        }
        return combos;
    }

    int maxtotal = 0;

    Day16Helper h;

    Object solveQ1(List<String> input) {
        var result = 0L;
        return maxtotal;
    }


    public record Combo(List<String> p1, List<String> p2, Set<String> common) {
    }

    record Pair(String from, String to) {
    }


    void init(List<String> input, int maxminutes) {
        Map<Pair, Integer> distances = new HashMap<>();
        Map<String, Set<String>> map = new HashMap<>();
        Map<String, Integer> rates = new HashMap<>();
        for (var line : input) {
            var split = line.replaceAll(",", "").split(" ");
            for (int i = 9; i < split.length; i++) {
                addUni(map, split[1], split[i]);
            }
            var rate = toInts(line)[0];
            if (rate > 0) {
                rates.put(split[1], rate);
            }
        }

        Set<String> targets = rates.entrySet()
                .stream()
                .filter(e -> e.getValue() > 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        for (var from : map.keySet()) {
            for (var to : map.keySet()) {
                if (from.equals(to)) continue;
                distances.put(new Pair(from, to), 1 + getNextStepTo(map, from, to));
            }
        }
        h = new Day16Helper(distances, targets, maxminutes, rates);
    }

}

