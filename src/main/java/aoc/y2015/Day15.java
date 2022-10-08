package aoc.y2015;

import aoc.AU;

import java.util.Arrays;
import java.util.List;
import java.util.function.ToIntFunction;

public class Day15 extends AU {

    public static void main(String[] args) {
        new Day15();
    }

    Day15() {
        println("Day " + getDay() + " Q1: " + solve(false));

        println("Day " + getDay() + " Q2: " + solve(true));
    }

    static List<Candy> candies = Arrays.asList(
            new Candy("Sprinkles", 2, 0, -2, 0, 3),
            new Candy("Butterscotch", 0, 5, -3, 0, 3),
            new Candy("Chocolate", 0, 0, 5, -1, 8),
            new Candy("Candy", 0, -1, 0, 5, 8)
    );

    Object solve(boolean part2) {
        var result = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                for (int k = 0; k < 100; k++) {
                    for (int l = 0; l < 100; l++) {
                        if (i + j + k + l == 100) {
                            var score = score(i, j, k, l, part2);
                            if (score > result) {
                                result = score;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public int score(int i, int j, int k, int l, boolean cal500) {
        if (i + j + k + l != 100) {
            return 0;
        }
        int total1 = getTotal1(i, j, k, l, c -> c.capacity);
        int total2 = getTotal1(i, j, k, l, c -> c.durability);
        int total3 = getTotal1(i, j, k, l, c -> c.flavor);
        int total4 = getTotal1(i, j, k, l, c -> c.texture);
        int total5 = getTotal1(i, j, k, l, c -> c.calories);

        if (cal500 && total5 != 500) {
            return 0;
        }

        if (total1 < 0 || total2 < 0 || total3 < 0 || total4 < 0) {
            return 0;
        }
        return total1 * total2 * total3 * total4;
    }

    private static int getTotal1(int i, int j, int k, int l, ToIntFunction<Candy> function) {
        int total1 = 0;
        total1 += function.applyAsInt(candies.get(0)) * i;
        total1 += function.applyAsInt(candies.get(1)) * j;
        total1 += function.applyAsInt(candies.get(2)) * k;
        total1 += function.applyAsInt(candies.get(3)) * l;
        return total1;
    }

    @Override
    public String getDay() {
        return "15";
    }
}

class Candy {
    Candy(String name, int capacity, int durability, int flavor, int texture, int calories) {
        this.name = name;
        this.capacity = capacity;
        this.durability = durability;
        this.flavor = flavor;
        this.texture = texture;
        this.calories = calories;
    }

    String name;
    int capacity;
    int durability;
    int flavor;
    int texture;
    int calories;
}

