package aoc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Day extends AU {
    private static final Logger log = LogManager.getLogger(Day.class);

    @Override
    protected String getDay() {
        return "";
    }


    public static void main(String[] args) {
        new Day();
    }

    Day() {
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

}

