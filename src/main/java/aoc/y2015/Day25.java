package aoc.y2015;

import aoc.AU;

public class Day25 extends AU {

    @Override
    protected String getDay() {
        return "25";
    }


    public static void main(String[] args) {
        new Day25();
    }

    Day25() {
        //To continue, please consult the code grid in the manual.  Enter the code at row 2947, column 3029.
        println("Day " + getDay() + " Q1: " + solveQ1());
    }

    Object solveQ1() {
        var result = 20151125L;

        int row = 1;
        while (true) {
            int current = row++;
            int col = 1;
            while (current > 0) {
                if (current == 2947 && col == 3029) {
                    return result;
                }
                result = next(result);
                current--;
                col++;
            }
        }
    }

    long next (long prev) {
        return (prev * 252533) % 33554393;
    }

}

