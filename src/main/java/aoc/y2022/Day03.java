package aoc.y2022;

import aoc.AU;
import aoc.AocException;

import java.util.HashSet;
import java.util.Set;

public class Day03 extends AU {

    public static void main(String[] args) {
        new Day03();
    }

    Day03() {
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + solveQ2());
    }

    Object solveQ2() {
        var result = 0L;
        var input = getInputLines();

        int count = 0;

        Set<String> set = new HashSet<>();
        for (var line : input) {

            var map = mapCharCount(line);
            count++;
            if (count == 1) {
                set.addAll(map.keySet());
            } else {
                set.retainAll(map.keySet());
            }

            if (count == 3) {
                 char c = set.stream().findAny().orElse("a").charAt(0);
                if (c >= 'A' && c <= 'Z') {
                    result += (c - 'A') + 27;
                }

                if (c >= 'a' && c <= 'z') {
                    result += (c - 'a') + 1;
                }
                System.out.println(set);
                set.clear();
                count = 0;
            }
        }
        
        return result;
    }

    Object solveQ1() {
        var result = 0L;
        var input = getInputLines();

        int count = 0;

        for (var ln : input) {
            count++;
            if (count == 3) {
                count = 0;

            }
            var r1 = ln.substring(ln.length()/2);
            var r2 = ln.substring(0, ln.length()/2);

            var c = common(r1, r2);

            if (!Character.isAlphabetic(c)) {
                throw new AocException("No common char found");
            }
            if (c >= 'A' && c <= 'Z') {
                result += (c - 'A') + 27;
            }

            if (c >= 'a' && c <= 'z') {
                result += (c - 'a') + 1;
            }
        }

        return result;
    }

    char common(String a, String b) {
        for (int i = 0; i < a.length(); i++) {
            if (b.indexOf(a.charAt(i)) != -1) {
                return a.charAt(i);
            }
        }
        System.out.println(a + "\n" + b + "\n");
        throw new AocException("No common char");
    }

}

