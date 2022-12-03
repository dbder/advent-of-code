package aoc.y2017;

import aoc.AU;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day02 extends AU {

    public static void main(String[] args) {
        new Day02();
    }

    Day02() {
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + solveQ2());
    }

    Object solveQ2() {
        var result = 0;
        var input = getInputLines();

        var list = new ArrayList<Integer>();

        for (var line : input) {
            var ints = toIntList(line);
            Collections.sort(ints);
            list.add(ints.get(ints.size()-1) - ints.get(0));
            result += getED(ints);

        }
        return result;
    }

    Object solveQ1() {
        var result = 0;
        var input = getInputLines();

        for (var line : input) {
            var ints = toIntList(line);
            Collections.sort(ints);
            result += ints.get(ints.size()-1) - ints.get(0);
        }
        return result;
    }


    int getED(List<Integer> list) {
        for (int i = 0; i < list.size(); i++ ) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(j) % list.get(i) == 0) {
                    return list.get(j) / list.get(i);
                }
            }
        }
        return -1;
    }
}

