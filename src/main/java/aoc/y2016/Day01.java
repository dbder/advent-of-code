package aoc.y2016;

import aoc.AU;
import aoc.V2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class Day01 extends AU {

    @Override
    protected String getDay() {
        return "01";
    }


    public static void main(String[] args) {
        new Day01();
    }

    Day01() {
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + solveQ2());
    }

    Object solveQ2() {
        var input = getInputLines().get(0);

        V2 position = new V2(0, 0);

        var in = input.split(", ");

        var direction = 0;
        Set<V2> set = new HashSet<>();
        V2 prev;

        for (var d : in) {
            char c = d.charAt(0);
            prev = position;
            switch (c) {
                case 'L' -> {
                    direction--;
                    if (direction < 0) direction = 3;
                }
                case 'R' -> {
                    direction++;
                    if (direction > 3) direction = 0;
                }
            }
            position = position.move(direction, Integer.parseInt(d.substring(1)));

            for (int r = Math.min(prev.row(), position.row()); r <= Math.max(prev.row(), position.row()); r++) {
                for (int cl = Math.min(prev.col(), position.col()); cl <= Math.max(prev.col(), position.col()); cl++) {
                    if (set.contains(new V2(r, cl)) && !new V2(r, cl).equals(prev)) {
                        return Math.abs(cl) + Math.abs(r);
                    }
                    set.add(new V2(r, cl));
                }
            }
        }

        return Math.abs(position.col()) + Math.abs(position.row());
    }

    Object solveQ1() {
        var input = getInputLines().get(0);

        V2 position = new V2(0, 0);

        var in = input.split(", ");

        var direction = 0;

        for (var d : in) {
            char c = d.charAt(0);
            switch (c) {
                case 'L':
                    direction--;
                    if (direction < 0) direction = 3;
                    break;
                case 'R':
                    direction++;
                    if (direction > 3) direction = 0;
                    break;
            }
            switch (direction) {
                case 0:
                    position = new V2(
                            position.row() + Integer.parseInt(d.substring(1)),
                            position.col()
                    );
                    break;
                case 1:
                    position = new V2(
                            position.row(),
                            position.col() + Integer.parseInt(d.substring(1))
                    );
                    break;
                case 2:
                    position = new V2(
                            position.row() - Integer.parseInt(d.substring(1)),
                            position.col());
                    break;
                case 3:
                    position = new V2(
                            position.row(),
                            position.col() - Integer.parseInt(d.substring(1)));
                    break;
            }
        }
        return Math.abs(position.col()) + Math.abs(position.row());
    }

}
