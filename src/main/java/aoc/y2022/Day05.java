package aoc.y2022;

import aoc.AU;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Day05 extends AU {

    public static void main(String[] args) {
        new Day05();
    }

    List<String> testData1 = """
            """.lines().collect(toList());
    List<String> testData2 = """
            """.lines().collect(toList());

    Day05() {
        if (!testData1.isEmpty()) println("Test Q1: " + solveQ1(testData1));
        if (!testData2.isEmpty()) println("Test Q1: " + solveQ1(testData2));
        println("Day " + getDay() + " Q1: " + solveQ1(getInputLines()));

        if (!testData1.isEmpty()) println("Test Q2: " + solveQ2(testData1));
        if (!testData2.isEmpty()) println("Test Q2: " + solveQ2(testData2));
        println("Day " + getDay() + " Q2: " + solveQ2(getInputLines()));
    }

    Object solveQ2(List<String> input) {
        var result = 0L;

        return result;
    }

    Object solveQ1(List<String> input) {
        var result = 0L;

        var list = new ArrayList<LinkedList<String>>();
        list.add(new LinkedList<>(Arrays.asList("")));
        list.add(new LinkedList<>(Arrays.asList("P", "F", "M", "Q", "W", "G", "R", "T").stream().collect(toList())));
        list.add(new LinkedList<>(Arrays.asList("H", "F", "R").stream().collect(toList())));
        list.add(new LinkedList<>(Arrays.asList("P", "Z", "R", "V", "G", "H", "S", "D").stream().collect(toList())));
        list.add(new LinkedList<>(Arrays.asList("Q", "H", "P", "B", "F", "W", "G").stream().collect(toList())));
        list.add(new LinkedList<>(Arrays.asList("P", "S", "M", "J", "H").stream().collect(toList())));
        list.add(new LinkedList<>(Arrays.asList("M", "Z", "T", "H", "S", "R", "P", "L").stream().collect(toList())));
        list.add(new LinkedList<>(Arrays.asList("P", "T", "H", "N", "M", "L").stream().collect(toList())));
        list.add(new LinkedList<>(Arrays.asList("F", "D", "Q", "R").stream().collect(toList())));
        list.add(new LinkedList<>(Arrays.asList("D", "S", "C", "N", "L", "P", "H").stream().collect(toList())));

        for (int x = 0; x < input.size(); x++) {
            var ints = toInts(input.get(x));
            int amount = ints[0];
            int from = ints[1];
            int to = ints[2];

            var buffer = new LinkedList<String>();
            for (int i = 0; i < amount; i++) {
                buffer.add(list.get(from).removeLast());
            }

            for (int i = 0; i < amount; i++) {
                list.get(to).addLast(buffer.removeLast());
            }

            list.forEach(System.out::println);


        }

        System.out.println("");

        list.forEach(System.out::println);
        list.forEach(b -> System.out.print(b.removeLast()));
        System.out.println("");
        return result;
    }

}

