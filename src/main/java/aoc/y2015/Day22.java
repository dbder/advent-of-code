package aoc.y2015;

import aoc.AU;

import java.util.List;
import java.util.stream.Collectors;

public class Day22 extends AU {

    @Override
    protected String getDay() {
        return "22";
    }


    int bosshitpoints = 58;
    int bossdamage = 9;

    int playerhitpoints = 50;
    int playermana = 500;

    public static void main(String[] args) {
        new Day22();
    }

    Day22() {
        println("Day " + getDay() + " Q1: " + solve());
        manaspendMax = Integer.MAX_VALUE;
        q2 = true;
        println("Day " + getDay() + " Q2: " + solve());
    }

    int manaspendMax = Integer.MAX_VALUE;
    boolean q2;


    Object solve() {
        runs(playerhitpoints, bosshitpoints, playermana, 0, 0, true, List.of());
        return manaspendMax;
    }

    void runs(int hp, int bossHP, int mana, int manaspend, int armor, boolean turn, List<Effect> effects) {
        if (mana < 0) return;
        if (q2) {
            hp--;
        }
        if (hp <= 0) return;
        if (manaspend >= manaspendMax) return;

        effects = effects.stream().map(e -> new Effect(e.name, e.turns)).collect(Collectors.toList());

        int addedArmor = 0;
        for (Effect e : effects) {
            e.turns--;
            if (e.name.equals("Shield")) {
                addedArmor += 7;
            } else if (e.name.equals("Poison")) {
                bossHP -= 3;
            } else if (e.name.equals("Recharge")) {
                mana += 101;
            }
        }

        if (bossHP <= 0) {
            manaspendMax = Math.min(manaspendMax, manaspend);
            return;
        }

        effects = effects.stream().filter(e -> e.turns > 0).toList();
        if (turn) {
            List<Effect> neweffects;
            effects = effects.stream().map(e -> new Effect(e.name, e.turns)).toList();
            runs(hp, bossHP - 4, mana - 53, manaspend + 53, armor, false, effects);

            effects = effects.stream().map(e -> new Effect(e.name, e.turns)).toList();
            runs(hp + 2, bossHP - 2, mana - 73, manaspend + 73, armor, false, effects);


            if (effects.stream().noneMatch(e -> e.name.equals("Shield"))) {
                neweffects = effects.stream().map(e -> new Effect(e.name, e.turns)).collect(Collectors.toList());
                neweffects.add(new Effect("Shield", 6));
                runs(hp, bossHP, mana - 113, manaspend + 113, armor, false, neweffects);
            }

            if (effects.stream().noneMatch(e -> e.name.equals("Poison"))) {
                neweffects = effects.stream().map(e -> new Effect(e.name, e.turns)).collect(Collectors.toList());
                neweffects.add(new Effect("Poison", 6));
                runs(hp, bossHP, mana - 173, manaspend + 173, armor, false, neweffects);
            }

            if (effects.stream().noneMatch(e -> e.name.equals("Recharge"))) {
                neweffects = effects.stream().map(e -> new Effect(e.name, e.turns)).collect(Collectors.toList());
                neweffects.add(new Effect("Recharge", 5));
                runs(hp, bossHP, mana - 229, manaspend + 229, armor, false, neweffects);
            }
        } else {
            hp -= Math.max(1, bossdamage - (addedArmor));
            runs(hp, bossHP, mana, manaspend, armor, true, effects);
        }
    }

}

class Effect {
    Effect(String name, int turns) {
        this.name = name;
        this.turns = turns;
    }

    int turns;
    String name;

    @Override
    public String toString() {
        return "Effect{" +
                "turns=" + turns +
                ", name='" + name + '\'' +
                '}';
    }
}