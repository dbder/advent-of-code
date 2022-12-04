package aoc.y2018;

import aoc.AU;
import aoc.misc.V2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.toList;

public class Day06 extends AU {

    public static void main(String[] args) {
        new Day06();
    }

    List<String> testData1 = """
            1, 1
            1, 6
            8, 3
            3, 4
            5, 5
            8, 9
            """.lines().collect(toList());
    List<String> testData2 = """
            """.lines().collect(toList());

    Day06() {
//        if (!testData1.isEmpty()) println("Test Q1: " + solveQ1(testData1));
//        if (!testData2.isEmpty()) println("Test Q1: " + solveQ1(testData2));
//        println("Day " + getDay() + " Q1: " + solveQ1(getInputLines()));

//        if (!testData1.isEmpty()) println("Test Q2: " + solveQ2(testData1));
//        if (!testData2.isEmpty()) println("Test Q2: " + solveQ2(testData2));
        println("Day " + getDay() + " Q2: " + solveQ2(getInputLines()));
    }

    Object solveQ2(List<String> input) {
        var result = 0L;

        var list = input.stream().map(this::toInts).map(i -> V2.of(i[0] + 10000, i[1] + 10000)).toList();

//        var set = getdist1000(list.get(0));
//
//        System.out.println(set);

        for (int r = 1 ; r < 21000; r++) {
            if (r % 1000 == 0) System.out.println(r);
            for (int c = 1 ; c < 21000;c++) {
                var v = V2.of(r,c);
                if (list.stream().mapToInt(i -> v.manhattan(i)).sum() < 10000) {
                    result++;
                }

            }
        }
        return result;
    }

    Set<V2> getdist1000(V2 v) {

        var visited = new HashSet<V2>();
        var current = v;

        var level = new ArrayList<V2>();
        level.add(current);

        int depth = 0;
        while (depth < 1000) {
            depth++;
            var nextlevel = new ArrayList<V2>();

            for (var v2 : level) {

                for (var tp : v2.tps()) {
                    if (!visited.contains(tp)) {
                        visited.add(tp);
                        nextlevel.add(tp);
                    }
                }
            }
            level = nextlevel;
        }
        return visited;
    }


    Object solveQ1(List<String> input) {
        var result = 0L;
        var list = input.stream().map(this::toInts).map(i -> V2.of(i[0] + 400, i[1] + 400)).toList();

        int[][][] grid = new int[800][800][list.size()];

//        list.forEach(System.out::println);

        for (int i = 0; i < list.size(); i++) {
            var v = list.get(i);
            bfsFill(grid, i, v);
        }

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                var low = min(grid[r][c]);
                long count = Arrays.stream(grid[r][c]).filter(i -> i == low).count();
                if (count > 1) {
                    grid[r][c] = new int[]{-1};
                } else {
                    for (int i = 0; i < grid[r][c].length; i++) {
                        if (grid[r][c][i] == low) {
                            grid[r][c] = new int[]{i};
                        }
                    }
                }



            }

        }
        var infi = new HashSet<Integer>();
        for (int i = 0; i < grid.length; i++) {
            infi.add(grid[i][0][0]);
            infi.add(grid[i][grid[0].length-1][0]);
        }
        for (int i = 0; i < grid[0].length; i++) {
            infi.add(grid[0][i][0]);
            infi.add(grid[grid.length-1][i][0]);
        }
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {

                if (infi.contains(grid[r][c][0])) grid[r][c][0] = -1;
            }
        }

        var counts = new ArrayList<Integer>();
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if(grid[r][c][0] != -1) counts.add(grid[r][c][0]);
            }
        }

        var countmap = mapTCount(counts);

//        System.out.println(countmap);

//        print3d(grid);

        return max(countmap.values());

    }

    void bfsFill(int[][][] grid, int id, V2 v) {
        var visited = new HashSet<V2>();
        var current = v;
        var level = new ArrayList<V2>();
        level.add(current);

        int depth = 0;
        while (!level.isEmpty()) {
            depth++;
            var nextlevel = new ArrayList<V2>();

            for (var v2 : level) {
                for (var tp : v2.tps()) {
                    if (!visited.contains(tp) && tp.isIN(grid)) {
                        visited.add(tp);
                        grid[tp.row()][tp.col()][id] = depth;
                        nextlevel.add(tp);
                    }
                }
            }
            level = nextlevel;

        }


    }

}

