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

        for (int i = 0; i < 100; i++) {
            if (counts[i] > 0) {
                println(i + " " + counts[i]);
            }
        }
    }

    Object solveQ2() {
        return null;
    }

    Object solveQ1() {
        var input = getInputLines().stream().map(i -> Integer.parseInt(i)).sorted().toList();
        for (int x = 0; x < input.size(); x++) {
            count(input, x, input.get(x), 150, 1);
        }


        return count;
    }
    int count = 0;
    int[] counts = new int[100];
    void count (List<Integer> input, int index, int sum, int target, int containers) {

        if(sum == target) {
            count++;
            counts[containers]++;
            return;
        }
        for (int i = index + 1; i < input.size(); i++) {
            count(input, i, sum + input.get(i), target, containers + 1);
        }
    }


    public String getDay() { return "17";};

}

