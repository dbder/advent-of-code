package aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day extends AU {
    public static void main(String[] args) throws IOException {
        var input = Files.lines(Path.of("src/aoc/y2021/input/day01"))
                .mapToInt(Integer::parseInt)
                .toArray();

        solveQ1(input);
//        solveQ2(input);

    }

    static void solveQ1(Object input) {
        print("Q1: " + "");
    }
}

