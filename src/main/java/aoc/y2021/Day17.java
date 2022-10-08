package aoc.y2021;

import aoc.AU;

public class Day17 extends AU {

    public static void main(String[] args) {
        solveQ1();
        solveQ2();
    }

    /**
     * How many distinct initial velocity values cause the probe to be within the target area after any step?
     */
    static void solveQ2() {
        int count = 0;
        for (int x = -200; x < 500; x++) {
            for (int y = -500; y < 5000; y++) {
                var t = new Traject(x, y);
                if (t.run()) {
                    count++;
                }
            }
        }

        println("Day 17 Q2: " + count);
    }

    /**
     * Find the initial velocity that causes the probe to reach the highest y position and still eventually be within the target area after any step.
     * What is the highest y position it reaches on this trajectory?
     */
    static void solveQ1() {
        int max = 0;
        for (int x = 1; x < 500; x++) {
            for (int y = 1; y < 5000; y++) {
                var t = new Traject(x, y);
                if (t.run()) {
                    max = Math.max(max, t.maxy);
                }
            }
        }

        println("Day 17 Q1: " + max);
    }


    static boolean contains(int x, int y) {
        return x <= 303 && x >= 244 && y <= -54 && y >= -91;
    }

    private static class Traject {
        Traject(int xv, int yv) {
            xvel = xv;
            yvel = yv;
        }

        int maxy;
        int xpos;
        int ypos;
        int xvel;
        int yvel;

        private boolean step() {
            xpos += xvel;
            ypos += yvel;
            maxy = Math.max(maxy, ypos);
            if (--xvel == -1) {
                xvel = 0;
            }
            yvel--;
            return contains(xpos, ypos);
        }

        boolean run() {
            while (ypos > -100) {
                if (step()) {
                    return true;
                }
            }
            return false;
        }
    }
}

