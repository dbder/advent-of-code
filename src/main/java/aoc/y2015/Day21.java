package aoc.y2015;

import aoc.AU;
import aoc.AocException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Day21 extends AU {
    private static final Logger log = LogManager.getLogger(Day21.class);

    public static void main(String[] args) {
        new Day21();
    }

    int bhp = 100;
    int bd = 8;
    int ba = 2;

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
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + solveQ2());
    }

    Object solveQ2() {
        return null;
    }

    Object solveQ1() {

        var result = 0;

        for (var weap : weapons) {
            if (!wins(100, weap.dam(), 0)) {
                result = Math.max(result, weap.cost());
            }
            for (var arm : armors) {
                if (!wins(100, weap.dam(), arm.arm())) {
                    result = Math.max(result, weap.cost() + arm.cost());
                }

                for (var r1 : rings) {
                    if (!wins(100, weap.dam() + r1.dam(), arm.arm() + r1.arm())) {
                        result = Math.max(result, weap.cost()  + arm.cost() + r1.cost());
                    }
                    for (var r2 : rings) {
                        if (!wins(100, weap.dam() + r1.dam() + r2.dam(), arm.arm() + r1.arm() + r2.arm())){
                            result = Math.max(result, weap.cost() + arm.cost() + r1.cost() + r2.cost());
                        }
                    }
                }
            }
        }
        return result;
    }

    boolean wins(int hp, int damage, int armor) {
        var bossHp = bhp;

        while (hp > 0 && bossHp > 0) {
            bossHp -= Math.max(1, damage - ba);
            if (bossHp <= 0) {
                return true;
            }
            hp -= Math.max(1, bd - armor);
            if (hp <= 0) {
                return false;
            }
        }
        throw new AocException("Should not happen");
    }


    @Override
    public String getDay() {
        return "21";
    }


}

record Triple(int cost, int dam, int arm) {
}

