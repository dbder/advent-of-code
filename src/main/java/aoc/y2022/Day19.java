package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;

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
//        if (!testData1.get().isEmpty()) new Day19(testData1.get(), true);
//        if (!testData2.get().isEmpty()) new Day19(testData2.get(), true);
        new Day19(null, true);
        if (true) return;
        if (!testData1.get().isEmpty()) new Day19(testData1.get(), false);
        if (!testData2.get().isEmpty()) new Day19(testData2.get(), false);
        new Day19(null, false);
    }

    static Supplier<List<String>> testData1 = () -> """
            Blueprint 1: Each ore robot costs 4 ore. Each clay robot costs 2 ore. Each obsidian robot costs 3 ore and 14 clay. Each geode robot costs 2 ore and 7 obsidian.
            Blueprint 2: Each ore robot costs 2 ore. Each clay robot costs 3 ore. Each obsidian robot costs 3 ore and 8 clay.  Each geode robot costs 3 ore and 12 obsidian.
            """.lines().collect(toList());

    static Supplier<List<String>> testData2 = () -> """
            """.lines().collect(toList());

    Object solveQ2(List<String> input) {
        var result = 0L;
        //DAY2 !!
        return result;
    }

    record Blueprint(int name, int[] ore, int[] clay, int[] obsidian, int[] geode) {}

    Object solveQ1(List<String> input) {
        var result = 1L;
        var list = new ArrayList<Integer>();
        for (var line : input) {
            var ints = toInts(line);
            var bp = new Blueprint(ints[0], new int[]{ints[1], 0, 0}, new int[]{ints[2], 0, 0}, new int[]{ints[3], ints[4], 0}, new int[]{ints[5], 0, ints[6]});

            System.out.println(bp);
            max = 0;
            dp = new int[40];
            dpgeo = new int[40];
            visited = new HashSet<>();
            getMaxScore(0, 0, 0, 0, 1, 0, 0, 0, bp, 1);
            System.out.println("name: " + bp.name + " " + max);
            list.add(max);
            System.out.println(list);
        }

        for (int i = 2; i < list.size(); i += 3) {
            result *= list.get(i) * list.get(i - 1) * list.get(i - 2);
        }

        return result;
    }

    int max = 0;

    int[] dp = new int[40];
    int[] dpgeo = new int[40];

    record State(int ore, int clay, int obsidian, int geode, int oreRobot, int clayRobot, int obsidianRobot,
                 int geodeRobot, int minute) {
        @Override
        public String toString() {
            return "State{" +
                    "ore=" + ore +
                    ", clay=" + clay +
                    ", obsidian=" + obsidian +
                    ", geode=" + geode +
                    ", oreRobot=" + oreRobot +
                    ", clayRobot=" + clayRobot +
                    ", obsidianRobot=" + obsidianRobot +
                    ", geodeRobot=" + geodeRobot +
                    '}';
        }
    }

    Set<State> visited = new HashSet<>();

    void buyClayBot(
            int ore,
            int clay,
            int obsidian,
            int geode,
            int oreR,
            int clayR,
            int obsidianR,
            int geodeR,
            Blueprint bp,
            int minute
    ) {
        getMaxScore(ore - bp.clay[0], clay, obsidian, geode, oreR, clayR + 1, obsidianR, geodeR, bp, minute);
    }

    void buyOreBot(
            int ore,
            int clay,
            int obsidian,
            int geode,
            int oreR,
            int clayR,
            int obsidianR,
            int geodeR,
            Blueprint bp,
            int minute

    ) {
        getMaxScore(ore - bp.ore[0], clay, obsidian, geode, oreR + 1, clayR, obsidianR, geodeR, bp, minute);
    }

    void buyObsidianBot(
            int ore,
            int clay,
            int obsidian,
            int geode,
            int oreR,
            int clayR,
            int obsidianR,
            int geodeR,
            Blueprint bp,
            int minute
    ) {
        getMaxScore(ore - bp.obsidian[0], clay - bp.obsidian[1], obsidian, geode, oreR, clayR, obsidianR + 1, geodeR, bp, minute);
    }

    void buyGeodeBot(
            int ore,
            int clay,
            int obsidian,
            int geode,
            int oreR,
            int clayR,
            int obsidianR,
            int geodeR,
            Blueprint bp,
            int minute
    ) {
        getMaxScore(ore - bp.geode[0], clay, obsidian - bp.geode[2], geode, oreR, clayR, obsidianR, geodeR + 1, bp, minute);
    }


    void getMaxScore(
            int ore,
            int clay,
            int obsidian,
            int geode,
            int oreR,
            int clayR,
            int obsidianR,
            int geodeR,
            Blueprint bp,
            int minute
    ) {
        if (minute > 33) return;


//        if (geode + 10 < dpgeo[minute]) {
//            return;
//        } else {
//            if (geode > dpgeo[minute]) {
//                dpgeo[minute] = geode;
//            }
//        }

        if (geodeR + 2 < dp[minute]) {
            return;
        } else {
            if (geodeR > dp[minute]) {
                dp[minute] = geodeR;
            }
        }
        if (geode > max) {
            max = geode;
            System.out.println("max: " + max + " " + minute + " Geode: " + geodeR + "-" + geode);
        }

        if (minute > 15 && clayR < 2) return;
//        if (minute > 25 && obsidian < 3) return;
//        if (minute > 25 && geodeR < 1) return;
        var state = new State(ore, clay, obsidian, geode, oreR, clayR, obsidianR, geodeR, minute);
        if (visited.contains(state)) return;
        visited.add(state);

        if (minute > 32) return;
        boolean bought = false;

        if (ore >= bp.geode[0] && obsidian >= bp.geode[2]) {
            buyGeodeBot(ore + oreR, clay + clayR, obsidian + obsidianR, geode + geodeR, oreR, clayR, obsidianR, geodeR, bp, minute + 1);
            bought = true;
            if (oreR < 5 && ore >= bp.ore[0]) {
                buyOreBot(ore + oreR, clay + clayR, obsidian + obsidianR, geode + geodeR, oreR, clayR, obsidianR, geodeR, bp, minute + 1);
                bought = true;
            }
            return;
        }

        if (oreR < 5 && ore >= bp.ore[0]) {
            buyOreBot(ore + oreR, clay + clayR, obsidian + obsidianR, geode + geodeR, oreR, clayR, obsidianR, geodeR, bp, minute + 1);
            bought = true;
        }
        if (clayR < bp.obsidian[1] && ore >= bp.clay[0]) {
            buyClayBot(ore + oreR, clay + clayR, obsidian + obsidianR, geode + geodeR, oreR, clayR, obsidianR, geodeR, bp, minute + 1);
            bought = true;
        }
        if (obsidianR < bp.geode[2] && ore >= bp.obsidian[0] && clay >= bp.obsidian[1]) {
            buyObsidianBot(ore + oreR, clay + clayR, obsidian + obsidianR, geode + geodeR, oreR, clayR, obsidianR, geodeR, bp, minute + 1);
            bought = true;
        }


        if (!bought || (ore < 5 || clay < 15 || obsidian < 17)) {
            getMaxScore(ore + oreR, clay + clayR, obsidian + obsidianR, geode + geodeR, oreR, clayR, obsidianR, geodeR, bp, minute + 1);
        }
    }

    void doMinutes(
            int ore,
            int clay,
            int obsidian,
            int geode,
            int oreR,
            int clayR,
            int obsidianR,
            int geodeR,
            Blueprint bp,
            int minute
    ) {
//        var state = new State(ore, clay, obsidian, geode, oreR, clayR, obsidianR, geodeR);

        var maxOre = maxOf(bp.ore[0], bp.clay[0], bp.obsidian[0], bp.geode[0]);
        var maxClay = maxOf(bp.ore[1], bp.clay[1], bp.obsidian[1], bp.geode[1]);
        var maxObsidian = maxOf(bp.ore[2], bp.clay[2], bp.obsidian[2], bp.geode[2]);
        if (ore > maxOre + oreR && clay + clayR > maxClay && obsidian + obsidianR > maxObsidian) return;


//        if (visited.contains(state)) return;
//        visited.add(state);

        if (minute > 20) {
            if (dp[minute] - 4 > geodeR) return;
            if (geodeR > dp[minute]) dp[minute] = geodeR;
        }


        if (ore >= bp.geode[0] && obsidian >= bp.geode[2]) {
            doMinutes(
                    ore - bp.geode[0] + oreR,
                    clay + clayR,
                    obsidian - bp.geode[2] + obsidianR,
                    geode + geodeR,
                    oreR,
                    clayR,
                    obsidianR,
                    geodeR + 1,
                    bp,
                    minute + 1);
        }

        if (ore >= bp.obsidian[0] && clay >= bp.obsidian[1]) {
            doMinutes(
                    ore - bp.obsidian[0] + oreR,
                    clay - bp.obsidian[1] + clayR,
                    obsidian + obsidianR,
                    geode + geodeR,
                    oreR,
                    clayR,
                    obsidianR + 1,
                    geodeR,
                    bp,
                    minute + 1);

        }


        if (ore >= bp.ore[0]) {
            doMinutes(
                    ore - bp.ore[0] + oreR,
                    clay + clayR,
                    obsidian + obsidianR,
                    geode + geodeR,
                    oreR + 1,
                    clayR,
                    obsidianR,
                    geodeR,
                    bp,
                    minute + 1);
        }
        if (ore >= bp.clay[0]) {
            doMinutes(
                    ore - bp.clay[0] + oreR,
                    clay + clayR,
                    obsidian + obsidianR,
                    geode + geodeR,
                    oreR,
                    clayR + 1,
                    obsidianR,
                    geodeR,
                    bp,
                    minute + 1);
        }


        if (ore > maxOre + 10 && clay > maxClay + 10 && obsidian > maxObsidian) return;

        doMinutes(ore + oreR,
                clay + clayR,
                obsidian + obsidianR,
                geode + geodeR,
                oreR, clayR, obsidianR, geodeR, bp, minute + 1);

    }

    int maxOf(int... arr) {
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = Math.max(max, i);
        }
        return max;
    }
}



