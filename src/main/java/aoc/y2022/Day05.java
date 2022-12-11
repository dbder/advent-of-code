package aoc.y2022;

import aoc.AU;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day05 extends AU {

    public static void main(String[] args) {
        new Day05();
    }

    Day05() {
        println("Day " + getDay() + " Q1: " + solveQ1(getInputLines()));
        println("Day " + getDay() + " Q2: " + solveQ2(getInputLines()));
    }

    Object solveQ2(List<String> input) {
        var lists = chunk(input);
        var stacks = getStacks(lists.get(0));
        doInstructions(stacks, lists.get(1), (ch, stack) -> stack.addFirst(ch));
        return stacks.stream().map(s -> "" + s.peekLast()).collect(Collectors.joining());
    }

    Object solveQ1(List<String> input) {
        var lists = chunk(input);
        var stacks = getStacks(lists.get(0));
        doInstructions(stacks, lists.get(1), (ch, stack) -> stack.addLast(ch));
        return stacks.stream().map(s -> "" + s.peekLast()).collect(Collectors.joining());
    }


    public void doInstructions(List<LinkedList<Character>> stacks, List<String> instructions, BiConsumer<Character, LinkedList<Character>> bufferFillStrategy) {
        for (var line : instructions) {
            var ints = toInts(line);
            int amount = ints[0];
            int from = ints[1] - 1;
            int to = ints[2] - 1;

            var buffer = new LinkedList<Character>();

            IntStream.range(0, amount)
                    .mapToObj(i -> stacks.get(from))
                    .map(LinkedList::removeLast)
                    .forEach(ch -> bufferFillStrategy.accept(ch, buffer));

            stacks.get(to).addAll(buffer);

        }
    }

    List<LinkedList<Character>> getStacks(List<String> input) {
        int index = 0;
        int numberOfStacks = 9;

        var stacks = IntStream
                .range(0, numberOfStacks)
                .mapToObj(i -> new LinkedList<Character>())
                .toList();

        while (input.get(index).charAt(0) != ' ') {
            var line = input.get(index);
            for (int i = 0; i < 9; i++) {
                var ch = line.charAt(1 + (i * 4));
                if (ch != ' ') stacks.get(i).addFirst(ch);
            }
            index++;
        }
        return stacks;
    }
}

