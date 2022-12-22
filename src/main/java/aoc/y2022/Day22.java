package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;
import aoc.misc.V2;

import javax.swing.text.Segment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class Day22 extends AU {


    Day22(List<String> testData, boolean q1) {
        var startTime = System.currentTimeMillis();
        switch ((q1 ? "q1" : "q2") + "-" + (testData == null ? "real" : "test")) {
            case "q2-test" -> println("Test Q2: " + solveQ2(testData));
            case "q2-real" -> println("Real Q2: " + solveQ2(getInputLines()));
            default -> throw new AocException("Unknown case");
        }
        println("Time: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    public static void main(String[] args) {
//        if (!testData1.get().isEmpty()) new Day22(testData1.get(), true);
//        new Day22(null, true);
//        if (!testData1.get().isEmpty()) new Day22(testData1.get(), false);
        new Day22(null, false);
    }

    static Supplier<List<String>> testData1 = () -> """
                    ...#
                    .#..
                    #...
                    ....
            ...#.......#
            ........#...
            ..#....#....
            ..........#.
                    ...#....
                    .....#..
                    .#......
                    ......#.
                        
            10R5L5R10L4R5L5
            """.lines().collect(toList());


    private int mod;

    private char[][] grid;
    private String dirs;
    private Monkey current;
    private Integer[] ints;


    Object solveQ2(List<String> input) {
        var result = 0L;
        init(input);
        var firstlen = ints[0];
        while (firstlen > 0) {
            current = current.move();
            place(current);
            firstlen--;
        }

        var segments = getSegments(grid);

        buildMapping(segments);

        for (int i = 0; i < dirs.length(); i++) {
            var dir = dirs.charAt(i);
            var len = ints[i + 1];

            current = current.turn(dir);
            place(current);

            while (len > 0) {

                var next = current.move();
                if (mapping.containsKey(next)) next = mapping.get(next);
                if (grid[next.r][next.c] != '#') current = next;
                place(current);
                len--;
            }
        }

//        println(grid);
        int facescore = 0;
        if (current.facing == 'v') facescore = 1;
        if (current.facing == '<') facescore = 2;
        if (current.facing == '^') facescore = 3;

        result = 1000 * (current.r + 1) + 4 * (current.c + 1) + facescore;

        return result;
    }

    record Segment(int r1, int c1, int r2, int c2, int r, int c, int nr) {

    }

    List<Segment> getSegments(char[][] grid) {
        int counter = 1;
        int r = 0;
        int c = 0;
        List<Segment> segments = new ArrayList<>();
        while (counter <= 6) {
            if (c * mod >= grid[0].length) {
                c = 0;
                r += 1;
                continue;
            }
            if (grid[r * mod][c * mod] == ' ') {
                c += 1;
                continue;
            }
            segments.add(new Segment(r * mod, c * mod, (r * mod) + mod - 1, (c * mod) + mod - 1, r, c, counter));

            c += 1;
            counter++;
        }
        return segments;
    }

    Map<Monkey, Monkey> mapping = new HashMap<>();

    Map<Monkey, Monkey> buildMapping(List<Segment> segments) {

        var s1 = segments.stream().filter(s -> s.nr == 1).findFirst().orElseThrow();
        var s2 = segments.stream().filter(s -> s.nr == 2).findFirst().orElseThrow();
        var s3 = segments.stream().filter(s -> s.nr == 3).findFirst().orElseThrow();
        var s4 = segments.stream().filter(s -> s.nr == 4).findFirst().orElseThrow();
        var s5 = segments.stream().filter(s -> s.nr == 5).findFirst().orElseThrow();
        var s6 = segments.stream().filter(s -> s.nr == 6).findFirst().orElseThrow();

        Segment fst;
        Segment sec;

        /// segment 1 - 6
        fst = s1;
        sec = s6;
        for (int i = 0; i < mod; i++) {
            mapping.put(
                    new Monkey(fst.r1() - 1, fst.c1 + i, '^'),
                    new Monkey(sec.r1 + i, sec.c1, '>')
            );
            mapping.put(
                    new Monkey(sec.r1 + i, sec.c1 - 1, '<'),
                    new Monkey(fst.r1(), fst.c1 + i, 'v')
            );
        }


        /// segment 1 - 4
        fst = s1;
        sec = s4;
        for (int i = 0; i < mod; i++) {
            mapping.put(
                    new Monkey(fst.r1() + i, fst.c1 - 1, '<'),
                    new Monkey(sec.r2 - i, sec.c1, '>')
            );

            mapping.put(
                    new Monkey(sec.r1 + i, sec.c1 - 1, '<'),
                    new Monkey(fst.r2() - i, fst.c1, '>')
            );

        }


        /// segment 4 - 3
        fst = s4;
        sec = s3;
        for (int i = 0; i < mod; i++) {
            mapping.put(
                    new Monkey(fst.r1() - 1, fst.c1 + i, '^'),
                    new Monkey(sec.r1 + i, sec.c1, '>')
            );

            mapping.put(
                    new Monkey(sec.r1 + i, sec.c1 - 1, '<'),
                    new Monkey(fst.r1(), fst.c1 + i, 'v')
            );

        }


        /// segment 3 - 2
        fst = s3;
        sec = s2;
        for (int i = 0; i < mod; i++) {
            mapping.put(
                    new Monkey(fst.r1() + i, fst.c2 + 1, '>'),
                    new Monkey(sec.r2, sec.c1 + i, '^')
            );

            mapping.put(
                    new Monkey(sec.r2 + 1, sec.c1 + i, 'v'),
                    new Monkey(fst.r1() + i, fst.c2, '<')
            );

        }


        /// segment 6 - 2
        fst = s6;
        sec = s2;
        for (int i = 0; i < mod; i++) {
            mapping.put(
                    new Monkey(fst.r2() + 1, fst.c1 + i, 'v'),
                    new Monkey(sec.r1, sec.c1 + i, 'v')
            );

            mapping.put(
                    new Monkey(sec.r1 - 1, sec.c1 + i, '^'),
                    new Monkey(fst.r2(), fst.c1 + i, '^')
            );

        }


        /// segment 5 - 6
        fst = s5;
        sec = s6;
        for (int i = 0; i < mod; i++) {
            mapping.put(
                    new Monkey(fst.r2() + 1, fst.c1 + i, 'v'),
                    new Monkey(sec.r1 + i, sec.c2, '<')
            );

            mapping.put(
                    new Monkey(sec.r1 + i, sec.c2 + 1, '>'),
                    new Monkey(fst.r2(), fst.c1 + i, '^')
            );

        }


        /// segment 5 - 2
        fst = s5;
        sec = s2;
        for (int i = 0; i < mod; i++) {
            mapping.put(
                    new Monkey(fst.r1() + i, fst.c2 + 1, '>'),
                    new Monkey(sec.r2 - i, sec.c2, '<')
            );

            mapping.put(
                    new Monkey(sec.r1 + i, sec.c2 + 1, '>'),
                    new Monkey(fst.r2() - i, fst.c2, '<')
            );

        }


        return mapping;
    }

    void place(Monkey current) {
        grid[current.r][current.c] = current.facing;
    }


    record Monkey(int r, int c, Character facing) {

        Monkey turn(Character ch) {
            var newfacing = switch (ch) {
                case 'R' -> switch (facing) {
                    case '^' -> '>';
                    case '>' -> 'v';
                    case 'v' -> '<';
                    case '<' -> '^';
                    default -> throw new AocException("Unknown facing: " + facing);
                };
                case 'L' -> switch (facing) {
                    case '^' -> '<';
                    case '>' -> '^';
                    case 'v' -> '>';
                    case '<' -> 'v';
                    default -> throw new AocException("Unknown facing: " + facing);
                };
                default -> throw new AocException("Unknown turn: " + c);
            };
            return new Monkey(r, c, newfacing);
        }

        Monkey move() {
            var newr = r;
            var newc = c;
            switch (facing) {
                case '^' -> newr--;
                case '>' -> newc++;
                case 'v' -> newr++;
                case '<' -> newc--;
            }
            return new Monkey(newr, newc, facing);
        }
    }

    void init(List<String> input) {

        var parts = chunk(input);
        var mapinput = parts.get(0);
        var programinput = parts.get(1).get(0);

        grid = charGrid(mapinput);

        int index = 0;
        while (grid[0][index] != '.') index++;

        current = new Monkey(0, index, '>');
        grid[current.r][current.c] = current.facing;
        dirs = programinput.replaceAll("[0-9]*", "");
        ints = toInts(programinput);
        mod = mapinput.stream().mapToInt(l -> l.strip().length()).min().orElse(0);

    }
}

