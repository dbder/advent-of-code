package aoc.y2022;

import aoc.AU;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day01 extends AU {

    public static void main(String[] args) {
        new Day01();
    }

    Day01 () {
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + solveQ2());
    }

    Object solveQ2() {
        var input = getInputLines();

        int sub = 0;
        List<Integer> list = new ArrayList<>();
        for (var line : input) {
            if (line.equals("")) {
                list.add(sub);
                sub = 0;
                continue;
            }
            var tmp = toInt(line);
            sub += tmp;

        }
        list.add(sub);
        Collections.sort(list);

        return list.get(list.size() - 1) + list.get(list.size() - 2) + list.get(list.size() - 3);
    }

    Object solveQ1() {
        var result = 0;
        var input = getInputLines();

        int sub = 0;
        for (var line : input) {
            if (line.equals("")) {
                sub = 0;
                continue;
            }
            var tmp = toInt(line);
            sub += tmp;
            result = Math.max(result, sub);
        }

        return result;
    }

}

