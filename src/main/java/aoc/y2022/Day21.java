package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class Day21 extends AU {

    Day21(List<String> testData, boolean q1) {
        var startTime = System.currentTimeMillis();
        switch ((q1 ? "q1" : "q2") + "-" + (testData == null ? "real" : "test")) {
            case "q2-test" -> println("Test Q2: " + solveQ2(testData));
            case "q2-real" -> println("Real Q2: " + solveQ2(getInputLines()));
            default -> throw new AocException("Unknown case");
        }
        println("Time: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    public static void main(String[] args) {
        if (!testData1.get().isEmpty()) new Day21(testData1.get(), false);
        new Day21(null, false);
    }

    static Supplier<List<String>> testData1 = () -> """
            root: pppw + sjmn
            dbpl: 5
            cczh: sllz + lgvd
            zczc: 2
            ptdq: humn - dvpt
            dvpt: 3
            lfqf: 4
            humn: 5
            ljgn: 2
            sjmn: drzm * dbpl
            sllz: 4
            pppw: cczh / lfqf
            lgvd: ljgn * ptdq
            drzm: hmdt - zczc
            hmdt: 32
            """.lines().collect(toList());

    Object solveQ2(List<String> input) {

        List<Monkey> monkeys = new ArrayList<>();
        for (var line : input) {
            if (line.contains("humn:")) {
                continue;
            }
            monkeys.add(Monkey.parse(line));
        }

        HashMap<String, Monkey> monkeyMap = new HashMap<>();
        for (var monkey : monkeys) {
            monkeyMap.put(monkey.name, monkey);
        }

        var root = monkeyMap.get("root");
        var rootleft = monkeyMap.get(root.left);
        var rootright = monkeyMap.get(root.right);
        Long testValue = getValue(rootright, monkeyMap);
        Monkey current = rootleft;
        while (true) {

            var leftmonkey = monkeyMap.get(current.left);
            var rightmonkey = monkeyMap.get(current.right);


            var left = getValueSafe(leftmonkey, monkeyMap, false);
            var right = getValueSafe(rightmonkey, monkeyMap, false);

            var operand = left;
            if (left == null) {
                operand = right;
            }

            switch (current.op) {
                case "+" -> testValue -= operand;
                case "-" -> {
                    if (left == null) {
                        testValue += operand;
                    } else {
                        testValue -= testValue;
                    }
                }
                case "*" -> testValue /= operand;
                case "/" -> {
                    if (left == null) {
                        testValue *= operand;
                    } else {
                        testValue = operand / testValue;
                    }
                }
            }
            if (left == null && leftmonkey != null) {
                current = leftmonkey;
            } else {
                current = rightmonkey;
            }

            if (leftmonkey == null || rightmonkey == null) {
                break;
            }
        }
        return testValue;
    }

    record Monkey(String name, Long value, String op, String left, String right) {
        static Monkey parse(String line) {
            line = line.replaceAll(":", "");
            var split = line.split(" ");
            var name = split[0];
            if (split.length == 2) {
                return new Monkey(name, Long.parseLong(split[1]), null, null, null);
            } else {
                return new Monkey(name, null, split[2], split[1], split[3]);
            }
        }
    }


    Long getValueSafe(Monkey monkey, Map<String, Monkey> monkeyMap, boolean scan) {
        try {
            return getValue(monkey, monkeyMap);
        } catch (Exception e) {
            return null;
        }
    }


    Long getValue(Monkey monkey, Map<String, Monkey> monkeyMap) {
        if (monkey.value != null) return monkey.value;
        var left = monkeyMap.get(monkey.left);
        var right = monkeyMap.get(monkey.right);
        if (left.name.equals("humn") || right.name.equals("humn")) {
            return null;
        }
        var leftval = getValue(left, monkeyMap);
        var rightval = getValue(right, monkeyMap);


        return switch (monkey.op) {
            case "+" -> leftval + rightval;
            case "-" -> leftval - rightval;
            case "*" -> leftval * rightval;
            case "/" -> leftval / rightval;
            default -> throw new AocException("Unknown op: " + monkey.op);
        };
    }

}

