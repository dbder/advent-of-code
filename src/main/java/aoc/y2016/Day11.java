package aoc.y2016;

import aoc.AU;
import aoc.misc.AocException;

import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class Day11 extends AU {

    Day11(List<String> testData, boolean q1) {
        switch ((q1 ? "q1" : "q2") + "-" + (testData == null ? "real" : "test")) {
            case "q1-test" -> println("Test Q1: " + solveQ1(testData));
            case "q1-real" -> println("Real Q1: " + solveQ1(getInputLines()));
            case "q2-test" -> println("Test Q2: " + solveQ2(testData));
            case "q2-real" -> println("Real Q2: " + solveQ2(getInputLines()));
            default -> throw new AocException("Unknown case");
        }
    }

    public static void main(String[] args) {
        if (!testData1.get().isEmpty()) new Day11(testData1.get(), true);
        if (!testData2.get().isEmpty()) new Day11(testData2.get(), true);
        new Day11(null, true);
        if (true) return;
        if (!testData1.get().isEmpty()) new Day11(testData1.get(), false);
        if (!testData2.get().isEmpty()) new Day11(testData2.get(), false);
        new Day11(null, false);
    }

    static Supplier<List<String>> testData1 = () -> """
            """.lines().collect(toList());

    static Supplier<List<String>> testData2 = () -> """
            """.lines().collect(toList());

    Object solveQ2(List<String> input) {
        var result = 0L;
        //DAY2 !!
        return result;
    }

    Object solveQ1(List<String> input) {
        var result = 0L;


        return result;
    }

    record Level(int lift) {

    }

//    record Row(boolean POG, boolean POM, boolean TG, boolean TC, boolean PRG, boolean PRC, boolean RUG, boolean RUM, )



//    The first floor contains a
//    polonium generator, a
//    thulium generator, a
//    promethium generator
//    ruthenium generator
//    cobalt generator

//    thulium-compatible microchip, a , a , a ruthenium-compatible microchip, a , and a cobalt-compatible microchip.
//    The second floor contains a polonium-compatible microchip and a promethium-compatible microchip.
//    The third floor contains nothing relevant.
//    The fourth floor contains nothing relevant.

}

