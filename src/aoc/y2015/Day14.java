package aoc.y2015;

import aoc.AU;
import aoc.AocException;

import java.util.List;

public class Day14 extends AU {

    public static void main(String[] args) {
        new Day14().solveQ1();
        new Day14().solveQ2();
    }

    void solveQ2() {
//        println("Day " + day +" Q2: " + "");
    }

    void solveQ1() {
        var input = getInputLinesT();
        var result = 0;

        List<Counter> counters = List.of(
                new Counter("V", 19, 7, 124),
                new Counter("Rudolph", 3, 15, 28),
                new Counter("Donner", 19, 9, 164),
                new Counter("Blitzen", 19, 9, 158),
                new Counter("Comet", 13, 7, 82),
                new Counter("Cupid", 25, 6, 145),
                new Counter("Dasher", 14, 3, 38),
                new Counter("Dancer", 3, 16, 37),
                new Counter("Prancer", 25, 6, 143)
        );

        for (int i = 0; i < 2503; i++) {
            for (Counter counter : counters) {
                counter.move(10000000);
            }
        }

        for (Counter counter : counters) {
            result = Math.max(result, counter.distance);
        }


        println("Day " + getDay() + " Q1: " + result);
    }


    public String getDay() {
        return "14";
    }

    ;

}

class Counter {

    public Counter(String name, int speed, int stamina, int cooldown) {
        this.reload = stamina;
        this.cooldown = cooldown;
        this.name = name;
        this.speed = speed;
        currentFlyLeft = stamina;
    }

    int reload;
    int cooldown;
    int currentFlyLeft;
    int currentRestLeft;
    int distance;
    String name;
    int speed;

    boolean move(int target) {
        if (currentRestLeft > 0) {
            currentRestLeft--;
            if (currentRestLeft == 0) {
                currentFlyLeft = reload;
            }
            return false;
        } else if (currentFlyLeft > 0) {
            currentFlyLeft--;
            distance += speed;
            if (distance >= target) {
                return true;
            }
            if (currentFlyLeft == 0) {
                currentRestLeft = cooldown;
            }
            return false;
        }
        throw new AocException("Should not happen");
    }

}

