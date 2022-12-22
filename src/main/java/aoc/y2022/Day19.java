package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;
import aoc.y2022.day19.Blueprint;
import aoc.y2022.day19.FactoryState;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class Day19 extends AU {

    Day19(List<String> testData, boolean q1) {
        var startTime = System.currentTimeMillis();
        switch ((q1 ? "q1" : "q2") + "-" + (testData == null ? "real" : "test")) {
            case "q1-test" -> println("Test Q1: " + solveQ1(testData));
            case "q1-real" -> println("Real Q1: " + solveQ1(getInputLines()));
            case "q2-test" -> println("Test Q2: " + solveQ2(testData));
            case "q2-real" -> println("Real Q2: " + solveQ2(getInputLines()));
            default -> throw new AocException("Unknown case");
        }
        println("Time: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    public static void main(String[] args) {
        if (!testData1.get().isEmpty()) new Day19(testData1.get(), true);
//        if (!testData2.get().isEmpty()) new Day19(testData2.get(), true);
//        new Day19(null, true);
//        if (true) return;
//        if (!testData1.get().isEmpty()) new Day19(testData1.get(), false);
//        if (!testData2.get().isEmpty()) new Day19(testData2.get(), false);
//        new Day19(null, false);
    }

    static Supplier<List<String>> testData1 = () -> """
            Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
            Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay.  Each geode robot costs 3 ore and 12 obsidian.
            """.lines().collect(toList());

    Object solveQ2(List<String> input) {
        var result = 0L;
        for (var line : input) {
            var ints = toInts(line);
            var bp = new Blueprint(ints[0], new int[]{ints[1], 0, 0}, new int[]{ints[2], 0, 0}, new int[]{ints[3], ints[4], 0}, new int[]{ints[5], 0, ints[6]});

            List<List<FactoryState>> states = new ArrayList<>();
            range(0, 33).forEach(i -> states.add(new ArrayList<>()));
            states.get(1).add(FactoryState.start());

            for (int i = 1; i <= 32; i++) {
                for (int j = 0; j < states.get(i).size(); j++) {
                    var state = states.get(i).get(j);


                }
            }
            for (int i = 1; i <= 32; i++) {
                var curStates = states.get(i);
                System.out.println("Day: " + i + " All states: " + curStates.size());
            }
            System.out.println(states.get(32).size());
            System.out.println();

        }
        return result;
    }


    Object solveQ1(List<String> input) {
        int maxMinutes = 24;
        var result = 0L;
        println("Max minutes: " + maxMinutes);
        for (var line : input) {
            var ints = toInts(line);
            var bp = new Blueprint(ints[0], new int[]{ints[1], 0, 0}, new int[]{ints[2], 0, 0}, new int[]{ints[3], ints[4], 0}, new int[]{ints[5], 0, ints[6]});
            println(bp);
            List<List<FactoryState>> states = new ArrayList<>();
            range(0, maxMinutes+1).forEach(i -> states.add(new ArrayList<>()));
            states.get(1).add(new FactoryState(0, 0, 0, 0, 1, 0, 0, 0, 1));

            var peaks = FactoryState.getPeaks(states.get(1), bp);

            for (int i = 1; i <= maxMinutes + 1; i++) {
//                System.out.println("Minute: " + i + " peaks: " + peaks.size());
                peaks = FactoryState.getPeaks(peaks, bp);
//                System.out.println("Minute: " + i + " after: " + peaks.size());
//                System.out.println("size: " + states.get(i).size());

                for (int j = 0; j < maxMinutes; j++) {
                    states.set(j, FactoryState.getPeaks(states.get(j), bp));
                }

                for (int j = 0; j < states.get(i).size(); j++) {
                    var state = states.get(i).get(j);
                    boolean added = false;


                    var ore = state.buildOreRobot(bp);
                    if (ore.minute() != 0 && ore.minute() <= maxMinutes) {
                        if (FactoryState.isPeak(ore, peaks, bp)) {
                            states.get(ore.minute()).add(ore);
                            peaks.add(ore);
                            added = true;
                        }
                    }

                    var clay = state.buildClayRobot(bp);
                    if (clay.minute() != 0 && clay.minute() <= maxMinutes) {
                        if (FactoryState.isPeak(clay, peaks, bp)) {
                            states.get(clay.minute()).add(clay);
                            peaks.add(clay);
                            added = true;
                        }
                    }

                    var obsidian = state.buildObsidianRobot(bp);
                    if (obsidian.minute() != 0 && obsidian.minute() <= maxMinutes) {
                        if (FactoryState.isPeak(obsidian, peaks, bp)) {
                            states.get(obsidian.minute()).add(obsidian);
                            peaks.add(obsidian);
                            added = true;
                        }
                    }

                    var geode = state.buildGeodeRobot(bp);
                    if (geode.minute() != 0 && geode.minute() <= maxMinutes) {
                        if (FactoryState.isPeak(geode, peaks, bp)) {
                            states.get(geode.minute()).add(geode);
                            peaks.add(geode);
                            added = true;
                        }
                    }


                    if (!added) {
                        var nextMinute = state.nextMinute(bp);
                        if (nextMinute.minute() <= maxMinutes + 1) {
                            if (FactoryState.isPeak(nextMinute, peaks, bp)) {
                                states.get(nextMinute.minute()).add(nextMinute);
                                peaks.add(nextMinute);
                            }

                        }
                    }
                }
            }

            System.out.println("start count:");
            var max = states.get(maxMinutes + 1).stream().mapToInt(FactoryState::geode).max().orElse(0);
            System.out.println("Max: " + max);
            System.out.println();

        }
        return result;
    }

    int maxOf(int... arr) {
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = Math.max(max, i);
        }
        return max;
    }
}



