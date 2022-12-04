package aoc.y2017;

import aoc.AU;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Day06 extends AU {

    public static void main(String[] args) {
        new Day06();
    }

    List<String> testData1 = """
            0 2 7 0
            """.lines().collect(toList());

    Day06() {
        if (!testData1.isEmpty()) println("Test Q1: " + solveQ1(testData1));
        println("Day " + getDay() + " Q1: " + solveQ1(getInputLines()));

        if (!testData1.isEmpty()) println("Test Q2: " + solveQ2(testData1));
        println("Day " + getDay() + " Q2: " + solveQ2(getInputLines()));
    }

    Object solveQ2(List<String> input) {
        var result = 0L;
        var in = toInts(input.get(0));
        var current = new State(in);
        var set = new HashSet<State>();

        while (!set.contains(current)) {

            result++;
            set.add(current);
            var max = max(current.in);
            var index = findIndex(current.in, max);

            int spread = max / current.in.length;
            if (max % current.in.length != 0) spread++;

            var next = current.in.clone();
            next[index] = 0;
            while (max > 0) {
                index++;
                index %= next.length;
                var diff = min(spread, max);
                next[index] += diff;
                max -= diff;
            }


            current = new State(next);

        }
        set.clear();
        result = 0;
        while (!set.contains(current)) {

            result++;
            set.add(current);
            var max = max(current.in);
            var index = findIndex(current.in, max);

            int spread = max / current.in.length;
            if (max % current.in.length != 0) spread++;

            var next = current.in.clone();
            next[index] = 0;
            while (max > 0) {
                index++;
                index %= next.length;
                var diff = min(spread, max);
                next[index] += diff;
                max -= diff;
            }


            current = new State(next);

        }
        return result;
    }

    Object solveQ1(List<String> input) {
        var result = 0L;
        var in = toInts(input.get(0));
        var current = new State(in);
        var set = new HashSet<State>();

        while (!set.contains(current)) {

            result++;
            set.add(current);
            var max = max(current.in);
            var index = findIndex(current.in, max);

            int spread = max / current.in.length;
            if (max % current.in.length != 0) spread++;

            var next = current.in.clone();
            next[index] = 0;
            while (max > 0) {
                index++;
                index %= next.length;
                var diff = min(spread, max);
                next[index] += diff;
                max -= diff;
            }

            current = new State(next);

        }
        return result;
    }

    int findIndex(Integer[] in, int max) {
        for (int i = 0; i < in.length; i++) {
            if (in[i] == max) return i;
        }
        return -1;
    }


    record State(Integer[] in) {

        @Override
        public boolean equals(Object obj) {
            Integer[] other = ((State) obj).in;
            return Arrays.deepEquals(other, in);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(in);
        }
    }
}

