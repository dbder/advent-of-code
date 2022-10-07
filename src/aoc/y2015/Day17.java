package aoc.y2015;

import aoc.AU;

import java.util.List;

public class Day17 extends AU {

    public static void main(String[] args) {
        new Day17();
    }

    Day17() {
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + solveQ2());
    }

    Object solveQ2() {
        return null;
    }

    Object solveQ1() {
        var input = getInputLines().stream().map(i -> Integer.parseInt(i)).sorted().toList();
        for (int x = 0; x < input.size(); x++) {
            count(input, x, input.get(x), 150);
        }


        return count;
    }
    int count = 0;
    void count (List<Integer> input, int index, int sum, int target) {

        if(sum == target) {
            count++;
            return;
        }
        for (int i = index + 1; i < input.size(); i++) {
            count(input, i, sum + input.get(i), target);
        }
    }


    public String getDay() { return "17";};

}

