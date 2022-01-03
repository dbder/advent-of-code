package aoc.y2021;

import aoc.AU;

import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class Day05 extends AU {

    public static void main(String[] args) {
        var input = getInputAsStream("src/aoc/y2021/input/day05")
                .map(Line::parse)
                .toList();

        solveQ1(input);
        solveQ2(input);

    }

    static void solveQ2(List<Line> input) {
        int[][] grid = new int[1000][1000];

        for (Line line : input) {
            int x = line.x1;
            int y = line.y1;
            grid[y][x]++;
            while (x != line.x2 || y != line.y2) {
                if (x < line.x2) {
                    x++;
                } else if (x > line.x2) {
                    x--;
                }
                if (y < line.y2) {
                    y++;
                } else if (y > line.y2) {
                    y--;
                }
                grid[y][x]++;
            }
        }

        int count = 0;
        for (var row : grid) {
            for (var i : row) {
                if (i > 1) {
                    count++;
                }
            }
        }

        print("Day5 Q2: " + count);
    }


    static void solveQ1(List<Line> input) {
        int[][] grid = new int[1000][1000];

        for (Line line : input) {
            if (line.isStraight()) {
                for (int x = line.minX(); x <= line.maxX(); x++) {
                    for (int y = line.minY(); y <= line.maxY(); y++) {
                        grid[y][x]++;
                    }
                }
            }
        }

        int count = 0;
        for (var row : grid) {
            for (var i : row) {
                if (i > 1) {
                    count++;
                }
            }
        }

        print("Day5 Q1: " + count);
    }


    private record Line(int x1, int y1, int x2, int y2) {
        static Line parse(String string) {
            var parts = string.split(",| -> ");
            return new Line(
                    parseInt(parts[0]),
                    parseInt(parts[1]),
                    parseInt(parts[2]),
                    parseInt(parts[3])
            );
        }


        boolean isStraight() {
            return x1 == x2 || y1 == y2;
        }

        int minX() {
            return min(x1, x2);
        }

        int maxX() {
            return max(x1, x2);
        }

        int minY() {
            return min(y1, y2);
        }

        int maxY() {
            return max(y1, y2);
        }
    }
}

