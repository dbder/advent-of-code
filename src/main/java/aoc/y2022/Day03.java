package aoc.y2022;

import aoc.AU;
import aoc.misc.AocException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
                 char c = set.stream().findAny().orElse("0").charAt(0);
                 if (c == 0) {
                     count = 0;
                     continue;
                 }
                if (c >= 'A' && c <= 'Z') {
                    result += (c - 'A') + 27;
                }

                if (c >= 'a' && c <= 'z') {
                    result += (c - 'a') + 1;
                }
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
            System.out.println(count + " " + ln);
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
        Set<Character> list = new HashSet<>();
        for (int i = 0; i < a.length(); i++) {
            if (b.indexOf(a.charAt(i)) != -1) {
                list.add(a.charAt(i));
//                return a.charAt(i);
            }
        }
        if (list.size() > 1) {
            System.out.println(list);
        }

        return list.stream().findFirst().orElse('0');
//        System.out.println(a + "\n" + b + "\n");
//        throw new AocException("No common char");
    }

}



// get only CHAR from next operations
// for each 3 lines:
//   create a set for the first line
//   create a set for the second line
//   create a set for the third line
//   intersect the 3 sets ( set.retainAll() .. retainAll() )
// add to the counter if CHAR:
//    lowercase: 1-26
//    uppercase: 27-52
