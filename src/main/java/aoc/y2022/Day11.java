package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
//        if (!testData1.get().isEmpty()) new Day11(testData1.get(), true);
//        if (!testData2.get().isEmpty()) new Day11(testData2.get(), true);
        new Day11(null, true);
        if (true) return;
        if (!testData1.get().isEmpty()) new Day11(testData1.get(), false);
        if (!testData2.get().isEmpty()) new Day11(testData2.get(), false);
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

    static Supplier<List<String>> testData2 = () -> """
            """.lines().collect(toList());

    Object solveQ2(List<String> input) {
        var result = 0L;
        //DAY2 !!
        return result;
    }

    Object solveQ1(List<String> input) {
        var result = 0L;

        int i = 0;

        var map = new HashMap<Integer, Monkey>();



        while (i < input.size()) {
            int id = toInt(input.get(i++));
            var items = toInts(input.get(i++));
            i++;
            var test = toInt(input.get(i++));
            var ontrue = toInt(input.get(i++));
            var onfalse = toInt(input.get(i++));
            i++;
            var monkey = new Monkey(id, Arrays.stream(items).map(lo -> Long.valueOf(lo)).collect(toList()), test, ontrue, onfalse, new long[1]);
            map.put(id, monkey);
        }

        var monkeys = map.values().stream().sorted((a,b) -> a.id - b.id).collect(toList());

        for (int j = 0 ; j < 10000; j++) {
//           if (j % 100 == 0) println("j = " + j);
          doRound(monkeys);
        }
        for (var v : map.values()) {
            System.out.println(v.id + " " + v.items);
//            map.values().stream()
        }

        var ins = map.values().stream().map(m -> m.inspected[0]).collect(toList());
        System.out.println(ins);

        return map.values().stream().map(m -> m.inspected[0]).sorted((a,b) -> b.compareTo(a)).limit(2).reduce(1L, (a,b) -> a *  b);
    }

    record Monkey(int id, List<Long> items, int test, int ontrue, int onfalse, long[] inspected) {
    }

    void doRound(List<Monkey> monkeys) {

//        var monkeys = map.values().stream().sorted((a,b) -> a.id - b.id).collect(toList());


        for (var monkey : monkeys) {

            var falses = new LinkedList<BigInteger>();
            var trues = new LinkedList<BigInteger>();
            for (var item : monkey.items) {
                monkey.inspected[0]++;
                item = dom(monkey.id, item, monkey.test);
//                item /= 3;
                if (item % monkey.test == 0) {
                    monkeys.get(monkey.ontrue).items.add(item);
                } else {
                    monkeys.get(monkey.onfalse).items.add(item);

                }


            }

            monkey.items.clear();

        }

    }

//    boolean test(int id, int)

//
//    long dom(int id, long val, int mod) {
//        return switch (id) {
//            case 0 -> val  * 19;
//            case 1 -> val + 6;
//            case 2 -> val * val;
//            case 3 -> val + 3;
//            default -> throw new AocException("Unknown id");
//        } % (23 * 19 * 13 * 17);
//    }

    long dom(int id, long val, int mod) {
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
        } % (3 * 13 * 19* 17 * 5 *7* 11 * 2);
    }

}

