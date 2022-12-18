package aoc.y2022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Day16Helper {

    final Map<Day16.Pair, Integer> distances;
    final Set<String> targets;
    final int maxminutes;
    final Map<String, Integer> rates;


    public Day16Helper(Map<Day16.Pair, Integer> distances, Set<String> targets, int maxminutes, Map<String, Integer> rates){
        this.distances = distances;
        this.targets = targets;
        this.maxminutes = maxminutes;
        this.rates = rates;
    }


    public int getPotency(
            Day16.Combo combo
    ) {
        Map<Integer, List<String>> nodes = new HashMap<>();
        var cur = "AA";
        int totaldist1 = 0;
        for (var p1 : combo.p1()) {
            var dist = distances.get(new Day16.Pair(cur, p1));
            totaldist1 += dist;
            nodes.computeIfAbsent(totaldist1, k -> new ArrayList<>()).add(p1);
            cur = p1;
        }

        cur = "AA";
        var totaldist2 = 0;
        for (var p2 : combo.p2()) {
            var dist = distances.get(new Day16.Pair(cur, p2));
            totaldist2 += dist;
            nodes.computeIfAbsent(totaldist2, k -> new ArrayList<>()).add(p2);
            cur = p2;
        }
        var left = targets.stream().collect(Collectors.toSet());
        left.removeAll(combo.common());

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


    public int scorev2(
            Day16.Combo combo
    ) {
        Map<Integer, List<String>> nodes = new HashMap<>();
        var cur = "AA";
        int totaldist = 0;
        for (var p1 : combo.p1()) {
            var dist = distances.get(new Day16.Pair(cur, p1));
            totaldist += dist;
            nodes.computeIfAbsent(totaldist, k -> new ArrayList<>()).add(p1);
            cur = p1;
        }

        cur = "AA";
        totaldist = 0;
        for (var p2 : combo.p2()) {
            var dist = distances.get(new Day16.Pair(cur, p2));
            totaldist += dist;
            nodes.computeIfAbsent(totaldist, k -> new ArrayList<>()).add(p2);
            cur = p2;
        }

        return score(nodes);
    }

    public int getDist(List<String> path) {
        int total = 0;
        String cur = "AA";
        for (int i = 0; i < path.size() - 1; i++) {
            total += distances.get(new Day16.Pair(cur, path.get(i)));
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


}
