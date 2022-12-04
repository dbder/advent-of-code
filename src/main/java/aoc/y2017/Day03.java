package aoc.y2017;

import aoc.AU;
import aoc.V2;

import java.util.HashSet;

public class Day03 extends AU {

    public static void main(String[] args) {
        new Day03();
    }

    Day03() {
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + solveQ2());
    }

    Object solveQ2() {

        int centr = 5000;
        int left = centr;
        int right = centr;
        int top = centr;
        int bottom = centr;


        int dir = 0;
        int r = centr;
        int c = centr;

        int[][] grid = new int[centr * 2][centr * 2];
        grid[centr][centr] = 1;
        while (true) {

            if (dir == 0) {
                c++;
                if (c > right) {
                    right++;
                    dir = 1;
                }
            } else if (dir == 1) {
                r--;
                if (r < top) {
                    top--;
                    dir = 2;
                }
            } else if (dir == 2) {
                c--;
                if (c < left) {
                    left--;
                    dir = 3;
                }
            } else {
                r++;
                if (r > bottom) {
                    bottom++;
                    dir = 0;
                }
            }

            for (var tp : TPS8) {
                grid[r][c] += grid[r + tp[0]][c + tp[1]];
            }

            if (grid[r][c] > 265149) {
                return grid[r][c];
            }
        }
    }

    Object solveQ1() {

        var pos = new V2(0, 0);
        var pos2 = new V2(0, 1);
        var set = new HashSet<V2>();

        set.add(pos);
        set.add(pos2);

        int counter = 1;
        int left = 5000;
        int right = 5000;
        int top = 5000;
        int bottom = 5000;


        int dir = 0;
        int r = 5000;
        int c = 5000;

        int[][] grid = new int[10000][10000];
        grid[5000][5000] = 1;
        while (counter < 265149) {

            if (dir == 0) {
                c++;
                if (c > right) {
                    right++;
                    dir = 1;
                }
            } else if (dir == 1) {
                r--;
                if (r < top) {
                    top--;
                    dir = 2;
                }
            } else if (dir == 2) {
                c--;
                if (c < left) {
                    left--;
                    dir = 3;
                }
            } else if (dir == 3) {
                r++;
                if (r > bottom) {
                    bottom++;
                    dir = 0;
                }
            }
            counter++;
        }

        return r + c - 10000;
    }

}


