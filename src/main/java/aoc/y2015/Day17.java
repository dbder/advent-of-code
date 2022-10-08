package aoc.y2015;

import aoc.AU;

import java.util.Comparator;
import java.util.List;

public class Day17 extends AU {

    public static void main(String[] args) {
        new Day17();
    }

    Day17() {
        var sol = solve();
        println("Day " + getDay() + " Q1: " + solve().size());
        println("Day " + getDay() + " Q2: " + sol.stream().filter(l -> l.size() == sol.get(0).size()).count());
    }

    List<List<Integer>> solve() {
        return powerSet(getInputLines().stream().map(Integer::parseInt).toList())
                .filter(l -> l.stream().reduce(0, Integer::sum) == 150)
                .sorted(Comparator.comparingInt(List::size))
                .toList();
    }

    @Override
    public String getDay() {
        return "17";
    }

}

