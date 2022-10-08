package aoc.y2015;

import aoc.AU;
import aoc.AocException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Day07 extends AU {
    private static final String DAY = "07";

    public static void main(String[] args) {

        var input = getInputAsStream("src/aoc/y2015/input/day" + DAY + "-1").toList();
        solveQ1(input);

        input = getInputAsStream("src/aoc/y2015/input/day" + DAY + "-2").toList();
        solveQ2(input);

    }

    static void solveQ2(List<String> input) {
        var map = new HashMap<String, Integer>();

        fillMap(input, map);
        println("Day " + DAY + " Q2: " + map.get("a"));
    }

    static void solveQ1(List<String> input) {
        var map = new HashMap<String, Integer>();

        fillMap(input, map);
        println("Day " + DAY + " Q1: " + map.get("a"));
    }

    private static void fillMap(List<String> input, HashMap<String, Integer> map) {
        List<Operation> operations = new ArrayList<>();
        for (var str : input) {
            var operation = new Operation(str, map);
            operations.add(operation);
        }

        while (!operations.isEmpty()) {
            var op = doOperation(operations, map);
            operations.remove(op);

        }
    }

    static Operation doOperation(List<Operation> operations, Map<String, Integer> map) {
        for (var operation : operations) {
            if (operation.operate(map)) {
                return operation;
            }
        }
        throw new AocException("No operation found");
    }
}


class Operation {

    public Operation(String str, Map<String, Integer> map) {
        var split = str.split(" ");
        if (split[0].equals("NOT")) {
            type = Type.NOT;
            second = split[1];
            result = split[3];
        } else if (split[1].equals("AND")) {
            type = Type.AND;
            first = split[0];
            second = split[2];
            result = split[4];

        } else if (split[1].equals("OR")) {
            type = Type.OR;
            first = split[0];
            second = split[2];
            result = split[4];
        } else if (split[1].equals("LSHIFT")) {
            type = Type.LSHIFT;
            first = split[0];
            second = split[2];
            result = split[4];
        } else if (split[1].equals("RSHIFT")) {
            type = Type.RSHIFT;
            first = split[0];
            second = split[2];
            result = split[4];
        } else if (split[1].equals("->")) {

            type = Type.ASSIGN;
            first = split[0];
            result = split[2];
        } else {
            throw new AocException("Unknown operation: " + str);
        }

        if (second != null && Character.isDigit(second.charAt(0))) {
            map.put(second, Integer.parseInt(second));
        }
        if (first != null && Character.isDigit(first.charAt(0))) {
            map.put(first, Integer.parseInt(first));
        }
    }

    String first;
    String second;

    String result;

    enum Type {
        AND, OR, NOT, LSHIFT, RSHIFT, ASSIGN
    }

    Type type;

    boolean operate(Map<String, Integer> map) {

        if (first != null && Character.isAlphabetic(first.charAt(0)) && !map.containsKey(first)) {
            return false;
        }
        if (second != null && Character.isAlphabetic(second.charAt(0)) && !map.containsKey(second)) {
            return false;
        }

        switch (type) {
            case AND:
                map.put(result, map.get(first) & map.get(second));
                break;
            case OR:
                map.put(result, map.get(first) | map.get(second));
                break;
            case NOT:
                map.put(result, ~map.get(second));
                break;
            case LSHIFT:
                map.put(result, map.get(first) << map.get(second));
                break;
            case RSHIFT:
                map.put(result, map.get(first) >> map.get(second));
                break;
            case ASSIGN:
                map.put(result, map.get(first));
                break;
        }
        return true;
    }


}

