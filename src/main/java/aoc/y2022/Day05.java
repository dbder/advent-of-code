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
        var stacks = doInstructions(input, (ch, stack) -> stack.addFirst(ch));
        return stacks.stream().map(s -> "" + s.peekLast()).collect(Collectors.joining());
    }

    Object solveQ1(List<String> input) {
        var stacks = doInstructions(input, (ch, stack) -> stack.addLast(ch));
        return stacks.stream().map(s -> "" + s.peekLast()).collect(Collectors.joining());
    }

    public List<LinkedList<Character>> doInstructions(List<String> input, BiConsumer<Character, LinkedList<Character>> bufferFillStrategy) {
        var lists = chunk(input);
        var stacks = getStacks(lists.get(0));
        lists.get(1).stream()
                .map(this::toInstruction)
                .forEach(instr -> process(stacks, instr, bufferFillStrategy));
        return stacks;
    }

    private void process(List<LinkedList<Character>> stacks, Instruction instruction, BiConsumer<Character, LinkedList<Character>> bufferFillStrategy) {
        var buffer = new LinkedList<Character>();

        IntStream.range(0, instruction.amount)
                .mapToObj(i -> stacks.get(instruction.from))
                .map(LinkedList::removeLast)
                .forEach(ch -> bufferFillStrategy.accept(ch, buffer));

        stacks.get(instruction.to).addAll(buffer);
    }

    private Instruction toInstruction(String str) {
        var ints = toInts(str);
        return new Instruction(ints[0], ints[1] - 1, ints[2] - 1);
    }

    record Instruction(int amount, int from, int to) {
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

