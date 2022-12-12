package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;
import aoc.misc.V2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class Day12 extends AU {

    Day12(List<String> testData, boolean q1) {
        switch ((q1 ? "q1" : "q2") + "-" + (testData == null ? "real" : "test")) {
            case "q1-test" -> println("Test Q1: " + solveQ1(testData));
            case "q1-real" -> println("Real Q1: " + solveQ1(getInputLines()));
            case "q2-test" -> println("Test Q2: " + solveQ2(testData));
            case "q2-real" -> println("Real Q2: " + solveQ2(getInputLines()));
            default -> throw new AocException("Unknown case");
        }
    }

    public static void main(String[] args) {
        if (!testData1.get().isEmpty()) new Day12(testData1.get(), true);
        if (!testData2.get().isEmpty()) new Day12(testData2.get(), true);
        new Day12(null, true);
        if (true) return;
        if (!testData1.get().isEmpty()) new Day12(testData1.get(), false);
        if (!testData2.get().isEmpty()) new Day12(testData2.get(), false);
        new Day12(null, false);
    }

    static Supplier<List<String>> testData1 = () -> """
            Sabqponm
            abcryxxl
            accszExk
            acctuvwj
            abdefghi
            """.lines().collect(toList());

    static Supplier<List<String>> testData2 = () -> """
            """.lines().collect(toList());

    Object solveQ2(List<String> input) {
        var result = 0L;
        //DAY2 !!
        return result;
    }

    Object solveQ1(List<String> input) {
        var result = 0L;

        var grid = charGrid(input);
        int startr = 0;
        int startc = 0;
        var start = new V2(startc, startr);
        var end = new V2(startc, startr);

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 'S') {
                    start = new V2(r, c);
                    System.out.println("Found S at " + r + "," + c);
                }
                if (grid[r][c] == 'E') {
                    end = new V2(r, c);
                    System.out.println("Found E at " + r + "," + c);
                }
            }
        }
//        grid[end.row()][end.col()] = 'z';


        var visited = new HashSet<V2>();
        visited.add(end);

        var level = new ArrayList<V2>();
        level.add(end);
        int count = 0;
        int a = 0;
        while (!level.isEmpty()) {
            count++;
//            System.out.println("Level " + count + " has " + level.size() + " nodes");
            var nextlevel = new ArrayList<V2>();

            for (var v : level) {

                var height = grid[v.row()][v.col()];
                if (height == 'S') height = 'a';
                if (height == 'E') height = 'z';
                for (var dir : v.tps() ) {
                    if (dir.equals(end)) {
                        System.out.println("Found end at " + dir);
                    }
                    if (height == 'a') {
                        System.out.println("Found end at " + dir+ " in " + (count -a )+ " steps");
                        return count;
                    }
                    if (visited.contains(dir)) continue;
                    if (!dir.isIN(grid)) continue;
                    if (grid[dir.row()][dir.col()] == 'E' && height >= 'y') {
                        System.out.println("found end at " + dir + " in " + (count -a )+ " stteps");
                    }
                    var nh = grid[dir.row()][dir.col()];
                    if (nh == 'E') nh = 'z';
                    if (height > nh+1) continue;
                    visited.add(dir);
                    nextlevel.add(dir);

                }

//                printGrid(visited);
            }

            level = nextlevel;


        }
        System.out.println("count" + count);



        return result;
    }

    private static void printGrid(HashSet<V2> visited) {
        var minr = visited.stream().mapToInt(V2::row).min().orElse(0);
        var minc = visited.stream().mapToInt(V2::col).min().orElse(0);
        var maxr = visited.stream().mapToInt(V2::row).max().orElse(0);
        var maxc = visited.stream().mapToInt(V2::col).max().orElse(0);

        boolean[][] grid = new boolean[maxr + 1 - minr][maxc + 1 - minc];

        for (var v : visited) grid[v.row() - minr][v.col() - minc] = true;
        println(grid);
    }
}

