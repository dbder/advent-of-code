package aoc.y2015;

import aoc.AU;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Day13 extends AU {
    private static String day = "13";
    public static void main(String[] args) {

//        var input = getInputAsString("src/aoc/y2015/input/day" + day + "t");
        var input = getInputAsStream("src/aoc/y2015/input/day" + day).toList();

        solveQ1(input);
        solveQ2(input);

    }

    static Map<String, Map<String,Integer>> map = new HashMap<>();
    static void solveQ2(List<String> input) {
        for (var s : input) {
            var split = s.split(" ");
            var p1 = split[0];
            var p2 = split[10].substring(0, split[10].length() - 1);
            int mul = 1;
            if (split[2].equals("lose")) mul = -1;
            System.out.println(Arrays.toString(split));
            var value = Integer.parseInt(split[3]) * mul;
            map.putIfAbsent(p1, new HashMap<>());
            map.putIfAbsent(p2, new HashMap<>());

            map.get(p1).put(p2, value);
        }


        System.out.println(map);
        printAllRecursive(map.keySet().size(), map.keySet().toArray(new String[0]));

        System.out.println("best " + bestSum);
//        println("Day " + day +" Q2: " + "");
    }

//    static int getHappy(String[] arr, int i) {
//
//    }


    static void solveQ1(List<String> input) {
        var result = 0;


        println("Day " + day +" Q1: " + result);
    }


    public static void printAllRecursive(
            int n, String[] elements) {

        if(n == 1) {
            printArray(elements);
        } else {
            for(int i = 0; i < n-1; i++) {
                printAllRecursive(n - 1, elements);
                if(n % 2 == 0) {
                    swap(elements, i, n-1);
                } else {
                    swap(elements, 0, n-1);
                }
            }
            printAllRecursive(n - 1, elements);
        }
    }


    private static void swap(String[] input, int a, int b) {
        String tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }
    static int bestSum = 0;
    private static void printArray(String[] input) {
        int sum = 0;
        for (int i = 0; i < input.length; i++) {
            int prev = i - 1;
            int next = i + 1;
            if (prev < 0) prev = input.length - 1;
            if (next >= input.length) next = 0;

            String pp = input[prev];
            String np = input[next];
            String cp = input[i];

            sum += map.get(cp).get(pp);
            sum += map.get(cp).get(np);
        }
        if (sum > bestSum) bestSum = sum;

        System.out.print('\n');
        for(int i = 0; i < input.length; i++) {
            System.out.print(input[i]);
        }
    }
}

