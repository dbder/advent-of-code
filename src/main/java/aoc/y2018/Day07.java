package aoc.y2018;

import aoc.AU;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Day07 extends AU {

    public static void main(String[] args) {


        if (!testData1.get().isEmpty()) new Day07(testData1.get(), true);
        if (!testData2.get().isEmpty()) new Day07(testData2.get(), true);
        new Day07(null, true);
        if (true) return;
        if (!testData1.get().isEmpty()) new Day07(testData1.get(), false);
        if (!testData2.get().isEmpty()) new Day07(testData2.get(), false);
        new Day07(null, false);
    }

    static Supplier<List<String>> testData1 = () -> """
            Step C must be finished before step A can begin.
            Step C must be finished before step F can begin.
            Step A must be finished before step B can begin.
            Step A must be finished before step D can begin.
            Step B must be finished before step E can begin.
            Step D must be finished before step E can begin.
            Step F must be finished before step E can begin.
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

        return result;
    }

    Object solveQ1(List<String> input) {
        var result = 0L;

        var all = new HashSet<String>();

        var map = new HashMap<String, Set<String>>();

        for (var s : input) {
            var arr = s.split(" ");
            all.add(arr[1]);
            all.add(arr[7]);

            var vals = map.computeIfAbsent(arr[7], i -> new HashSet<>());
            if (map.get(arr[1]) == null) map.put(arr[1], new HashSet<>());
            vals.add(arr[1]);

        }

        map.entrySet().forEach((f) -> System.out.println(f));


        var list = new LinkedList<String>();
        while (map.size() >0) {
            var before = map.entrySet().stream().filter(e -> e.getValue().isEmpty()).sorted(Comparator.comparing(Map.Entry::getKey)).findFirst().orElse(null);
//            if (before == null) break;
            list.addLast(before.getKey());
            map.remove(before.getKey());
            for (var e : map.entrySet()) {
                e.getValue().remove(before.getKey());
            }
        }
        return list.stream().collect(Collectors.joining());

        //ACBPFXIDHWEGZMNOJLRKSUQTVY
        //RZTLGHKWPFCNXVEMABUOSIDQJY
        //ZWTNXVUFSIQOMKHJEDCB
        //PGRALBCDEFHIJKMNOQSTUVWXYZ

//        return result;
    }
}

