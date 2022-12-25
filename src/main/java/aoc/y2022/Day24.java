package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;
import aoc.misc.V2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class Day24 extends AU {

    Day24(List<String> testData, boolean q1) {
        var startTime = System.currentTimeMillis();
        switch ((q1 ? "q1" : "q2") + "-" + (testData == null ? "real" : "test")) {
//            case "q1-test" -> println("Test Q1: " + solveQ1(testData));
//            case "q1-real" -> println("Real Q1: " + solveQ1(getInputLines()));
            case "q2-test" -> println("Test Q2: " + solveQ2(testData));
            case "q2-real" -> println("Real Q2: " + solveQ2(getInputLines()));
            default -> throw new AocException("Unknown case");
        }
        println("Time: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    public static void main(String[] args) {
//        if (!testData1.get().isEmpty()) new Day24(testData1.get(), true);
//        if (!testData2.get().isEmpty()) new Day24(testData2.get(), true);
//        new Day24(null, true);
//        if (true) return;
//        if (!testData1.get().isEmpty()) new Day24(testData1.get(), false);
//        if (!testData2.get().isEmpty()) new Day24(testData2.get(), false);
        new Day24(null, false);
    }

    static Supplier<List<String>> testData1 = () -> """
            #.#####
            #.....#
            #>....#
            #.....#
            #...v.#
            #.....#
            #####.#
            """.lines().collect(toList());

    static Supplier<List<String>> testData2 = () -> """
            #.######
            #>>.<^<#
            #.<..<<#
            #>v.><>#
            #<^v^^>#
            ######.#
            """.lines().collect(toList());

    char[][][] grids = new char[1][1][1];
    LinkedList<V2> goals = new LinkedList<V2>();


    Set<V2> blizzards;
    int count = 0;

    private record State(V2 v2, int minute){}
    Object solveQ2(List<String> input) {
        var result = 0L;
        buildGrids(input);
        goals.add(V2.of(rows, cols - 1));
        goals.add(V2.of(0, -1));
        goals.add(V2.of(rows, cols - 1));
        Set<V2> positions = new HashSet<>();
        positions.add(new V2(0, -1));

        while (!positions.isEmpty()) {
            count++;
            positions = next(positions);
            if (goals.isEmpty()) return count;

        }


        return result;
    }

    Set<V2> next(Set<V2> positions) {
        var nextPositions = new HashSet<V2>();

        int state = count % 600;
        for (var position : positions) {
            var possibilities = position.neighbors();
            possibilities.add(position);
            for (var pos : possibilities) {
                if (pos.equals(goals.peekFirst())) {
                    System.out.println("Found goal " + goals.get(0));
                    goals.removeFirst();
                    return Set.of(pos);
                }
                if (goals.size() == 1) {
                    nextPositions.add(new V2(0, -1));
                }
                if (!pos.isIN(grids[0])) continue;
                if (grids[state][pos.row()][pos.col()] == empty) nextPositions.add(pos);
            }

        }
        return nextPositions;
    }

    int rows = 25;
    int cols = 120;
    char empty  = grids[0][0][0];

    void buildGrids(List<String> input) {

        grids = new char[600][input.size() - 2][input.get(0).length() - 2];
        rows = input.size() - 2;
        cols = input.get(0).length() - 2;

        var tmpgrid = charGrid(input);
        for (int r = 1; r < tmpgrid.length - 1; r++) {
            for (int c = 1; c < tmpgrid[0].length - 1; c++) {
                if (tmpgrid[r][c] == '.') continue;
                grids[0][r - 1][c - 1] = tmpgrid[r][c];
            }
        }
        record Pair(char ch, V2 v2) {
        }
        List<Pair> blizzards = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                var ch = grids[0][r][c];
                if (ch == empty) continue;
                blizzards.add(new Pair(ch, new V2(r, c)));
            }
        }

        for (int i = 1; i < 600; i++) {
            var nextblizzards = new ArrayList<Pair>();
            for (var blizzard : blizzards) {
                nextblizzards.add(switch (blizzard.ch) {
                    case 'v' -> new Pair('v', new V2((blizzard.v2.row() + 1) % rows, blizzard.v2.col()));
                    case '^' -> new Pair('^', new V2((blizzard.v2.row() + rows - 1) % rows, blizzard.v2.col()));
                    case '<' -> new Pair('<', new V2(blizzard.v2.row(), (blizzard.v2.col() + cols - 1) % cols));
                    case '>' -> new Pair('>', new V2(blizzard.v2.row(), (blizzard.v2.col() + 1) % cols));
                    default -> throw new AocException("Unknown case");
                });
            }
            blizzards = nextblizzards;

            for (var blizzard : blizzards) {
                int r = blizzard.v2.row();
                int c = blizzard.v2.col();
                grids[i][r][c] = blizzard.ch;
            }
        }
    }
}

