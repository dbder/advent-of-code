package aoc.y2017;

import aoc.AU;

import java.util.List;

public class Day04 extends AU {

    public static void main(String[] args) {
        new Day04();
    }

    Day04() {
        println("Day " + getDay() + " Q1: " + solveQ1(getInputLines()));
        println("Day " + getDay() + " Q2: " + solveQ2(getInputLines()));
    }

    Object solveQ2(List<String> input) {
        var result = 0L;
        for (var line : input) {
            var words = line.split(" ");
            for (int i = 0; i < words.length; i++) {
                words[i] = stringSort(words[i]);
            }
            var map = mapCountT(words);
            if (map.keySet().size() == 1 && map.containsKey(1)) result++;
        }

        return result;
    }

    Object solveQ1(List<String> input) {
        var result = 0L;
        for (var line : input) {
            var words = line.split(" ");
            var map = mapCountT(words);
            if (map.keySet().size() == 1 && map.containsKey(1)) result++;
        }

        return result;
    }

}

