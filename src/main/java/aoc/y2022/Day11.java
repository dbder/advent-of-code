package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toList;

public class Day11 extends AU {

    Day11(List<String> testData, boolean q1) {
        switch ((q1 ? "q1" : "q2") + "-" + (testData == null ? "real" : "test")) {
            case "q1-test" -> println("Test Q1: " + solveQ1(testData));
            case "q1-real" -> println("Real Q1: " + solveQ1(getInputLines()));
            case "q2-test" -> println("Test Q2: " + solveQ2(testData));
            case "q2-real" -> println("Real Q2: " + solveQ2(getInputLines()));
            default -> throw new AocException("Unknown case");
        }
    }

    public static void main(String[] args) {
        new Day11(null, true);
        new Day11(null, false);
    }

    static Supplier<List<String>> testData1 = () -> """
            Monkey 0:
               Starting items: 79, 98
               Operation: new = old * 19
               Test: divisible by 23
                 If true: throw to monkey 2
                 If false: throw to monkey 3
             
             Monkey 1:
               Starting items: 54, 65, 75, 74
               Operation: new = old + 6
               Test: divisible by 19
                 If true: throw to monkey 2
                 If false: throw to monkey 0
             
             Monkey 2:
               Starting items: 79, 60, 97
               Operation: new = old * old
               Test: divisible by 13
                 If true: throw to monkey 1
                 If false: throw to monkey 3
             
             Monkey 3:
               Starting items: 74
               Operation: new = old + 3
               Test: divisible by 17
                 If true: throw to monkey 0
                 If false: throw to monkey 1
            """.lines().collect(toList());

    Object solveQ2(List<String> input) {
        var monkeys = getMonkeys(input);
        int modulo = monkeys.stream().map(m -> m.test).reduce(1, (a, b) -> a * b);
        for (int j = 0 ; j < 10000; j++) doRound(monkeys, modulo, 1);
        return monkeys.stream().map(m -> m.inspected[0]).sorted(reverseOrder()).limit(2).reduce(1L, (a, b) -> a *  b);
    }

    Object solveQ1(List<String> input) {
        var monkeys = getMonkeys(input);
        int modulo = monkeys.stream().map(m -> m.test).reduce(1, (a, b) -> a * b);
        for (int j = 0 ; j < 20; j++) {
            doRound(monkeys, modulo, 3);
        }
        return monkeys.stream().map(m -> m.inspected[0]).sorted(reverseOrder()).limit(2).reduce(1L, (a, b) -> a *  b);
    }

    List<Monkey> getMonkeys(List<String> input) {
        var monkeys = new ArrayList<Monkey>();
        int i = 0;
        while (i < input.size()) {
            int id = toInt(input.get(i++));
            var items = toInts(input.get(i++));
            i++;
            var test = toInt(input.get(i++));
            var ontrue = toInt(input.get(i++));
            var onfalse = toInt(input.get(i++));
            i++;
            var monkey = new Monkey(id, Arrays.stream(items).map(Long::valueOf).collect(toList()), test, ontrue, onfalse, new long[1]);
            monkeys.add(monkey);
        }
        return monkeys;
    }

    record Monkey(int id, List<Long> items, int test, int ontrue, int onfalse, long[] inspected) {}

    void doRound(List<Monkey> monkeys, long modulo, int div) {
        modulo *= div;
        for (var monkey : monkeys) {
            for (var item : monkey.items) {
                monkey.inspected[0]++;
                item = dom(monkey.id, item, modulo);
                item /= div;
                if (item % monkey.test == 0) {
                    monkeys.get(monkey.ontrue).items.add(item);
                } else {
                    monkeys.get(monkey.onfalse).items.add(item);
                }
            }
            monkey.items.clear();
        }
    }

    long dom(int id, long val, long mod) {
        return switch (id) {
            case 0 -> val * 13;
            case 1 -> val + 2;
            case 2 -> val + 8;
            case 3 -> val + 1;
            case 4 -> val *  17;
            case 5 -> val + 3;
            case 6 -> val * val;
            case 7 -> val + 6;
            default -> throw new AocException("Unknown id");
        } % (mod);
    }

}

