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
        targets = rates.entrySet().stream().filter(e -> e.getValue() > 0).map(e -> e.getKey()).collect(Collectors.toSet());
        for (var from : map.keySet()) {
            for (var to : map.keySet()) {
                distances.put(new Pair(from, to), 1 + getNextStepTo(map, from, to));
            }
        }


        var powerset = powerSet(targets.stream().toList()).sorted((a, b) -> a.size() - b.size()).collect(toList());


        int maxScore = 0;
        for (int i = 1; i <= 7; i++) {
            var powersegment = getOfSize(powerset, i);

            for (var list : powersegment) {
                for (var perm : getPermutations(list.toArray(new String[0]))) {
                    for (var perm2 : getPermutations(list.toArray(new String[0]))) {
                        Map<Integer, Set<String>> nodes = new HashMap<>();
                        var cur = "AA";
                        var totaldist = 0;
                        for (var item : perm) {
                            var dist = distances.get(new Pair(cur, item));
                            totaldist += dist;
                            nodes.computeIfAbsent(totaldist, e -> new HashSet<>()).add(item);
                            cur = item;
                        }

                        cur = "AA";
                        totaldist = 0;
                        for (var item : perm2) {
                            var dist = distances.get(new Pair(cur, item));
                            totaldist += dist;
                            nodes.computeIfAbsent(totaldist, e -> new HashSet<>()).add(item);
                            cur = item;
                        }

                        var score = score(nodes);

                        if (score > maxScore) {
                            maxScore = score;
                            println(i + "New max score: " + maxScore + " " + Arrays.toString(perm) + " " + Arrays.toString(perm2));
                            System.out.println(nodes);
                        }
                    }
                }

            }


        }


        return maxScore;
    }

    int score(Map<Integer, Set<String>> nodes) {
        var score = 0;
        var base = 0;
        Set<String> used = new HashSet<>();
        for (int i = 0; i < 26; i++) {
            nodes.computeIfAbsent(i, e -> new HashSet<>());
            for (var item : nodes.get(i)) {
                if (!used.contains(item)) {
                    used.add(item);
                    base += rates.get(item);
                }
            }
            score += base;
        }

        return score;
    }

    List<List<String>> getOfSize(List<List<String>> lists, int size) {
        var result = new ArrayList<List<String>>();
        for (var list : lists) {
            if (list.size() == size) result.add(list);
        }
        return result;
    }

    record Pair(String a, String b) {
    }

    Map<Pair, Integer> distances = new HashMap<>();
    Set<String> targets = new HashSet<>();
    long maxpressure = 0;

    record Check(String player, String elephant, String opened, int depth) {
    }

    Set<Check> visited = new HashSet<>();
    Map<String, Set<String>> map = new HashMap<>();
    Map<String, Integer> rates = new HashMap<>();

    void getmax(
            final String player,
            final String elephant,
            final int playerStepsLeft,
            final int eleStepsLeft,
            final int depth,
            int pressure,
            final Set<String> opened
    ) {

        int diff = 26 - depth;

        if (diff * 81 + pressure < maxtotal) {
            return;
        }


//        println("player: " + player + " elephant: " + elephant + " playerStepsLeft: " + playerStepsLeft + " eleStepsLeft: " + eleStepsLeft + " depth: " + depth + " pressure: " + pressure + " opened: " + opened);
        if (playerStepsLeft == 0) {

            var newopened = new HashSet<>(opened);
            newopened.add(player);

            var curtargets = targets.stream().filter(t -> !newopened.contains(t)).collect(toList());
            if (curtargets.isEmpty()) {

                getmax(player, elephant, 1, eleStepsLeft, depth, pressure, newopened);

            } else {

                for (var target : targets) {
                    if (target.equals(player)) continue;
                    getmax(target, elephant, distances.get(new Pair(player, target)), eleStepsLeft, depth, pressure, newopened);
                }

            }
            return;
        }

        if (eleStepsLeft == 0) {
            var newopened = new HashSet<>(opened);
            newopened.add(elephant);
            var curtargets = targets.stream().filter(t -> !newopened.contains(t)).collect(toList());
            if (curtargets.isEmpty()) {
                getmax(player, elephant, playerStepsLeft, 1, depth, pressure, newopened);
            } else {
                for (var target : targets) {
                    if (target.equals(elephant)) continue;
                    getmax(player, target, playerStepsLeft, distances.get(new Pair(elephant, target)), depth, pressure, newopened);
                }
            }
            return;
        }

        if (depth == 27) return;
        var addedPressure = opened.stream().mapToInt(rates::get).sum();
        pressure += addedPressure;

        if (depth >= 26) {
            if (depth > 26) throw new AocException("Depth too high");
            if (pressure > maxpressure) {
                System.out.println("maxpressure = " + pressure);
                maxpressure = pressure;
            }
            return;
        }


        if (playerStepsLeft > 0 && eleStepsLeft > 0) {
            getmax(player, elephant, playerStepsLeft - 1, eleStepsLeft - 1, depth + 1, pressure, opened);
            return;
        }


//        var newplateroute = plateroute.stream().collect(Collectors.toList());
//        newplateroute.add(depth + 1 + " " + player);
//        var newelephantroute = elephantroute.stream().collect(Collectors.toList());
//        newelephantroute.add(depth + 1 + " " + elephant);
//        if (pressure + (26 - depth) * maxtotal + pressure < maxpressure) {
//            return;
//        }


    }

    public int getNextStepTo(Map<String, Set<String>> map, String from, String to) {

        record Node(String name, Node parent) {
        }
        if (from.equals(to)) return 0;
        if (map.get(from).contains(to)) return 1;
        var visited = new HashSet<String>();
        var level = new ArrayList<Node>();
        var current = new Node(from, null);
        var root = current;
        visited.add(from);
        level.add(current);
        int count = 0;
        while (!level.isEmpty()) {
            count++;
            var next = new ArrayList<Node>();
            for (var node : level) {
                var links = map.get(node.name);
                for (var link : links) {
                    if (visited.contains(link)) continue;
                    visited.add(link);
                    if (link.equals(to)) {
                        return count;
                    }
                    next.add(new Node(link, node));
                }
            }
            level = next;
        }
        throw new AocException("No path found");
    }

}

