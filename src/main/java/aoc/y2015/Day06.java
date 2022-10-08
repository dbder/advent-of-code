package aoc.y2015;

import aoc.AU;

import java.util.List;

public class Day06 extends AU {
    @Override
    public String getDay() {
        return "06";
    }
    public static void main(String[] args) {
        new Day06();
    }

    Day06() {
        var input = getInputLines();

        solveQ1(input);
        solveQ2(input);
    }


    void solveQ2(List<String> input) {
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
                count += grid[r][c];
            }
        }
        println("Day " + getDay() + " Q2: " + count);
    }

    void solveQ1(List<String> input) {
        var grid = new boolean[1000][1000];

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
                        grid[r][c] = !grid[r][c];
                    } else if (strat.equals("on")) {
                        grid[r][c] = true;
                    } else if (strat.equals("off")) {
                        grid[r][c] = false;
                    }
                }
            }

        }

        int count = 0;
        for (int r = 0; r < 1000; r++) {
            for (int c = 0; c < 1000; c++) {
                if (grid[r][c]) {
                    count++;
                }
            }
        }
        println("Day " + getDay() + " Q1: " + count);
    }
}

