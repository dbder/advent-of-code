package aoc.y2016;

import aoc.AU;
import aoc.Position2D;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.text.Position;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day01 extends AU {
    private static final Logger log = LogManager.getLogger(Day01.class);

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

        Position2D position = new Position2D(0, 0);

        var in = input.split(", ");

        var direction = 0;
        Set<Position2D> set = new HashSet<>();
        Position2D prev = position;

        for (var d : in) {
            char c = d.charAt(0);
            prev = position;
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
                    position = new Position2D(
                            position.row() + Integer.parseInt(d.substring(1)),
                            position.col()
                    );
                    break;
                case 1:
                    position = new Position2D(
                            position.row(),
                            position.col() + Integer.parseInt(d.substring(1))
                    );
                    break;
                case 2:
                    position = new Position2D(
                            position.row() - Integer.parseInt(d.substring(1)),
                            position.col());
                    break;
                case 3:
                    position = new Position2D(
                            position.row(),
                            position.col() - Integer.parseInt(d.substring(1)));
                    break;
            }

            for (int r = (int) Math.min((int) prev.row(), position.row()); r <= (int) Math.max((int) prev.row(), position.row()); r++) {
                for (int cl = (int) Math.min((int) prev.col(), position.col()); cl <= (int) Math.max((int) prev.col(), position.col()); cl++) {
                    if (set.contains(new Position2D(r, cl)) && !new Position2D(r, cl).equals(prev)) {
                        return Math.abs(cl) + Math.abs(r);
                    }
                    set.add(new Position2D(r, cl));
                }
            }
        }

        return Math.abs(position.col()) + Math.abs(position.row());
    }

    Object solveQ1() {
        var input = getInputLines().get(0);

        Position2D position = new Position2D(0, 0);

        var in = input.split(", ");

        var direction = 0;
        Set<Position2D> set = new HashSet<>();
        Position2D prev = position;

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
                    position = new Position2D(
                            position.row() + Integer.parseInt(d.substring(1)),
                            position.col()
                    );
                    break;
                case 1:
                    position = new Position2D(
                            position.row(),
                            position.col() + Integer.parseInt(d.substring(1))
                    );
                    break;
                case 2:
                    position = new Position2D(
                            position.row() - Integer.parseInt(d.substring(1)),
                            position.col());
                    break;
                case 3:
                    position = new Position2D(
                            position.row(),
                            position.col() - Integer.parseInt(d.substring(1)));
                    break;
            }
        }
        return Math.abs(position.col()) + Math.abs(position.row());
    }

}

class Actor2D {
    private int row;
    private int col;

    private Set<String> map = new HashSet<>();

    private int direction;

    public void turnLeft() {
        direction--;
        if (direction < 0) direction = 3;
    }
    public void turnRight() {
        direction++;
        if (direction > 3) direction = 0;
    }

    public void move(int steps) {
        switch (direction) {
            case 0:
                row += steps;
                break;
            case 1:
                col += steps;
                break;
            case 2:
                row -= steps;
                break;
            case 3:
                col -= steps;
                break;
        }
    }

    public Actor2D(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }


    @Override
    public String toString() {
        return "Actor2D{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}

