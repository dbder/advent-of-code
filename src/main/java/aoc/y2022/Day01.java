package aoc.y2022;

import aoc.AU;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class Day01 extends AU {

    public static void main(String[] args) {
        new Day01();
    }

    Day01() {
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + solveQ2());
    }

    Object solveQ2() {
        return chunk(getInputLines())
                .stream()
                .map(list -> list.stream()
                        .map(Integer::parseInt)
                        .reduce(0, Integer::sum)
                )
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce(1, Integer::sum);
    }

    Object solveQ1() {
        return chunk(getInputLines())
                .stream()
                .map(list -> list.stream()
                        .map(Integer::parseInt)
                        .reduce(0, Integer::sum)
                )
                .reduce(0, Integer::max);
    }

}

