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
            case "q1-test" -> println("Test Q1: " + solveQ1(testData));
            case "q1-real" -> println("Real Q1: " + solveQ1(getInputLines()));
            case "q2-test" -> println("Test Q2: " + solveQ2(testData));
            case "q2-real" -> println("Real Q2: " + solveQ2(getInputLines()));
            default -> throw new AocException("Unknown case");
        }
        println("Time: " + (System.currentTimeMillis() - startTime) + "ms");
    }

    public static void main(String[] args) {
        if (!testData1.get().isEmpty()) new Day22(testData1.get(), true);
//        new Day22(null, true);
        if (true) return;
        if (!testData1.get().isEmpty()) new Day22(testData1.get(), false);
        if (!testData2.get().isEmpty()) new Day22(testData2.get(), false);
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

    static Supplier<List<String>> testData2 = () -> """
            """.lines().collect(toList());

    Object solveQ2(List<String> input) {
        var result = 0L;
        //DAY2 !!
        return result;
    }

    static class Neighbors {
        public Node left;
        public Node right;
        public Node up;
        public Node down;

        @Override
        public String toString() {
            return "Neighbors{" +
                    "left=" + left +
                    ", right=" + right +
                    ", up=" + up +
                    ", down=" + down +
                    '}';
        }
    }

    record Node(V2 v2, Neighbors neighbors, boolean passable) {
        Node(int r, int c, boolean passable) {
            this(V2.of(r, c), new Neighbors(), passable);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "v2=" + v2 +
                    ", passable=" + passable +
                    '}';
        }
    }

    int mod;

    char[][] grid;

    Object solveQ1(List<String> input) {
        var result = 0L;
        var parts = chunk(input);
        var mapinput = parts.get(0);
        var programinput = parts.get(1).get(0);

        grid = charGrid(mapinput);


        int index = 0;
        while (grid[0][index] != '.') {
            index++;
        }
        Monkey current = new Monkey(0, index, '>');
        grid[current.r][current.c] = current.facing;

        var dirs = programinput.replaceAll("[0-9]*", "");
        var ints = toInts(programinput);
        System.out.println("dirs = " + dirs);
        System.out.println("ints = " + Arrays.toString(ints));


        mod = 4;
        var firstlen = ints[0];
        while (firstlen > 0) {
            current = getRight(grid, current);
            place(grid, current);
            firstlen--;
        }
//        printSegments(grid);

        var segments = getSegments(grid);

        System.out.println("segments = " + segments);
        buildMapping(segments);

        for (int i = 0; i < dirs.length(); i++) {
            var dir = dirs.charAt(i);
            var len = ints[i + 1];

            current = current.turn(dir);
            place(grid, current);

            while (len > 0) {

                switch (current.facing) {
                    case '^' -> current = getUp(grid, current);
                    case 'v' -> current = getDown(grid, current);
                    case '<' -> current = getLeft(grid, current);
                    case '>' -> current = getRight(grid, current);
                }
                place(grid, current);
                len--;
            }

            println(dir + " " + len);
        }

        println(grid);
        int facescore = 0;
        if (current.facing == '>') facescore = 0;
        if (current.facing == 'v') facescore = 1;
        if (current.facing == '<') facescore = 2;
        if (current.facing == '^') facescore = 3;

        result = 1000 * (current.r + 1) + 4 * (current.c + 1) + facescore;

        return result;
    }

    void printSegments(char[][] grid) {

        var segments = getSegments(grid);
        for (var segment : segments) {
            for (int i = segment.r1; i <= segment.r2; i++) {
                for (int j = segment.c1; j <= segment.c2; j++) {
                    System.out.print(grid[i][j]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    record Segment(int r1, int c1, int r2, int c2, int r, int c, int nr) {


        boolean contains(int r, int c) {
            return r >= r1 && r <= r2 && c >= c1 && c <= c2;
        }
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

        Segment fst = s1;
        Segment sec = s2;


        fst = s1;
        sec = s3;
        for (int i = 0; i < mod; i++) {
            mapping.put(
                    new Monkey(fst.r1() + i, fst.c1 + 1, '<'),
                    new Monkey(sec.r1, sec.c1 + i, 'v')
            );
            mapping.put(
                    new Monkey(sec.r1 - 1, sec.c1 + i, '^'),
                    new Monkey(fst.r1() + i, fst.c1, '>')
            );
        }


        /// segment 4 - 6
        fst = s4;
        sec = s6;
        for (int i = 0; i < mod; i++) {
            mapping.put(
                    new Monkey(fst.r1() + i, fst.c2 + 1, '>'),
                    new Monkey(sec.r1, sec.c2 - i, 'v')
            );
            mapping.put(
                    new Monkey(sec.r1 - 1, sec.c1 + i, '^'),
                    new Monkey(fst.r2() - i, fst.c2, '<')
            );
        }

        fst = s5;
        sec = s2;
        for (int i = 0; i < mod; i++) {
            mapping.put(
                    new Monkey(fst.r2() + 1, fst.c1 + i, 'v'),
                    new Monkey(sec.r2, sec.c2 - i, '^')
            );
            mapping.put(
                    new Monkey(sec.r2 + 1, sec.c1 + i, 'v'),
                    new Monkey(fst.r2(), fst.c2 - i, '^')
            );
        }
        System.out.println();


        for (var segment : segments) {

        }


        return mapping;
    }

    record Trans(int segment, Monkey monkey) {


    }


    void place(char[][] grid, Monkey current) {
        grid[current.r][current.c] = current.facing;
    }

    Monkey getRight(char[][] grid, Monkey m) {
        var tmp = new Monkey(m.r, m.c + 1, m.facing);
        if (mapping.containsKey(tmp)) tmp = mapping.get(tmp);
        if (grid[tmp.r][tmp.c] == '#') return m;
        return tmp;
    }

    Monkey getMapped(Monkey monkey) {
        var mapped = mapping.get(monkey);
        if (grid[mapped.r][mapped.c] == '#') {
            return monkey;
        }
        return mapped;
    }

    Monkey getLeft(char[][] grid, Monkey m) {
        var tmp = new Monkey(m.r, m.c - 1, m.facing);

        if (mapping.containsKey(tmp)) {
            return getMapped(tmp);
        }

        while (true) {
            if (tmp.c < 0) tmp = new Monkey(tmp.r, (tmp.c + mod + 2) % mod, tmp.facing);
            var c = grid[tmp.r][tmp.c];
            if (c == '#') {
                return m;
            }
            if (c == '.') {
                return tmp;
            }
            if (c == '>' || c == '<' || c == '^' || c == 'v') {
                return tmp;
            }
            if (c == ' ') {
                tmp = new Monkey(tmp.r, (tmp.c + mod + 2) % mod, tmp.facing);
                c = grid[tmp.r][tmp.c];
                while (c == ' ') {
                    tmp = new Monkey(tmp.r, tmp.c - 1, tmp.facing);
                    c = grid[tmp.r][tmp.c];
                }
            }
        }
    }

    Monkey getDown(char[][] grid, Monkey m) {
        var tmp = new Monkey(m.r + 1, m.c, m.facing);
        if (mapping.containsKey(tmp)) tmp = mapping.get(tmp);
        if (grid[tmp.r][tmp.c] == '#') return m;
        return tmp;
    }

    Monkey getUp(char[][] grid, Monkey m) {
        var tmp = new Monkey(m.r - 1, m.c, m.facing);

        if (mapping.containsKey(tmp)) {
            var mapped = mapping.get(tmp);
            if (grid[mapped.r][mapped.c] == '#') {
                return m;
            }
            return mapped;
        }


        while (true) {
            if (tmp.r < 0) tmp = new Monkey((tmp.r + 51) % 50, tmp.c, tmp.facing);
            var c = grid[tmp.r][tmp.c];
            if (c == '#') {
                return m;
            }
            if (c == '.') {
                return tmp;
            }
            if (c == '>' || c == '<' || c == '^' || c == 'v') {
                return tmp;
            }
            if (c == ' ') {
                tmp = new Monkey((tmp.c + 51) % 50, tmp.c, tmp.facing);
                c = grid[tmp.r][tmp.c];
                while (c == ' ') {
                    tmp = new Monkey(tmp.r - 1, tmp.c, tmp.facing);
                    c = grid[tmp.r][tmp.c];
                }
            }
        }
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

        char getChar(char[][] map) {
            return map[r][c];
        }

        boolean isInGrid(char[][] map) {
            return r >= 0 && r < map.length && c >= 0 && c < map[0].length;
        }


    }


    void print(Set<Node> set) {
        int[][] grid = new int[100][100];

        for (Node node : set) {
            var monkey = node.v2;
            grid[monkey.row()][monkey.col()] = node.passable ? 1 : 2;
        }

        for (int[] ints : grid) {
            for (int anInt : ints) {
                System.out.print(anInt);
            }
            System.out.println();
        }
    }

}

