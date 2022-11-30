package aoc.y2016;

import aoc.AU;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day10 extends AU {

    @Override
    protected String getDay() {
        return "10";
    }


    public static void main(String[] args) {
        new Day10();
    }

    Day10() {
        println("Day " + getDay() + " Q1: " + solve(false));
        println("Day " + getDay() + " Q2: " + solve(true));
    }

    boolean q2;
    Object solve(boolean q2) {
        this.q2 = q2;
        var input = getInputLines();
        var bots = new HashMap<Integer, Bot>();
        for (var str : input) {
            if (str.startsWith("value")) {
                var ints = toInts(str);
                var value = ints[0];
                var id = ints[1];
                var bot = bots.computeIfAbsent(id, i -> new Bot(id, bots));
                bot.addValue(value);
            } else {
                var ints = toInts(str);
                var bot = bots.computeIfAbsent(ints[0], i -> new Bot(ints[0], bots));
                if (str.contains("low to bot")) {
                    bot.setLow(ints[1]);
                } else {
                    bot.setLow((-ints[1]) - 1);
                }
                if (str.contains("high to bot")) {
                    bot.setHigh(ints[2]);
                } else {
                    bot.setHigh((-ints[2]) - 1);
                }
            }
        }
        if (q2) return bots.get(-1).values.get(0) * bots.get(-2).values.get(0) * bots.get(-3).values.get(0);

        return q1answer;
    }

    Integer q1answer = null;
    class Bot {
        int id;
        Integer low;
        Integer high;
        List<Integer> values = new ArrayList<>();

        public void setLow(int low) {
            this.low = low;
            checkValue();
        }

        public void setHigh(int high) {
            this.high = high;
            checkValue();
        }

        void checkValue() {
            if (low == null || high == null) return ;
            if (values.size() == 2) {
                values.sort(Integer::compareTo);
                var l = bots.computeIfAbsent(low, i -> new Bot(low, bots));
                var h = bots.computeIfAbsent(high, i -> new Bot(high, bots));
                l.addValue(values.get(0));
                h.addValue(values.get(1));
                if (values.get(0) == 17 && values.get(1) == 61 && q1answer == null) {
                    q1answer = id;
                }
                values = new ArrayList<>();
            }
        }

        final Map<Integer, Bot> bots;

        Bot(int id, Map<Integer, Bot> bots) {
            this.id = id;
            this.bots = bots;
        }

        public void addValue(int value) {
            values.add(value);
            checkValue();
        }

        @Override
        public String toString() {
            return "Bot{" +
                    "id=" + id +
                    ", low=" + low +
                    ", high=" + high +
                    ", values=" + values +
                    '}';
        }
    }
}

