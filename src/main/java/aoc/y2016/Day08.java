package aoc.y2016;

import aoc.AU;

public class Day08 extends AU {

    @Override
    protected String getDay() {
        return "08";
    }


    public static void main(String[] args) {
        new Day08();
    }

    Day08() {
        println("Day " + getDay() + " Q1: " + solve(false));
        println("Day " + getDay() + " Q2: " + solve(true));
    }

    Object solve(boolean q2) {

        var input = getInputLines();
        var screen = new boolean[6][50];
        for (var in : input) {
            var split = in.split(" ");
            switch (split[0]) {
                case "rect" -> {
                    var xy = split[1].split("x");
                    var x = Integer.parseInt(xy[0]);
                    var y = Integer.parseInt(xy[1]);
                    for (int i = 0; i < y; i++) {
                        for (int j = 0; j < x; j++) {
                            screen[i][j] = true;
                        }
                    }
                }
                case "rotate" -> {
                    var row = Integer.parseInt(split[2].split("=")[1]);
                    var by = Integer.parseInt(split[4]);
                    if (split[1].equals("row")) {
                        var temp = new boolean[50];
                        for (int i = 0; i < 50; i++) {
                            temp[(i + by) % 50] = screen[row][i];
                        }
                        screen[row] = temp;
                    } else {
                        var temp = new boolean[6];
                        for (int i = 0; i < 6; i++) {
                            temp[(i + by) % 6] = screen[i][row];
                        }
                        for (int i = 0; i < 6; i++) {
                            screen[i][row] = temp[i];
                        }
                    }
                }
            }
        }
        int result = 0;
        for (var row : screen) {
            for (var col : row) {
                System.out.print((col ? "#" : " "));
                if (col) {
                    result++;
                }
            }
            System.out.println();
        }
        System.out.println();

        return q2 ? "CFLELOYFCS" : result;
    }

}

