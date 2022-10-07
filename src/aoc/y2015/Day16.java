package aoc.y2015;

import aoc.AU;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day16 extends AU {
    public static void main(String[] args) { new Day16(); }
    Day16() {
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + solveQ2());
    }
    Object solveQ2() {
        return null;
    }

    Map<String, Integer> sue = new HashMap<>();
    {
        sue.put("children", 3);
        sue.put("cats", 7);
        sue.put("samoyeds", 2);
        sue.put("pomeranians", 3);
        sue.put("akitas", 0);
        sue.put("vizslas", 0);
        sue.put("goldfish", 5);
        sue.put("trees", 3);
        sue.put("cars", 2);
        sue.put("perfumes", 1);

    }

    Object solveQ1() {
        var input = getInputLines();
        var result = 0;
        Map<String, Map<String, Integer>> map = new HashMap<>();
        var splits = input.stream().map(i -> i.split("[,:]")).toList();
        for (var split : splits) {
            for (int x = 0; x < split.length; x++) {
                split[x] = split[x].trim();
            }
            var m = map.put(split[0], new HashMap<>());
            m = map.get(split[0]);

            for (int i = 1; i < split.length; i += 2) {
                m.put(split[i], Integer.parseInt(split[i + 1]));
            }

        }

        for (var s : sue.entrySet()) {
            List<String> notsue = new ArrayList<>();
            for (var m : map.entrySet()) {
                if (m.getValue().containsKey(s.getKey())) {
                    if (m.getValue().get(s.getKey()) != s.getValue()) {
                        notsue.add(m.getKey());
                    }
                }
            }
            for (var n : notsue) {
                map.remove(n);
            }
        }

        System.out.println(map);



        return result;
    }


    public String getDay() {
        return "16";
    }

}

