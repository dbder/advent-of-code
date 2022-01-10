package aoc.y2021;

import aoc.AU;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day13 extends AU {

    public static void main(String[] args) {
        var input = getInputAsStream("src/aoc/y2021/input/day13")
                .toList();

        solveQ1(input);
        solveQ2(input);

    }

    /**
     * Finish folding the transparent paper according to the instructions. The manual says the code is always eight capital letters.
     *
     * What code do you use to activate the infrared thermal imaging camera system?
     */
    static void solveQ2(List<String> input) {

        var points = input.stream()
                .takeWhile(NOT_EMPTY)
                .map(s -> s.split(","))
                .map(a -> new P(Integer.parseInt(a[0]), Integer.parseInt(a[1])))
                .collect(Collectors.toSet());

        input.stream()
                .dropWhile(NOT_EMPTY)
                .skip(1)
                .forEach(i -> {
                    if (i.charAt(11) == 'x') {
                        foldX(points, Integer.parseInt(i.substring(13)));
                    } else {
                        foldY(points, Integer.parseInt(i.substring(13)));
                    }
                });

        println("Day 13 Q1: :");
        draw(points);
    }

    /**
     * How many dots are visible after completing just the first fold instruction on your transparent paper?
     */
    static void solveQ1(List<String> input) {

        var points = input.stream()
                .takeWhile(s -> !s.isEmpty())
                .map(s -> s.split(","))
                .map(a -> new P(Integer.parseInt(a[0]), Integer.parseInt(a[1])))
                .collect(Collectors.toSet());

        foldX(points, 655);

        println("Day 13 Q1: " + points.size());
    }

    static void foldY(Set<P> points, int row) {
        Set<P> in = new HashSet<>();
        Set<P> out = new HashSet<>();
        for (P p : points) {
            if (p.y <= row) continue;
            in.add(new P(p.x, (row - (p.y - row))));
            out.add(p);
        }
        points.removeAll(out);
        for (P p : in) points.add(p);
    }

    static void foldX(Set<P> points, int col) {
        Set<P> in = new HashSet<>();
        Set<P> out = new HashSet<>();
        for (P p : points) {
            if (p.x <= col) {
                continue;
            }
            in.add(new P((col - (p.x - col)), p.y));
            out.add(p);
        }
        points.removeAll(out);
        for (P p : in) points.add(p);
    }

    static void draw(Set<P> points) {
        int col = points.stream().mapToInt(i -> i.x).max().orElse(0);
        int row = points.stream().mapToInt(i -> i.y).max().orElse(0);
        char[][] grid = new char[row + 1][col + 1];
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) grid[r][c] = '.';
        }

        for (P point : points) grid[point.y][point.x] = '#';
        for (char[] arr : grid) {
            for (char c : arr) System.out.print(c);
            System.out.println();

        }
    }

    record P(int x, int y) {
    }
}

