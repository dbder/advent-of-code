package aoc.y2016;

import aoc.AU;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day05 extends AU {

    @Override
    protected String getDay() {
        return "05";
    }

    public static void main(String[] args) {
        new Day05();
    }

    Day05() {
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + solveQ2());
    }

    Object solveQ2() {
        var input = "wtnhxymk";
        var list = new ArrayList<String>();
        var chars = new char[8];
        for (int i = 0; i < 100000000; i++) {
            var hash = getMD5Hash(input + i);
            if (hash.startsWith("00000")) {
                var pos = hash.charAt(5);
                var c = hash.charAt(6);
                if (pos >= '0' && pos <= '7' && chars[pos - '0'] == 0) {
                    chars[pos - '0'] = c;
                    if (isvalid(chars)) {
                        return new String(chars);
                    }
                }
            }
        }
        return list.stream().map(s -> s.substring(5, 6)).limit(8).collect(Collectors.joining());
    }

    Object solveQ1() {
        var input = "wtnhxymk";
        var list = new ArrayList<String>();
        for (int i = 0; i < 10000000; i++) {
            var hash = getMD5Hash(input + i);
            if (hash.startsWith("00000")) {
                list.add(hash);
            }
        }
        return list.stream().map(s -> s.substring(5, 6)).limit(8).collect(Collectors.joining());
    }

    private boolean isvalid(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 0) {
                return false;
            }
        }
        return true;
    }

}

