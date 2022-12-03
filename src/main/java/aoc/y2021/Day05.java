package aoc.y2021;

import aoc.AU;

import java.util.List;

public class Day05 extends AU {

    @Override
    protected String getDay() {
        return "05";
    }

    public static void main(String[] args) {

            new Day05();

    }

    Day05() {
        var input = getInputStream()
                .map(Line::parse)
                .toList();

        solveQ1(input);
        solveQ2(input);

    }

    void solveQ2(List<Line> input) {
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

        println("Day5 Q2: " + countOverlapping(grid));
    }


    void solveQ1(List<Line> input) {
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


        println("Day5 Q1: " + countOverlapping(grid));
    }

    int countOverlapping(int[][] grid) {
        int count = 0;
        for (var row : grid) {
            for (var i : row) {
                if (i > 1) {
                    count++;
                }
            }
        }
        return count;
    }

    private record Line(int x1, int y1, int x2, int y2) {
        static Line parse(String string) {
            var parts = string.split(",| -> ");
            return new Line(
                    toInt(parts[0]),
                    toInt(parts[1]),
                    toInt(parts[2]),
                    toInt(parts[3])
            );
        }


        boolean isStraight() {
            return x1 == x2 || y1 == y2;
        }

        int minX() {
            return Math.min(x1, x2);
        }

        int maxX() {
            return Math.max(x1, x2);
        }

        int minY() {
            return Math.min(y1, y2);
        }

        int maxY() {
            return Math.max(y1, y2);
        }
    }
}

