package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;
import aoc.misc.V2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    // someone else's test data
    static Supplier<List<String>> testData2 = () -> """
            abccccaaaaaaacccaaaaaaaccccccccccccccccccccccccccccccccccaaaa
            abcccccaaaaaacccaaaaaaaaaaccccccccccccccccccccccccccccccaaaaa
            abccaaaaaaaaccaaaaaaaaaaaaaccccccccccccccccccccccccccccaaaaaa
            abccaaaaaaaaaaaaaaaaaaaaaaacccccccccaaaccccacccccccccccaaacaa
            abaccaaaaaaaaaaaaaaaaaacacacccccccccaaacccaaaccccccccccccccaa
            abaccccaaaaaaaaaaaaaaaacccccccccccccaaaaaaaaaccccccccccccccaa
            abaccccaacccccccccaaaaaacccccccccccccaaaaaaaacccccccccccccccc
            abcccccaaaacccccccaaaaaaccccccccijjjjjjaaaaaccccccaaccaaccccc
            abccccccaaaaacccccaaaacccccccciiijjjjjjjjjkkkkkkccaaaaaaccccc
            abcccccaaaaacccccccccccccccccciiiirrrjjjjjkkkkkkkkaaaaaaccccc
            abcccccaaaaaccccccccccccccccciiiirrrrrrjjjkkkkkkkkkaaaaaccccc
            abaaccacaaaaacccccccccccccccciiiqrrrrrrrrrrssssskkkkaaaaacccc
            abaaaaacaaccccccccccccccccccciiiqqrtuurrrrrsssssskklaaaaacccc
            abaaaaacccccccccccaaccccccccciiqqqttuuuurrssusssslllaaccccccc
            abaaaaaccccccccaaaaccccccccciiiqqqttuuuuuuuuuuusslllaaccccccc
            abaaaaaacccccccaaaaaaccccccciiiqqqttxxxuuuuuuuusslllccccccccc
            abaaaaaaccccaaccaaaaacccccchhiiqqtttxxxxuyyyyvvsslllccccccccc
            abaaacacccccaacaaaaaccccccchhhqqqqttxxxxxyyyyvvsslllccccccccc
            abaaacccccccaaaaaaaacccccchhhqqqqtttxxxxxyyyvvssqlllccccccccc
            abacccccaaaaaaaaaaccaaacchhhpqqqtttxxxxxyyyyvvqqqlllccccccccc
            SbaaacaaaaaaaaaaaacaaaaahhhhppttttxxEzzzzyyvvvqqqqlllcccccccc
            abaaaaaaacaaaaaacccaaaaahhhppptttxxxxxyyyyyyyvvqqqlllcccccccc
            abaaaaaaccaaaaaaaccaaaaahhhppptttxxxxywyyyyyyvvvqqqmmcccccccc
            abaaaaaaacaaaaaaacccaaaahhhpppsssxxwwwyyyyyyvvvvqqqmmmccccccc
            abaaaaaaaaaaaaaaacccaacahhhpppssssssswyyywwvvvvvqqqmmmccccccc
            abaaaaaaaacacaaaacccccccgggppppsssssswwywwwwvvvqqqqmmmccccccc
            abcaaacaaaccccaaaccccccccgggppppppssswwwwwrrrrrqqqmmmmccccccc
            abcaaacccccccccccccccccccgggggpppoosswwwwwrrrrrqqmmmmddcccccc
            abccaacccccccccccccccccccccgggggoooosswwwrrrnnnmmmmmddddccccc
            abccccccccccccccccccccccccccgggggooossrrrrrnnnnnmmmddddaccccc
            abaccccaacccccccccccccccccccccgggfoossrrrrnnnnndddddddaaacccc
            abaccaaaaaaccccccccccccccccccccgffooorrrrnnnneeddddddaaaacccc
            abaccaaaaaacccccccccccccccccccccfffooooonnnneeeddddaaaacccccc
            abacccaaaaaccccccccaaccaaaccccccffffoooonnneeeeccaaaaaacccccc
            abcccaaaaacccccccccaaccaaaaccccccffffoooneeeeeaccccccaacccccc
            abaccaaaaaccccccccaaaacaaaaccccccafffffeeeeeaaacccccccccccccc
            abacccccccccccccccaaaacaaacccccccccffffeeeecccccccccccccccaac
            abaaaacccccccaaaaaaaaaaaaaacccccccccfffeeeccccccccccccccccaaa
            abaaaacccccccaaaaaaaaaaaaaaccccccccccccaacccccccccccccccccaaa
            abaacccccccccaaaaaaaaaaaaaaccccccccccccaacccccccccccccccaaaaa
            abaaaccccccccccaaaaaaaaccccccccccccccccccccccccccccccccaaaaaa
            """.lines().collect(toList());

    // runner
    Object solveQ2(List<String> input) {

        var starttime = System.currentTimeMillis();
        var grid = charGrid(input);
        var start = V2.origin();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 'E') {
                    start = new V2(r, c);
                    grid[r][c] = 'z';
                }
            }
        }

        int[] minDist = new int[]{Integer.MAX_VALUE};
        getDistQ2(grid, List.of(start), new HashSet<>(), 0, minDist);
        System.out.println("Q2: " + minDist[0] + " in " + (System.currentTimeMillis() - starttime) + "ms");
        return minDist[0];
    }

    // recursive
    void getDistQ2(char[][] grid, List<V2> level, Set<V2> visited, int dist, int[] minDist) {
        if (level.stream().map(v -> grid[v.row()][v.col()]).anyMatch(h -> h == 'a')) {
            minDist[0] = Math.min(minDist[0], dist);
            return;
        }

        if (dist >= minDist[0]) return;

        List<V2> nextLevel = new ArrayList<>();
        for (V2 v : level) {
            int height = grid[v.row()][v.col()];
            for (var n : v.neighbors()) {
                if (visited.contains(n) || !n.isIN(grid)) continue;
                if (height <= grid[n.row()][n.col()] + 1) {
                    nextLevel.add(n);
                    visited.add(n);
                }
            }
        }
        getDistQ2(grid, nextLevel, visited, dist + 1, minDist);
    }

    void getDistQ1(char[][] grid, List<V2> level, Set<V2> visited, int dist, V2 end, int[] minDist) {
        if (level.contains(end)) {
            minDist[0] = Math.min(minDist[0], dist);
            return;
        }
        if (dist >= minDist[0]) return;

        List<V2> nextLevel = new ArrayList<>();
        for (V2 v : level) {
            int height = grid[v.row()][v.col()];
            for (var n : v.neighbors()) {
                if (visited.contains(n) || !n.isIN(grid)) continue;
                if (grid[n.row()][n.col()] <= height + 1) {
                    nextLevel.add(n);
                    visited.add(n);
                }
            }
        }
        getDistQ1(grid, nextLevel, visited, dist + 1, end, minDist);
    }

    Object solveQ1(List<String> input) {

        var grid = charGrid(input);

        var start = V2.origin();
        var end = V2.origin();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 'S') {
                    start = new V2(r, c);
                    grid[r][c] = 'a';
                }
                if (grid[r][c] == 'E') {
                    end = new V2(r, c);
                    grid[r][c] = 'z';
                }
            }
        }

        int[] minDist = new int[]{Integer.MAX_VALUE};
        getDistQ1(grid, List.of(start), new HashSet<>(), 0, end, minDist);
        return minDist[0];
    }

}

