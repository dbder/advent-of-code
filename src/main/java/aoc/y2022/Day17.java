package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;
import aoc.misc.V2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class Day17 extends AU {

    Day17(List<String> testData, boolean q1) {
        switch ((q1 ? "q1" : "q2") + "-" + (testData == null ? "real" : "test")) {
            case "q1-test" -> println("Test Q1: " + solveQ1(testData));
            case "q1-real" -> println("Real Q1: " + solveQ1(getInputLines()));
            case "q2-test" -> println("Test Q2: " + solveQ2(testData));
            case "q2-real" -> println("Real Q2: " + solveQ2(getInputLines()));
            default -> throw new AocException("Unknown case");
        }
    }

    int[][][] rocks;

    {
        rocks = new int[][][]{
                {
                        {1, 1, 1, 1}
                },
                {
                        {0, 1, 0},
                        {1, 1, 1},
                        {0, 1, 0}
                },
                {
                        {1, 1, 1},
                        {0, 0, 1},
                        {0, 0, 1}
                },
                {
                        {1},
                        {1},
                        {1},
                        {1}
                },
                {
                        {1, 1},
                        {1, 1}
                }

        };
    }

    public static void main(String[] args) {
//        if (!testData1.get().isEmpty()) new Day17(testData1.get(), true);
        if (!testData2.get().isEmpty()) new Day17(testData2.get(), true);
        new Day17(null, true);
        if (true) return;
        if (!testData1.get().isEmpty()) new Day17(testData1.get(), false);
        if (!testData2.get().isEmpty()) new Day17(testData2.get(), false);
        new Day17(null, false);
    }

    static Supplier<List<String>> testData1 = () -> """
            >>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>
            """.lines().collect(toList());

    static Supplier<List<String>> testData2 = () -> """
            """.lines().collect(toList());

    Object solveQ2(List<String> input) {
        var result = 0L;
        //DAY2 !!
        return result;
    }

    Set<V2> bounds = new HashSet<>();
    Set<V2> segments = new HashSet<>();

    Set<V2> rock;
    Set<V2> tmp;

    // 1514285714261
    // 1514285714288
    // 6714285714174
    // 1342857142863

    /**
     * 7 7
     * 17 10
     * 23 6
     * 36 13
     * 42 6
     * 51 9
     * 60 9
     * 66 6
     * 72 6
     * 78 6
     * 89 11
     *
     *
     * 2651 2653
     * 5294 2643
     * 7937 2643
     * 10580 2643
     *
     * 1546518431850 too low
     * 1552831289121 too low
     * 1564705882901 too high
     * 1564705882327
     *
     */
    Object solveQ1(List<String> input) {

        initBounds();
        rock = getNextRock();
//
//        var instructions = input.get(0);
//        List<Integer> diffs = new ArrayList<>();
//        int last = 0;
//        for (int c = 0; c < 20; c++) {
//            doMoves(instructions);
//            System.out.println((getSegmentTop() + 1) + " " + ((getSegmentTop() + 1) - last));
//            last = getSegmentTop() + 1;
//            diffs.add((getSegmentTop() + 1) - last);
//        }
//        System.out.println(diffs);

        var l = 1000000000000L;
        l -= 1713;
        var res = 0L;
        res += 2651L;  // first 1713

        res += (l / 1700) * 2660;
        l = l % 1700;

        res += 296;
        System.out.println("l = " + l);
        System.out.println("result = " + res);


//        if (true) return 0;
//        var result = 0L;


        return res;
    }


    void doMoves(String instructions) {

        int[] counts = new int[2025];

        int start = getSegmentTop()+1;
        int count = 0;

        var last = count;
        for (var c : instructions.toCharArray()) {
            tmp = switch (c) {
                case '<' -> shiftLeft(rock);
                case '>' -> shiftRight(rock);
                case '^' -> shiftUp(rock);
                case 'v' -> shiftDown(rock);
                default -> throw new AocException("Invalid direction: " + c);
            };
            if (!hitWall(tmp) && !hitOther(tmp)) {
                rock = tmp;
            }
            tmp = shiftDown(rock);
            if (hitWall(tmp) || hitOther(tmp)) {
                count++;
                counts[count] = getSegmentTop() + 1;
                segments.addAll(rock);
                rock = getNextRock();

                if (count == 187) {
                    System.out.println("553: " + ((getSegmentTop()+1) - start));
                }
//                System.out.println("count = " + count);
//                System.out.println(getSegmentTop() + 1);
            } else {
                rock = tmp;
            }
        }
        System.out.println("Blocks fell: " + count);
    }


    void printV2(Set<V2> set) {

        var tmpset = set.stream().collect(Collectors.toSet());
        tmpset.addAll(rock);
        tmpset.addAll(bounds);
        if (tmpset.isEmpty()) return;
        var minX = tmpset.stream().mapToInt(V2::col).min().orElseThrow();
        var maxX = tmpset.stream().mapToInt(V2::col).max().orElseThrow();
        var minY = tmpset.stream().mapToInt(V2::row).min().orElseThrow();
        var maxY = tmpset.stream().mapToInt(V2::row).max().orElseThrow();

        for (int y = maxY; y >= minY; y--) {
            for (int x = minX; x <= maxX; x++) {
                if (tmpset.contains(new V2(y, x))) {
                    print("#");
                } else {
                    print(".");
                }
            }
            println("");
        }
        System.out.println("=====================================");
    }

    int getSegmentTop() {
        return segments.stream().mapToInt(V2::row).max().orElse(-1);
    }

    int blocknr = 0;

    Set<V2> getNextRock() {
        int top = getSegmentTop();
        var result = new HashSet<V2>();
//        System.out.println("Block: " + blocknr);
        var rock = rocks[blocknr];
        blocknr++;
        if (blocknr == 5) blocknr = 0;
        for (int r = 0; r < rock.length; r++) {
            for (int c = 0; c < rock[r].length; c++) {
                if (rock[r][c] == 1) {
                    result.add(new V2(r + top + 4, c + 2));
                }
            }
        }
        return result;
    }

    boolean hitWall(Set<V2> set) {
        for (var v : set) {
            if (v.col() < 0 || v.col() > 6 || v.row() < 0) return true;
        }
        return false;
    }

    boolean hitOther(Set<V2> set) {
        boolean result = false;
        for (var v : set) {
            if (segments.contains(v)) result = true;
        }
        return result;
    }

    Set<V2> shiftLeft(Set<V2> set) {
        return set.stream().map(v -> v.add(0, -1)).collect(toSet());
    }

    Set<V2> shiftRight(Set<V2> set) {
        return set.stream().map(v -> v.add(0, 1)).collect(toSet());
    }

    Set<V2> shiftUp(Set<V2> set) {
        return set.stream().map(v -> v.add(1, 0)).collect(toSet());

    }

    Set<V2> shiftDown(Set<V2> set) {
        return set.stream().map(V2::down).collect(toSet());
    }


    boolean overlaps(Set<V2> set, Set<V2> other) {
        return set.stream().anyMatch(other::contains);
    }


    void initBounds() {
        for (int c = -1; c < 7; c++) {
            bounds.add(new V2(-1, c));
        }
        for (int r = -1; r < 20; r++) {
            bounds.add(new V2(r, -1));
            bounds.add(new V2(r, 7));
        }
    }


}

