package aoc.y2018;

import aoc.AU;
import aoc.misc.AocException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Day04 extends AU {

    public static void main(String[] args) {
        new Day04();
    }

    List<String> testData1 = """
            [1518-11-01 00:00] Guard #10 begins shift
            [1518-11-01 00:05] falls asleep
            [1518-11-01 00:25] wakes up
            [1518-11-01 00:30] falls asleep
            [1518-11-01 00:55] wakes up
            [1518-11-01 23:58] Guard #99 begins shift
            [1518-11-02 00:40] falls asleep
            [1518-11-02 00:50] wakes up
            [1518-11-03 00:05] Guard #10 begins shift
            [1518-11-03 00:24] falls asleep
            [1518-11-03 00:29] wakes up
            [1518-11-04 00:02] Guard #99 begins shift
            [1518-11-04 00:36] falls asleep
            [1518-11-04 00:46] wakes up
            [1518-11-05 00:03] Guard #99 begins shift
            [1518-11-05 00:45] falls asleep
            [1518-11-05 00:55] wakes up                     
            """.lines().collect(toList());
    List<String> testData2 = """
            """.lines().collect(toList());

    Day04() {
        if (!testData1.isEmpty()) println("Test Q1: " + solveQ1(testData1));
        println("Day " + getDay() + " Q1: " + solveQ1(getInputLines()));
        if (!testData1.isEmpty()) println("Test Q2: " + solveQ2(testData1));
        println("Day " + getDay() + " Q2: " + solveQ2(getInputLines()));
    }

    Object solveQ2(List<String> input) {
            Collections.sort(input);
            String activeGuard = "";
            int sleepStart = 0;
            var map = new HashMap<String, int[]>();
            for (String s : input) {
                var split = s.split(" ");
                var state = split[2];
                var ints = toInts(s.replaceAll("-", "/"));
                if (state.equals("Guard")) {
                    activeGuard = split[3];
                } else if (state.equals("falls")) {
                    sleepStart = ints[4];
                } else {
                    var sleepEnd = ints[4];
                    var sleepArray = map.getOrDefault(activeGuard, new int[60]);
                    for (int i = sleepStart; i < sleepEnd; i++) {
                        sleepArray[i]++;
                    }
                    map.put(activeGuard, sleepArray);
                }

            }

            var e = map.entrySet().stream().sorted((a, b) -> {
                var aSum = 0;
                var bSum = 0;
                for (int i = 0; i < 60; i++) {
                    aSum = Math.max(aSum, a.getValue()[i]);
                    bSum = Math.max(bSum, b.getValue()[i]);
                }
                return Integer.compare(bSum, aSum);
            }).findFirst().orElse(null);


            var max = max(e.getValue());

            for (int i = 0; i < 60; i++) {
                if (e.getValue()[i] == max) {
                    return toInts(e.getKey())[0] * i;
                }
            }

            throw new AocException("No result found");
        }


    Object solveQ1(List<String> input) {
        Collections.sort(input);
        String activeGuard = "";
        int sleepStart = 0;
        var map = new HashMap<String, int[]>();
        for (String s : input) {
            var split = s.split(" ");
            var state = split[2];
            var ints = toInts(s.replaceAll("-", "/"));
            if (state.equals("Guard")) {
                activeGuard = split[3];
            } else if (state.equals("falls")) {
                sleepStart = ints[4];
            } else {
                var sleepEnd = ints[4];
                var sleepArray = map.getOrDefault(activeGuard, new int[60]);
                for (int i = sleepStart; i < sleepEnd; i++) {
                    sleepArray[i]++;
                }
                map.put(activeGuard, sleepArray);
            }

        }

        var e = map.entrySet().stream().sorted((a, b) -> {
            var aSum = 0;
            var bSum = 0;
            for (int i = 0; i < 60; i++) {
                aSum += a.getValue()[i];
                bSum += b.getValue()[i];
            }
            return Integer.compare(bSum, aSum);
        }).findFirst().orElse(null);

        var max = max(e.getValue());

        for (int i = 0; i < 60; i++) {
            if (e.getValue()[i] == max) {
                return toInts(e.getKey())[0] * i;
            }
        }

        throw new AocException("No result found");
    }

}

