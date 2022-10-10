package aoc.y2016;

import aoc.AU;

import java.util.ArrayList;

public class Day07 extends AU {

    @Override
    protected String getDay() {
        return "07";
    }

    public static void main(String[] args) {
        new Day07();
    }

    Day07() {
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + solveQ2());
    }

    Object solveQ2() {
        return getInputStream()
                .filter(this::supportsTLS)
                .count();
    }

    Object solveQ1() {
        return getInputStream()
                .filter(this::supportsSSL)
                .count();
    }

    boolean supportsTLS(String str) {
        var splt = str.split("[\\[\\]]");
        var l1 = new ArrayList<String>();
        var l2 = new ArrayList<String>();
        for (int i = 0; i < splt.length; i++) {
            if (i % 2 != 0) {
                l1.addAll(getAbas(splt[i]));
            } else {
                l2.addAll(getBabs(splt[i]));
            }
        }
        return l1.stream().anyMatch(l2::contains);
    }

    boolean supportsSSL(String str) {
        var splt = str.split("[\\[\\]]");
        int count = 0;
        for (int i = 0; i < splt.length; i++) {
            if (i % 2 != 0) {
                if (hasABBA(splt[i])) {
                    return false;
                }
            } else {
                if (hasABBA(splt[i])) {
                    count++;
                }
            }
        }

        return count != 0;
    }

    ArrayList<String> getAbas(String str) {
        var list = new ArrayList<String>();
        for (var i = 0; i < str.length() - 2; i++) {
            var a = str.charAt(i);
            var b = str.charAt(i + 1);
            var c = str.charAt(i + 2);
            if (a == c && a != b) {
                list.add("" + b + a + b);
            }
        }
        return list;
    }

    ArrayList<String> getBabs(String str) {
        var list = new ArrayList<String>();
        for (var i = 0; i < str.length() - 2; i++) {
            var a = str.charAt(i);
            var b = str.charAt(i + 1);
            var c = str.charAt(i + 2);
            if (a == c && a != b) {
                list.add("" + a + b + a);
            }
        }
        return list;
    }

    boolean hasABBA(String str) {
        for (var i = 0; i < str.length() - 3; i++) {
            var a = str.charAt(i);
            var b = str.charAt(i + 1);
            var c = str.charAt(i + 2);
            var d = str.charAt(i + 3);
            if (a == d && b == c && a != b) {
                return true;
            }
        }
        return false;
    }
}

