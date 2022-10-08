package aoc.y2015;

import aoc.AU;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Day20 extends AU {
    private static final Logger log = LogManager.getLogger(Day20.class);

    public static void main(String[] args) {
        new Day20();
    }

    Day20() {
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + solveQ2());
    }

    Object solveQ2() {
        return null;
    }

    Object solveQ1() {
        var input = getInputLines();
        var result = 0;

        int[] houses = new int[29000000];

        for (int elf = 1; elf < 10000000; elf++) {
            int location = elf;
            int count = 0;
            while (count++ < 50 && location < 29000000) {
                houses[location] += elf * 11;
                location += elf;
            }
        }

        for (int i = 0; i < 29000000; i++) {
            if (houses[i] >= 29000000) {
                return i;
            }
        }
        throw new RuntimeException("No solution found");
    }

    @Override
    public String getDay() {
        return "20";
    }

}

