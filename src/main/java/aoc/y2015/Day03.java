package aoc.y2015;

import aoc.AU;
import aoc.AocException;
import aoc.Position2D;

import java.util.HashSet;

public class Day03 extends AU {

    public static void main(String[] args) {

    }

    Day03() {
        var input = getInputString();
        solveQ1(input);
        solveQ2(input);
    }


    static void solveQ1(String input) {
        var set = new HashSet<Position2D>();
        var current = getP2D(0, 0);
        set.add(current);
        for (var c : input.toCharArray()) {
            switch (c) {
                case '<' -> current = current.add(new int[]{0, -1});
                case '>' -> current = current.add(new int[]{0, 1});
                case 'v' -> current = current.add(new int[]{-1, 0});
                case '^' -> current = current.add(new int[]{1, 0});
                default -> throw new AocException("can not");
            }
            set.add(current);
        }
        println("Day 03 Q1: " + set.size());
    }


    static void solveQ2(String input) {
        var set = new HashSet<Position2D>();
        var current1 = getP2D(0, 0);
        var current2 = getP2D(0, 0);
        set.add(current1);
        boolean doit = false;
        for (var c : input.toCharArray()) {
            doit = !doit;
            if (doit) continue;
            switch (c) {
                case '<' -> current1 = current1.add(new int[]{0, -1});
                case '>' -> current1 = current1.add(new int[]{0, 1});
                case 'v' -> current1 = current1.add(new int[]{-1, 0});
                case '^' -> current1 = current1.add(new int[]{1, 0});
                default -> throw new AocException("can not");
            }
            set.add(current1);
        }
        doit = true;
        for (var c : input.toCharArray()) {
            doit = !doit;
            if (doit) continue;
            switch (c) {
                case '<' -> current2 = current2.add(new int[]{0, -1});
                case '>' -> current2 = current2.add(new int[]{0, 1});
                case 'v' -> current2 = current2.add(new int[]{-1, 0});
                case '^' -> current2 = current2.add(new int[]{1, 0});
                default -> throw new AocException("can not");
            }
            set.add(current2);
        }
        println("Day 03 Q2: " + set.size());
    }

    @Override
    public String getDay() {
        return "03";
    }
}

