package aoc.y2021;

import aoc.AU;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day02 extends AU {

    public static void main(String[] args) throws IOException {
        var input = Files.lines(Path.of("src/aoc/y2021/input/day02"))
                .toList();

        solveQ1(input);
        solveQ2(input);

    }

    private static void solveQ1(List<String> input) {
        int hor = 0;
        int depth = 0;

        for (String str : input) {
            int val = str.charAt(str.length() - 1) - '0';
            switch (str.charAt(0)) {
                case 'f' -> hor += val;
                case 'd' -> depth += val;
                case 'u' -> depth -= val;
            }
        }

        print("Q1: " + (hor * depth));
    }

    private static void solveQ2(List<String> input) {
        int hor = 0;
        int depth = 0;
        int aim = 0;

        for (String str : input) {
            int val = str.charAt(str.length() - 1) - '0';
            switch (str.charAt(0)) {
                case 'f' -> {
                    hor += val;
                    depth += (aim * val);
                }
                case 'd' -> aim += val;
                case 'u' -> aim -= val;
            }
        }

        print("Q2: " + (hor * depth));
    }
}

