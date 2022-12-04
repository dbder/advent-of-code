package aoc.y2018;

import aoc.AU;
import aoc.AocException;
import aoc.V2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day03 extends AU {

    public static void main(String[] args) {
        new Day03();
    }

    Day03() {
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + solveQ2());
    }

    Object solveQ2() {

        var input = getInputLines();

        Map<V2, List<Integer>> map = new HashMap<>();

        for (var l : input) {
            var ints = toInts(l);
            for (int r = ints[1]; r < ints[1] + ints[3]; r++) {
                for (int c = ints[2]; c < ints[2] + ints[4]; c++) {
                    map.computeIfAbsent(new V2(r, c), k -> new ArrayList<>()).add(ints[0]);
                }
            }
        }

        for (var l : input) {
            var ints = toInts(l);

            boolean dirty = false;
            for (int r = ints[1]; r < ints[1] + ints[3]; r++) {
                for (int c = ints[2]; c < ints[2] + ints[4]; c++) {
                    var p = new V2(r, c);
                    var list = map.computeIfAbsent(p, k -> new ArrayList<>());
                    if (list.size() > 1) dirty = true;
                }
            }
            if (!dirty) return ints[0];
        }

        throw new AocException("No solution found");

    }

    Object solveQ1() {
        var result = 0L;
        var input = getInputLines();


        Map<V2, List<Integer>> map = new HashMap<>();

        for (var l : input) {
            var ints = toInts(l);

            for (int r = ints[1]; r < ints[1] + ints[3]; r++) {
                for (int c = ints[2]; c < ints[2] + ints[4]; c++) {
                    var p = new V2(r, c);
                    var list = map.computeIfAbsent(p, k -> new ArrayList<>());
                    list.add(ints[0]);
                    if (list.size() == 2) result++;
                }
            }
        }
        return result;
    }

}

