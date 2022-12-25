package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;
import aoc.misc.V2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class Day23 extends AU {

    Day23(List<String> testData, boolean q1) {
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
//        if (!testData1.get().isEmpty()) new Day23(testData1.get(), true);
//        if (!testData2.get().isEmpty()) new Day23(testData2.get(), true);
//        new Day23(null, true);
//        if (true) return;
        if (!testData1.get().isEmpty()) new Day23(testData1.get(), false);
        new Day23(null, false);
    }

    static Supplier<List<String>> testData1 = () -> """
            ..............
            ..............
            .......#......
            .....###.#....
            ...#...#.#....
            ....#...##....
            ...#.###......
            ...##.#.##....
            ....#..#......
            ..............
            ..............
            ..............
            """.lines().collect(toList());

    static Supplier<List<String>> testData2 = () -> """
            .....
            ..##.
            ..#..
            .....
            ..##.
            .....
            """.lines().collect(toList());


    Set<V2> set = new HashSet<>();


    Object solveQ2(List<String> input) {
        var result = 0L;

        var grid = charGrid(input);

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '#') {
                    set.add(new V2(r, c));
                }
            }
        }


        var isDir = new LinkedList<Predicate<V2>>();
        isDir.addAll(Arrays.asList(this::isN, this::isS, this::isW, this::isE));

        var newElf = new LinkedList<Function<V2, V2>>();
        newElf.addAll(Arrays.asList(V2::down, V2::up, V2::left, V2::right));

        for (int i = 1; i < 10000; i++) {
            var newSet = new HashSet<V2>();
            var finalSet = new HashSet<V2>();
            var dups = new HashSet<V2>();

            for (var elf : set) {
                if (elf.neighbors8().stream().filter(set::contains).count() == 0) {
                    finalSet.add(elf);
                } else if (isDir.get(0).test(elf)) {
                    var ne = newElf.get(0).apply(elf);
                    if (newSet.contains(ne)) dups.add(ne);
                    newSet.add(ne);
                } else if (isDir.get(1).test(elf)) {
                    var ne = newElf.get(1).apply(elf);
                    if (newSet.contains(ne)) dups.add(ne);
                    newSet.add(ne);
                } else if (isDir.get(2).test(elf)) {
                    var ne = newElf.get(2).apply(elf);
                    if (newSet.contains(ne)) dups.add(ne);
                    newSet.add(ne);
                } else if (isDir.get(3).test(elf)) {
                    var ne = newElf.get(3).apply(elf);
                    if (newSet.contains(ne)) dups.add(ne);
                    newSet.add(ne);
                } else {
                    newSet.add(elf);
                }
            }
            boolean changed = false;
            for (var elf : set) {
                if (elf.neighbors8().stream().noneMatch(set::contains)) {
                    finalSet.add(elf);
                } else if (isDir.get(0).test(elf)) {
                    var tmp = newElf.get(0).apply(elf);
                    if (dups.contains(tmp)) {
                        finalSet.add(elf);
                    } else {
                        changed = true;
                        finalSet.add(tmp);
                    }
                } else if (isDir.get(1).test(elf)) {
                    var tmp = newElf.get(1).apply(elf);
                    if (dups.contains(tmp)) {
                        finalSet.add(elf);
                    } else {
                        changed = true;
                        finalSet.add(tmp);
                    }
                } else if (isDir.get(2).test(elf)) {
                    var tmp = newElf.get(2).apply(elf);
                    if (dups.contains(tmp)) {
                        finalSet.add(elf);
                    } else {
                        changed = true;
                        finalSet.add(tmp);
                    }
                } else if (isDir.get(3).test(elf)) {
                    var tmp = newElf.get(3).apply(elf);
                    if (dups.contains(tmp)) {
                        finalSet.add(elf);
                    } else {
                        changed = true;
                        finalSet.add(tmp);
                    }
                } else {
                    finalSet.add(elf);
                }
            }

            if (!changed) {
                return i;
            }

            set = finalSet;
            isDir.addLast(isDir.removeFirst());
            newElf.addLast(newElf.removeFirst());

        }


        var minr = set.stream().mapToInt(V2::row).min().orElse(0);
        var minc = set.stream().mapToInt(V2::col).min().orElse(0);
        var maxr = set.stream().mapToInt(V2::row).max().orElse(0);
        var maxc = set.stream().mapToInt(V2::col).max().orElse(0);


        result = (1+maxr - minr) * (1+maxc - minc) - set.size();

        return result;
    }


    boolean isN(V2 elf) {
        var tmp = elf.down();
        if (set.contains(tmp)) return false;
        if (set.contains(tmp.left())) return false;
        if (set.contains(tmp.right())) return false;
        return true;
    }

    boolean isE(V2 elf) {
        var tmp = elf.right();
        if (set.contains(tmp)) return false;
        if (set.contains(tmp.up())) return false;
        if (set.contains(tmp.down())) return false;
        return true;
    }

    boolean isS(V2 elf) {
        var tmp = elf.up();
        if (set.contains(tmp)) return false;
        if (set.contains(tmp.left())) return false;
        if (set.contains(tmp.right())) return false;
        return true;
    }

    boolean isW(V2 elf) {
        var tmp = elf.left();
        if (set.contains(tmp)) return false;
        if (set.contains(tmp.up())) return false;
        if (set.contains(tmp.down())) return false;
        return true;
    }

    void printset(Set<V2> set) {

        var minr = set.stream().mapToInt(v -> v.row()).min().orElse(0);
        var minc = set.stream().mapToInt(v -> v.col()).min().orElse(0);
        var maxr = set.stream().mapToInt(v -> v.row()).max().orElse(0);
        var maxc = set.stream().mapToInt(v -> v.col()).max().orElse(0);

        var grid = new char[1 + maxr - minr][1 + maxc - minc];
        for (var arr : grid) {
            Arrays.fill(arr, '.');
        }

        for (var s : set) {
            grid[s.row() - minr][s.col() - minc] = '#';
        }

        println(grid);
        System.out.println("----");

    }
}

