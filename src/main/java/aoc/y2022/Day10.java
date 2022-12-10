package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Day10 extends AU {

    Day10(List<String> testData, boolean q1) {
        switch ((q1 ? "q1" : "q2") + "-" + (testData == null ? "real" : "test")) {
            case "q1-test" -> println("Test Q1: " + solveQ1(testData));
            case "q1-real" -> println("Real Q1: " + solveQ1(getInputLines()));
            case "q2-test" -> println("Test Q2: " + solveQ2(testData));
            case "q2-real" -> println("Real Q2: " + solveQ2(getInputLines()));
            default -> throw new AocException("Unknown case");
        }
    }

    public static void main(String[] args) {
        if (!testData1.get().isEmpty()) new Day10(testData1.get(), true);
        new Day10(null, true);
        if (!testData1.get().isEmpty()) new Day10(testData1.get(), false);
        new Day10(null, false);
    }

    static Supplier<List<String>> testData1 = () -> """
            addx 15
            addx -11
            addx 6
            addx -3
            addx 5
            addx -1
            addx -8
            addx 13
            addx 4
            noop
            addx -1
            addx 5
            addx -1
            addx 5
            addx -1
            addx 5
            addx -1
            addx 5
            addx -1
            addx -35
            addx 1
            addx 24
            addx -19
            addx 1
            addx 16
            addx -11
            noop
            noop
            addx 21
            addx -15
            noop
            noop
            addx -3
            addx 9
            addx 1
            addx -3
            addx 8
            addx 1
            addx 5
            noop
            noop
            noop
            noop
            noop
            addx -36
            noop
            addx 1
            addx 7
            noop
            noop
            noop
            addx 2
            addx 6
            noop
            noop
            noop
            noop
            noop
            addx 1
            noop
            noop
            addx 7
            addx 1
            noop
            addx -13
            addx 13
            addx 7
            noop
            addx 1
            addx -33
            noop
            noop
            noop
            addx 2
            noop
            noop
            noop
            addx 8
            noop
            addx -1
            addx 2
            addx 1
            noop
            addx 17
            addx -9
            addx 1
            addx 1
            addx -3
            addx 11
            noop
            noop
            addx 1
            noop
            addx 1
            noop
            noop
            addx -13
            addx -19
            addx 1
            addx 3
            addx 26
            addx -30
            addx 12
            addx -1
            addx 3
            addx 1
            noop
            noop
            noop
            addx -9
            addx 18
            addx 1
            addx 2
            noop
            noop
            addx 9
            noop
            noop
            noop
            addx -1
            addx 2
            addx -37
            addx 1
            addx 3
            noop
            addx 15
            addx -21
            addx 22
            addx -6
            addx 1
            noop
            addx 2
            addx 1
            noop
            addx -10
            noop
            noop
            addx 20
            addx 1
            addx 2
            addx 2
            addx -6
            addx -11
            noop
            noop
            noop
            """.lines().collect(toList());

    Object solveQ2(List<String> input) {
        for (var line : input) {
            increase();
            if (line.equals("noop")) continue;
            x += toInts(line)[0];
            increase();
        }
        return chars.stream().map(String::valueOf).collect(Collectors.joining("\n", "\n", "\n"));
    }

    Object solveQ1(List<String> input) {

        for (var line : input) {
            increase();
            if (line.equals("noop")) continue;
            x += toInts(line)[0];
            increase();
        }
        return list.stream().mapToInt(i -> i).sum();
    }

    int x = 1;
    int cycle = 1;
    int frame = 0;

    List<char[]> chars = new ArrayList<>();
    {
        for (int i = 0; i < 10; i++) {
            chars.add(new char[100]);
            Arrays.fill(chars.get(i), ' ');
        }
    }

    List<Integer> list = new ArrayList<>();

    void increase() {
        int pixel = ((cycle - 1) % 40);
        if (pixel == x || pixel == x - 2 || pixel == x - 1) {
            chars.get(frame)[pixel] = 'X';
        }
        cycle++;
        var l = List.of(20, 60, 100, 140, 180, 220);
        if (l.contains(cycle)) {
            list.add(x * cycle);
        }

        if (l.contains(cycle + 20)) {
            frame++;
        }
    }

}

