package aoc.y2015;

import aoc.AU;
import aoc.misc.AocException;

public class Day23 extends AU {

    @Override
    protected String getDay() {
        return "23";
    }

    public static void main(String[] args) {
        new Day23();
    }

    Day23() {
        println("Day " + getDay() + " Q1: " + solve(false));
        println("Day " + getDay() + " Q2: " + solve(true));
    }

    Object solve(boolean q2) {
        var input = getInputLines();

        int[] registers = new int['z' + 1];
        if (q2) registers['a'] = 1;
        for (int in = 0; in < input.size(); in++) {
            var split = input.get(in).replace(",", "").split(" ");
            switch (split[0]) {
                case "jio" -> {
                    if (registers[split[1].charAt(0)] == 1) {
                        in += Integer.parseInt(split[2]) - 1;
                    }
                }
                case "hlf" -> registers[split[1].charAt(0)] /= 2;
                case "tpl" -> registers[split[1].charAt(0)] *= 3;
                case "inc" -> registers[split[1].charAt(0)]++;
                case "jmp" -> in += (Integer.parseInt(split[1]) - 1);
                case "jie" -> {
                    if (registers[split[1].charAt(0)] % 2 == 0) {
                        in += (Integer.parseInt(split[2])) - 1;
                    }
                }
                default -> throw new AocException("Unknown instruction " + split[0]);
            }
        }
        return registers['b'];
    }

}

