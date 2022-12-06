package aoc.y2017;

import aoc.AU;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class Day07 extends AU {

    public static void main(String[] args) {

//        if (!testData1.get().isEmpty()) new Day07(testData1.get(), true);
//        if (!testData2.get().isEmpty()) new Day07(testData2.get(), true);
        new Day07(null, true);
//        if (true) return;
//        if (!testData1.get().isEmpty()) new Day07(testData1.get(), false);
        new Day07(null, false);
    }

    static Supplier<List<String>> testData1 = () -> """
            pbga (66)
            xhth (57)
            ebii (61)
            havc (66)
            ktlj (57)
            fwft (72) -> ktlj, cntj, xhth
            qoyq (66)
            padx (45) -> pbga, havc, qoyq
            tknk (41) -> ugml, padx, fwft
            jptl (61)
            ugml (68) -> gyxo, ebii, jptl
            gyxo (61)
            cntj (57)
            """.lines().collect(toList());

    static Supplier<List<String>> testData2 = () -> """
            """.lines().collect(toList());

    Day07(List<String> testData, boolean q1) {
        switch ((q1 ? "q1" : "q2") + "-" + (testData == null ? "real" : "test")) {
            case "q1-test" -> println("Test Q1: " + solveQ1(testData));
            case "q1-real" -> println("Real Q1: " + solveQ1(getInputLines()));
            case "q2-test" -> println("Test Q2: " + solveQ2(testData));
            case "q2-real" -> println("Real Q2: " + solveQ2(getInputLines()));
        }
    }


    Object solveQ2(List<String> input) {
        var result = 0L;
        Map<String, Set<String>> nodes = new HashMap<>();

        Map<String, Integer> values = new HashMap<>();

        for (var line : input) {
            var sp = line.replace(",", "").split("\s+");
            var key = sp[0]; //, toInts(line)[1]);
            var vals = nodes.computeIfAbsent(key, i -> new HashSet<String>());
            values.put(key, toInts(line)[0]);
            for (int i = 3; i < sp.length; i++) {
                vals.add(sp[i]);
            }

        }

        println(nodes);
        println(values);

        List<Result>[] results = new List[10];
        for (int i = 0; i < results.length; i++) {
            results[i] = new ArrayList<Result>();
        }
        count("aapssr", nodes, values, 0, results);
        println(results);
        for (var r : results) {
            System.out.println(r);
        }
        return result;
    }

    int count(String key, Map<String, Set<String>> nodes, Map<String, Integer> values, int level, List<Result>[] results)  {
        var children = nodes.get(key);

        int it = values.get(key);
        int count = it;
        List<Integer> check = new ArrayList<>();
        for (var child : children) {
            int i = count(child, nodes, values, level +1, results);
            if (i == 61704) System.out.println(values.get(child));
            check.add(i);
            count += i;
        }
        if (count == 61704) {
            System.out.println();
            for (var c : children) {
                System.out.println(values.get(c));
            }
        }
        if (check.size() > 0) {
            for (var c1 : check) {
                for (var c2 : check) {
                    if (!c1.equals(c2)) {
                        System.out.println("!!!");
                        System.out.println(children);
                        System.out.println(check);
                        System.out.println();
                    }
                }
            }

        }


        results[level].add(new Result(key, count));
        return count;
    }

    record Result(String str, int value){}
    // 1492

    Object solveQ1(List<String> input) {
        var keys = new HashSet<String>();
        var values = new HashSet<String>();
        for (var line : input) {
            var sp = line.replace(",", "").split("\s+");
            keys.add(sp[0]);
            for (int i = 3; i < sp.length; i++) {
                values.add(sp[i]);
            }
        }
        keys.removeAll(values);
        return keys;
    }

    record Node(String key, int weight) {

    }

}

