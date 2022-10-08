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

        return result;
    }

    @Override
    public String getDay() {
        return "20";
    }

}

