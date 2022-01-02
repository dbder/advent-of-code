package aoc.y2021;

import aoc.AU;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Day03 extends AU {

    public static void main(String[] args) throws IOException {
        var input = Files.lines(Path.of("src/aoc/y2021/input/day03"))
                .collect(Collectors.toList());

        solveQ1(input);

    }

    static void solveQ1(List<String> input) {
        var ints = new int[input.get(0).length()];

        for (String str : input) {
            for (int x = 0; x < str.length(); x++) {
                if (str.charAt(x) == '1') {
                    ints[x]++;
                }
            }
        }

        int gamma = 0;
        int epsilon = 0;
        int bar = input.size() / 2;
        for (int x = 0; x < ints.length; x++) {
            gamma *= 2;
            epsilon *= 2;
            if (ints[x] > bar) {
                gamma++;
            }
            if (ints[x] < bar) {
                epsilon++;
            }
        }

        print("Q1: " + (gamma * epsilon));
    }
}

