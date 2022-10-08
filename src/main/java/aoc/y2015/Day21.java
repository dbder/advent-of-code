package aoc.y2015;

import aoc.AU;
import aoc.AocException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Day21 extends AU {
    private static final Logger log = LogManager.getLogger(Day21.class);

    public static void main(String[] args) {
        new Day21();
    }

    Day21() {
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + solveQ2());
        throw new AocException("dsf");
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
    public String getDay() { return "21";};

}

