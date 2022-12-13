package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Day13 extends AU {

    Day13(List<String> testData, boolean q1) {
        switch ((q1 ? "q1" : "q2") + "-" + (testData == null ? "real" : "test")) {
            case "q1-test" -> println("Test Q1: " + solveQ1(testData));
            case "q1-real" -> println("Real Q1: " + solveQ1(getInputLines()));
            case "q2-test" -> println("Test Q2: " + solveQ2(testData));
            case "q2-real" -> println("Real Q2: " + solveQ2(getInputLines()));
            default -> throw new AocException("Unknown case");
        }
    }

    public static void main(String[] args) {
        if (!testData1.get().isEmpty()) new Day13(testData1.get(), true);
        new Day13(null, true);
        if (!testData1.get().isEmpty()) new Day13(testData1.get(), false);
        new Day13(null, false);
    }

    static Supplier<List<String>> testData1 = () -> """
            [1,1,3,1,1]
            [1,1,5,1,1]
                        
            [[1],[2,3,4]]
            [[1],4]
                        
            [9]
            [[8,7,6]]
                        
            [[4,4],4,4]
            [[4,4],4,4,4]
                        
            [7,7,7,7]
            [7,7,7]
                        
            []
            [3]
                        
            [[[]]]
            [[]]
                        
            [1,[2,[3,[4,[5,6,7]]]],8,9]
            [1,[2,[3,[4,[5,6,0]]]],8,9]
            """.lines().collect(toList());

    Object solveQ2(List<String> input) {
        var result = 1L;
        var addedItems = List.of("[[2]]", "[[6]]");
        input.addAll(addedItems);
        input.removeAll(List.of(""));
        input.sort((a, b) -> {
            var intlista = new IntList(null, null, null);
            build(a, intlista, 0);
            var intlistb = new IntList(null, null, null);
            build(b, intlistb, 0);
            return compare(intlistb, intlista);
        });

        for (int i = 1; i <= input.size(); i++) {
            if (addedItems.contains(input.get(i-1))) result *= i;
        }
        return result;
    }

    Object solveQ1(List<String> input) {
        var result = 0L;
        var pairs = chunk(input);
        for (int i = 0; i < pairs.size(); i++) {
            if (isInOrder(pairs.get(i).get(0), pairs.get(i).get(1)) == 1) {
                result += i + 1;
            }
        }
        return result;
    }


    int isInOrder(String a, String b) {
        var intlista = new IntList(null, null, null);
        build(a, intlista, 0);
        var intlistb = new IntList(null, null, null);
        build(b, intlistb, 0);
        return compare(intlista, intlistb);
    }


    int compare(IntList a, IntList b) {
        if (a.value != null && b.value != null) {
            return b.value.compareTo(a.value);
        }
        if (a.value != null ^ b.value != null) {
            if (a.value != null) {
                return compare(new IntList(List.of(a), null, null), b);
            }
            return compare(a, new IntList(List.of(b), null, null));
        }

        for (int i = 0; i < b.children.size(); i++) {
            if (a.children.size() <= i) return 1;
            int com = compare(a.children.get(i), b.children.get(i));
            if (com == 1) return 1;
            if (com == -1) return -1;
        }

        if (a.children.size() > b.children.size()) return -1;
        if (a.children.size() < b.children.size()) return 1;

        return 0;
    }

    void build(String str, IntList list, int index) {
        if (index >= str.length()) return;
        var c = str.charAt(index);
        if (c == '[') {
            var sub = new IntList(null, list, null);
            list.children.add(sub);
            build(str, sub, index + 1);
        } else if (c == ']') {
            build(str, list.parent, index + 1);
        } else if (c == ',') {
            build(str, list, index + 1);
        } else {
            int val = c - '0';
            var nextc = str.charAt(index + 1);
            if (nextc >= '0' && nextc <= '9') {
                val = val * 10 + nextc - '0';
                index++;
            }
            list.children.add(new IntList(null, list, val));
            build(str, list, index + 1);
        }
    }


    record IntList(List<IntList> children, IntList parent, Integer value) {
        IntList {
            if (children == null) children = new ArrayList<>();
        }

        @Override
        public String toString() {
            return value == null ? "[" + children.stream().map(IntList::toString).collect(joining(",")) + "]" : value.toString();
        }
    }

}
