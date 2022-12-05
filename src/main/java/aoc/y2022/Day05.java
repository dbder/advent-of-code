package aoc.y2022;

import aoc.AU;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class Day05 extends AU {

    public static void main(String[] args) {
        new Day05();
    }

    Day05() {
        println("Day " + getDay() + " Q1: " + solveQ1(getInputLines()));
        println("Day " + getDay() + " Q2: " + solveQ2(getInputLines()));
    }

    Object solveQ2(List<String> input) {

        var lists = getBase();

        for (String s : chunk(input).get(1)) {
            var ints = toInts(s);
            int amount = ints[0];
            int from = ints[1];
            int to = ints[2];

            var buffer = new LinkedList<String>();
            for (int i = 0; i < amount; i++) {
                buffer.add(lists.get(from).removeLast());
            }

            for (int i = 0; i < amount; i++) {
                lists.get(to).addLast(buffer.removeLast());
            }

        }

        return lists.stream()
                .map(LinkedList::removeLast)
                .collect(Collectors.joining());
    }

    Object solveQ1(List<String> input) {
        ArrayList<LinkedList<String>> lists = getBase();
        for (String s : chunk(input).get(1)) {
            var ints = toInts(s);
            int amount = ints[0];
            int from = ints[1];
            int to = ints[2];

            var buffer = new LinkedList<String>();
            for (int i = 0; i < amount; i++) {
                buffer.add(lists.get(from).removeLast());
            }

            for (int i = 0; i < amount; i++) {
                lists.get(to).addLast(buffer.removeFirst());
            }
        }
        return lists.stream()
                .map(LinkedList::removeLast)
                .collect(Collectors.joining());
    }

    private static ArrayList<LinkedList<String>> getBase() {
        var lists = new ArrayList<LinkedList<String>>();
        lists.add(new LinkedList<>(asList("")));
        lists.add(new LinkedList<>(asList("P", "F", "M", "Q", "W", "G", "R", "T")));
        lists.add(new LinkedList<>(asList("H", "F", "R")));
        lists.add(new LinkedList<>(asList("P", "Z", "R", "V", "G", "H", "S", "D")));
        lists.add(new LinkedList<>(asList("Q", "H", "P", "B", "F", "W", "G")));
        lists.add(new LinkedList<>(asList("P", "S", "M", "J", "H")));
        lists.add(new LinkedList<>(asList("M", "Z", "T", "H", "S", "R", "P", "L")));
        lists.add(new LinkedList<>(asList("P", "T", "H", "N", "M", "L")));
        lists.add(new LinkedList<>(asList("F", "D", "Q", "R")));
        lists.add(new LinkedList<>(asList("D", "S", "C", "N", "L", "P", "H")));
        return lists;
    }

}

