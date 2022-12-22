package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;
import aoc.misc.V2;

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

    Object solveQ1(List<String> input) {
        var result = 0L;
        var parts = chunk(input);
        var mapinput = parts.get(0);
        var programinput = parts.get(1).get(0);

        var grid = charGrid(mapinput);


        int index = 0;
        while (grid[0][index] != '.'){
            index++;
        }
        Monkey current = new Monkey(0, index, '>');
        grid[current.r][current.c] = current.facing;

        var dirs = programinput.replaceAll("[0-9]*", "");
        var ints = toInts(programinput);
        System.out.println("dirs = " + dirs);
        System.out.println("ints = " + Arrays.toString(ints));

        var firstlen = ints[0];
        while (firstlen > 0) {
            current = getRight(grid, current);
            place(grid, current);
            firstlen--;
        }
        for (int i = 0; i < dirs.length(); i++) {
            var dir = dirs.charAt(i);
            var len = ints[i+1];

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

    void place(char[][] grid, Monkey current) {
        grid[current.r][current.c] = current.facing;
    }

    Monkey getRight(char[][] grid, Monkey m) {
        var tmp = new Monkey(m.r, m.c + 1, m.facing);
        while (true) {
            if (tmp.c >= grid[0].length) tmp = new Monkey(tmp.r, 0, tmp.facing);
            var c = grid[tmp.r][tmp.c];
            if (c == '#') {
                return m;
            }
            if (c == '.') {
                return tmp;
            }
            if (c == '>' || c == '<' || c == '^' || c == 'v') {return tmp;}
            if (c == ' ') {
                tmp = new Monkey(tmp.r, 0, tmp.facing);
                c = grid[tmp.r][tmp.c];
                while (c == ' ') {
                    tmp = new Monkey(tmp.r, tmp.c + 1, tmp.facing);
                    c = grid[tmp.r][tmp.c];
                }
            }
        }
    }

    Monkey getLeft(char[][] grid, Monkey m) {
        var tmp = new Monkey(m.r, m.c - 1, m.facing);
        while (true) {
            if (tmp.c < 0) tmp = new Monkey(tmp.r, grid[0].length - 1, tmp.facing);
            var c = grid[tmp.r][tmp.c];
            if (c == '#') {
                return m;
            }
            if (c == '.') {
                return tmp;
            }
            if (c == '>' || c == '<' || c == '^' || c == 'v') {return tmp;}
            if (c == ' ') {
                tmp = new Monkey(tmp.r, grid[0].length - 1, tmp.facing);
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
        while (true) {
            if (tmp.r >= grid.length) tmp = new Monkey(0, tmp.c, tmp.facing);
            var c = grid[tmp.r][tmp.c];
            if (c == '#') {
                return m;
            }
            if (c == '.' ) {
                return tmp;
            }
            if (c == '>' || c == '<' || c == '^' || c == 'v') {return tmp;}
            if (c == ' ') {
                tmp = new Monkey(0, tmp.c, tmp.facing);
                c = grid[tmp.r][tmp.c];
                while (c == ' ') {
                    tmp = new Monkey(tmp.r + 1, tmp.c, tmp.facing);
                    c = grid[tmp.r][tmp.c];
                }
            }
        }
    }

    Monkey getUp(char[][] grid, Monkey m) {
        var tmp = new Monkey(m.r - 1, m.c, m.facing);
        while (true) {
            if (tmp.r < 0) tmp = new Monkey(grid.length - 1, tmp.c, tmp.facing);
            var c = grid[tmp.r][tmp.c];
            if (c == '#') {
                return m;
            }
            if (c == '.') {
                return tmp;
            }
            if (c == '>' || c == '<' || c == '^' || c == 'v') {return tmp;}
            if (c == ' ') {
                tmp = new Monkey(grid.length - 1, tmp.c, tmp.facing);
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

