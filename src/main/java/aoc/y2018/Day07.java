package aoc.y2018;

import aoc.AU;
import aoc.misc.AocException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Day07 extends AU {

    public static void main(String[] args) {


//        if (!testData1.get().isEmpty()) new Day07(testData1.get(), true);
//        if (!testData2.get().isEmpty()) new Day07(testData2.get(), true);
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

        record Worker(int id, int cost){}

        var pq  = new PriorityQueue<Integer>((a,b) -> a - b);
        int workers = 2;
        for (int i = 0; i < workers; i++) pq.add(0);

        var list = new LinkedList<String>();
        var visited = new HashSet<String>();
        List<List<String>> batches = new ArrayList<>();
        while (map.size() > 0) {

            var max = pq.stream().mapToInt(i -> i).max().orElseThrow(() -> new AocException("Can not"));
            while (pq.size() < workers) {
                pq.add(max);
            }

            var possible = all.stream().filter(a -> !visited.contains(a)).filter(a -> possible(visited, map, a)).collect(toList());
            System.out.println(possible);
            possible.forEach(i -> {
                var poll = pq.poll();
                int val = ((i.charAt(0) - (int)'A') +1);
                poll += val;
                pq.add(poll);
            });
            batches.add(possible);
            visited.addAll(possible);



            System.out.println(pq);
            System.out.println(possible);



        }
        return list.stream().collect(Collectors.joining());

        //ACBPFXIDHWEGZMNOJLRKSUQTVY
        //RZTLGHKWPFCNXVEMABUOSIDQJY
        //ZWTNXVUFSIQOMKHJEDCB
        //PGRALBCDEFHIJKMNOQSTUVWXYZ

//        return result;
    }

    boolean possible(Set<String> visited, HashMap<String, Set<String>> map, String key) {
        return visited.containsAll(map.get(key));
    }
}

