package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class Day20 extends AU {

    Day20(List<String> testData, boolean q1) {
        var startTime = System.currentTimeMillis();
        switch ((q1 ? "q1" : "q2") + "-" + (testData == null ? "real" : "test")) {
            case "q1-test" -> println("Test Q1: " + solveQ1(testData));
            case "q1-real" -> println("Real Q1: " + solveQ1(getInputLines()));
            case "q2-test" -> println("Test Q2: " + solveQ2(testData));
            case "q2-real" -> println("Real Q2: " + solveQ2(getInputLines()));
            default -> throw new AocException("Unknown case");
        }
        println("Time: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    public static void main(String[] args) {
        if (!testData1.get().isEmpty()) new Day20(testData1.get(), true);
        new Day20(null, true);
        if (true) return;
        if (!testData1.get().isEmpty()) new Day20(testData1.get(), false);
        new Day20(null, false);
    }

    static Supplier<List<String>> testData1 = () -> """
            1
            2
            -3
            3
            -2
            0
            4
            """.lines().collect(toList());

    Object solveQ2(List<String> input) {
        var result = 0L;
        //DAY2 !!
        return result;
    }

    private class Node {
        Node prev;
        Node next;
        long value;
        BigInteger valueB;
        long origValue;
        boolean head = false;

        @Override
        public String toString() {
            return "Node{" +
                    "prev=" + (prev == null ? "null" : prev.value) +
                    ", next=" + (next == null ? "null" : next.value) +
                    ", value=" + value +
                    '}';
        }
    }

    Object solveQ1(List<String> input) {
        var result = 0L;
        Node head = null;
        Node current = null;
        int size = input.size();
        int keycr = 811589153 % (size - 1);
        for (int i = 0; i < input.size(); i++) {
            var line = input.get(i);
            var value = Integer.parseInt(line);

            var node = new Node();
            node.origValue = value;
            node.value = value * keycr;
            node.value %= (size - 1);
            node.valueB = BigInteger.valueOf(value).multiply(BigInteger.valueOf(811589153));
            node.prev = current;
            if (i != 0) {
                current.next = node;
            } else {
                head = node;
            }
            current = node;
            if (i == input.size() - 1) {
                current.next = head;
                head.prev = current;
            }
        }

        List<Node> nodes = new ArrayList<>();
        Node next = head;
        while (next != null) {
            nodes.add(next);
            next = next.next;
            if (next == head) break;
        }

        System.out.println(nodes.stream().map(n -> n.valueB.toString()).collect(toList()));
        System.out.println("---");

        for (int i = 0; i < 10; i++) {
            for (var node : nodes) {
                swap(node);
            }
            Node zero = head;
            while (zero.value != 0) {
                zero = zero.next;
            }
            System.out.print(zero.valueB + " ");
            zero = zero.next;
            while (zero.value != 0) {
                System.out.print(zero.valueB + " ");
                zero = zero.next;
            }
//            System.out.println(nodes.stream().map(n -> n.valueB.toString()).collect(toList()));
//            System.out.println("---");
        }


        next = head;
        while (next != null) {
            nodes.add(next);
            next = next.next;
            if (next == head) break;
        }

        Node zero = head;
        while (zero.value != 0) {
            zero = zero.next;
        }

        List<Long> counts = new ArrayList<>();
        List<BigInteger> countsB = new ArrayList<>();
        List<Long> originalValues = new ArrayList<>();

        for (int i = 1; i <= 3000; i++) {
            zero = zero.next;
            if (i == 1000 || i == 2000 || i == 3000) {
                System.out.println(zero);
                counts.add(zero.value);
                countsB.add(zero.valueB);
                originalValues.add(zero.origValue);
            }
        }

        System.out.println("-------------------");
        System.out.println("counts         = " + counts);
        System.out.println("countsB        = " + countsB);
        System.out.println("originalValues = " + originalValues);

        return countsB.stream().reduce(BigInteger::add).get();
//        return counts.stream().mapToLong(i -> i).sum();
    }

    void swap(Node node) {
        var val = node.value;
        if (val == 0) return;
        Node targetbefore = node.prev;
        Node targetafter = node.next;
        targetbefore.next = targetafter;
        targetafter.prev = targetbefore;
        if (val < 0) {
            while (val != 0) {
                targetbefore = targetbefore.prev;
                targetafter = targetafter.prev;
                val++;
            }
        } else {
            while (val != 0) {
                targetbefore = targetbefore.next;
                targetafter = targetafter.next;
                val--;
            }
        }
        targetbefore.next = node;
        node.prev = targetbefore;
        targetafter.prev = node;
        node.next = targetafter;
    }

}

