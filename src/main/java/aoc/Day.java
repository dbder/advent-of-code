package aoc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Day extends AU {
    @Override
    protected String getDay() {
        throw new UnsupportedOperationException("Not implemented");
    }

    private static final Logger log = LogManager.getLogger(Day.class);

    public static void main(String[] args) {
        new Day();
    }

    Day() {
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


//    @Override
//    public String getDay() { return "";};

}

