package aoc.y2015;

import aoc.AU;

import java.util.List;

public class Day21 extends AU {

    public static void main(String[] args) {
        new Day21();
    }

    List<Triple> weapons = List.of(
            new Triple(8, 4, 0),
            new Triple(10, 5,0),
            new Triple(25, 6,0),
            new Triple(40, 7,0),
            new Triple(74, 8,0)
    );
    List<Triple> armors = List.of(
            new Triple(13,0, 1),
            new Triple(31,0, 2),
            new Triple(53,0, 3),
            new Triple(75,0, 4),
            new Triple(102,0, 5),
            new Triple(0, 0,0)
    );

    List<Triple> rings = List.of(
            new Triple(25, 1,0),
            new Triple(50, 2,0),
            new Triple(100, 3,0),
            new Triple(20,0, 1),
            new Triple(40,0, 2),
            new Triple(80,0, 3),
            new Triple(0, 0,0)
    );


    Day21() {
        println("Day " + getDay() + " Q1: " + solve(false));
        println("Day " + getDay() + " Q2: " + solve(true));
    }

    Object solve(boolean q2) {
        var result = q2 ? 0 : 10000;
        for (var weap : weapons) {
            for (var arm : armors) {
                for (var r1 : rings) {
                    for (var r2 : rings) {
                        if (r1 == r2) continue;
                        if (!wins(100, weap.dam() + r1.dam() + r2.dam(), arm.arm() + r1.arm() + r2.arm())){
                            if (q2) result = Math.max(result, weap.cost() + arm.cost() + r1.cost() + r2.cost());
                        } else {
                            if (!q2) result = Math.min(result, weap.cost() + arm.cost() + r1.cost() + r2.cost());
                        }
                    }
                }
            }
        }
        return result;
    }

    boolean wins(int hp, int damage, int armor) {
        int bdamage = 8;
        int barmor = 2;
        int hitpoints = 100;

        while (true) {
            hitpoints -= Math.max(1, damage - barmor);
            if (hitpoints <= 0) {
                return true;
            }
            hp -= Math.max(1, bdamage - armor);
            if (hp <= 0) {
                return false;
            }
        }
    }

    @Override
    public String getDay() {
        return "21";
    }
}

record Triple(int cost, int dam, int arm) {
}

