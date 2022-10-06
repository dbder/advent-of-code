package aoc.y2015;

import aoc.AU;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;

public class Day15 extends AU {

    public static void main(String[] args) {
        var day = new Day15();
        println("Day " + day.getDay() + " Q1: " + day.solveQ1());
        day = new Day15();
        println("Day " + day.getDay() + " Q2: " + day.solveQ2());
    }

    static List<Candy> candies;

//    static {
//        candies = Arrays.asList(
//                new Candy("Butterscotch", -1, -2, 6, 3, 8),
//                new Candy("Cinnamon", 2, 3, -2, -1, 3)
//        );
//    }

    static {
        candies = Arrays.asList(
                new Candy("Sprinkles", 2, 0, -2, 0, 3),
                new Candy("Butterscotch", 0, 5, -3, 0, 3),
                new Candy("Chocolate", 0, 0, 5, -1, 8),
                new Candy("Candy", 0, -1, 0, 5, 8)
        );
    }

    Object solveQ2() {
        return null;
    }

    Object solveQ1() {
        var input = getInputLines();
        var result = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                for (int k = 0; k < 100; k++) {
                    for (int l = 0; l < 100; l++) {
                        if (i + j + k + l == 100) {
//
                            var score = score(i, j, k, l);
                            if (score > result) {
                                result = score;
                            }
                        }
                    }
                }
            }
        }

        println(score(44, 56, 0, 0));


        return result;
    }

    public int score(int i, int j, int k, int l) {
        if (i + j + k + l != 100) {
            return 0;
        }
        int total1 = getTotal1(i, j, k, l, (c) -> c.capacity);
        int total2 = getTotal1(i, j, k, l, (c) -> c.durability);
        int total3 = getTotal1(i, j, k, l, (c) -> c.flavor);
        int total4 = getTotal1(i, j, k, l, (c) -> c.texture);

        if (total1 * total2 * total3 * total4 == 5454000) {
            System.out.println(total1 + " " + total2 + " " + total3 + " " + total4);
            System.out.println(i + " " + j + " " + k + " " + l);
        }
        if (total1 < 0 || total2 < 0 || total3 < 0 || total4 < 0) {
            return 0;
        }

//        total1 *= Math.max(1, getScore(candies.get(1), j));
//        total1 *= Math.max(1, getScore(candies.get(2), k));
//        total1 *= Math.max(1, getScore(candies.get(3), l));
        return total1 * total2 * total3 * total4;

    }

    private static int getTotal1(int i, int j, int k, int l, Function<Candy, Integer> function) {
        int total1 = 0;
        total1 += function.apply(candies.get(0)) * i;
        total1 += function.apply(candies.get(1)) * j;
        total1 += function.apply(candies.get(2)) * k;
        total1 += function.apply(candies.get(3)) * l;
        return total1;
    }
    // 1184832 low


    public int getScore(Candy candy, int amount) {
        return candy.capacity * amount +
                candy.durability * amount +
                candy.flavor * amount +
                candy.texture * amount;
    }

    public int getCapacity(int amount) {
        return candies.stream().mapToInt(candy -> candy.capacity * amount).sum();
    }

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

