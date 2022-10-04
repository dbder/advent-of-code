package aoc.y2015;

import aoc.AU;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class Day06 extends AU {
    private static String day = "06";

    public static void main(String[] args) {

        var input = getInputAsStream("src/aoc/y2015/input/day" + day).toList();

        solveQ1(input);
        solveQ2(input);

    }

    static void solveQ2(List<String> input) {
//        println("Day " + day +" Q2: " + "");
    }

    static void solveQ1(List<String> input) {
        var grid = new int[1000][1000];

        for (var str : input) {
            if (!str.startsWith("toggle")) {
                str = str.substring(str.indexOf(' ') + 1);
            }
            var split = str.split("[ ,]");

            String strat = split[0];
            int r1 = Integer.parseInt(split[1]);
            int c1 = Integer.parseInt(split[2]);

            int r2 = Integer.parseInt(split[4]);
            int c2 = Integer.parseInt(split[5]);

            for (int r = r1; r <= r2; r++) {
                for (int c = c1; c <= c2; c++) {
                    if (strat.equals("toggle")) {
                        grid[r][c]++;
                        grid[r][c]++;
                    } else if (strat.equals("on")) {
                        grid[r][c]++;
                    } else if (strat.equals("off")) {
                        grid[r][c]--;
                        if (grid[r][c] < 0) grid[r][c] = 0;
                    }
                }
            }

        }

        int count = 0;
        for (int r = 0; r < 1000; r++) {
            for (int c = 0; c < 1000; c++) {

                    count+= grid[r][c];

            }
        }

//        Day 06 Q1: 542387  too low

        println("Day " + day + " Q1: " + count);
    }
}

