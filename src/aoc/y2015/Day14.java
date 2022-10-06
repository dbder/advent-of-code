package aoc.y2015;

import aoc.AU;
import aoc.AocException;

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
        new Day14().solveQ1();
        new Day14().solveQ2();
    }

    void solveQ2() {
        for (int i = 0; i < 2503; i++) {
            for (Counter counter : counters) {
                counter.move();
            }
            counters.sort((a, b) -> b.distance - a.distance);
            counters.stream()
                    .filter(c -> c.distance == counters.get(0).distance)
                    .forEach(c -> c.score++);
        }
        println("Day " + getDay() + " Q2: " + counters.stream().mapToInt(i -> i.score).max());    }

    void solveQ1() {
        for (int i = 0; i < 2503; i++) {
            for (Counter counter : counters) {
                counter.move();
            }
        }
        println("Day " + getDay() + " Q1: " + counters.stream().mapToInt(i -> i.distance).max());
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

    int stamina;
    int cooldown;
    int currentFlyLeft;
    int currentRestLeft;
    int distance;
    String name;
    int speed;
    int score;

    void move() {
        if (currentRestLeft > 0) {
            currentRestLeft--;
            if (currentRestLeft == 0) {
                currentFlyLeft = stamina;
            }
        } else if (currentFlyLeft > 0) {
            currentFlyLeft--;
            distance += speed;
            if (currentFlyLeft == 0) {
                currentRestLeft = cooldown;
            }
        }
        throw new AocException("Should not happen");
    }

    public String toString() {
        return name + " " + distance + " " + score;
    }

}

