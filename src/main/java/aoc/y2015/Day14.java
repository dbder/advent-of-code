package aoc.y2015;

import aoc.AU;
import aoc.misc.AocException;

import java.util.Arrays;
import java.util.List;

public class Day14 extends AU {

    List<Counter> counters = Arrays.asList(
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

    public static void main(String[] args) {
        new Day14();
    }

    Day14() {
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + solveQ2());
    }

    Object solveQ2() {
        range(1, 2503).forEach(i -> {
            counters.forEach(Counter::move);
            counters.sort((a, b) -> b.currentDistance - a.currentDistance);
            counters.stream()
                    .filter(c -> c.currentDistance == counters.get(0).currentDistance)
                    .forEach(c -> c.score++);
        });
        return counters
                .stream()
                .mapToInt(i -> i.score)
                .max();
    }

    Object solveQ1() {
        range(1, 2503).forEach(i -> {
            counters.forEach(Counter::move);
        });
        return counters
                .stream()
                .mapToInt(i -> i.currentDistance)
                .max();
    }

    @Override
    public String getDay() {
        return "14";
    }
}

class Counter {

    public Counter(String name, int speed, int stamina, int cooldown) {
        this.stamina = stamina;
        this.cooldown = cooldown;
        this.name = name;
        this.speed = speed;
        currentFlyLeft = stamina;
    }

    final int stamina;
    final int cooldown;
    int currentFlyLeft;
    int currentRestLeft;
    int currentDistance;
    String name;
    final int speed;
    int score;

    void move() {
        if (currentRestLeft > 0) {
            currentRestLeft--;
            if (currentRestLeft == 0) {
                currentFlyLeft = stamina;
            }
        } else if (currentFlyLeft > 0) {
            currentFlyLeft--;
            currentDistance += speed;
            if (currentFlyLeft == 0) {
                currentRestLeft = cooldown;
            }
        } else {
            throw new AocException("WTF");
        }
    }

    public String toString() {
        return name + " " + currentDistance + " " + score;
    }

}

