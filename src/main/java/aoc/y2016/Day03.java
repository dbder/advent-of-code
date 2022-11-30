package aoc.y2016;

import aoc.AU;

import java.util.ArrayList;

public class Day03 extends AU {

    @Override
    protected String getDay() {
        return "03";
    }

    public static void main(String[] args) {
        new Day03();
    }

    Day03() {
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + solveQ2());
    }

    Object solveQ2() {
        var input = getInputLines();
        var list  = new ArrayList<Integer>();
        int count = 0;
        for (String item : input) {
            var str = item.trim();
            var arr = str.split("\\s+");
            list.add(Integer.parseInt(arr[0]));
        }
        for (String value : input) {
            var str = value.trim();
            var arr = str.split("\\s+");
            list.add(Integer.parseInt(arr[1]));
        }

        for (String s : input) {
            var str = s.trim();
            var arr = str.split("\\s+");
            list.add(Integer.parseInt(arr[2]));
        }
        for (int i = 0; i < list.size(); i += 3) {
            var a = list.get(i);
            var b = list.get(i + 1);
            var c = list.get(i + 2);
            if (a + b > c && a + c > b && b + c > a) {
                count++;
            }
        }
        return count;
    }

    Object solveQ1() {
        var input = getInputLines();
        int count = 0;
        for (var  str : input) {
            str = str.trim();
            var arr = str.split("\\s+");
            var a = Integer.parseInt(arr[0].trim());
            var b = Integer.parseInt(arr[1].trim());
            var c = Integer.parseInt(arr[2].trim());
            if (a + b > c && a + c > b && b + c > a) {
                count++;
            }
        }
        return count;
    }

}

