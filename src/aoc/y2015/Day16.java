package aoc.y2015;

import aoc.AU;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day16 extends AU {
    public static void main(String[] args) {
        new Day16();
    }

    Map<String, Integer> suehas = new HashMap<>();

    Day16() {
        suehas.put("children", 3);
        suehas.put("cats", 7);
        suehas.put("samoyeds", 2);
        suehas.put("pomeranians", 3);
        suehas.put("akitas", 0);
        suehas.put("vizslas", 0);
        suehas.put("goldfish", 5);
        suehas.put("trees", 3);
        suehas.put("cars", 2);
        suehas.put("perfumes", 1);

        println("Day " + getDay() + " Q1: " + solve(false));
        println("Day " + getDay() + " Q2: " + solve(true));
    }


    Object solve(boolean q2) {

        Map<String, Map<String, Integer>> map = new HashMap<>();

        getInputStream()
                .map(i -> i.split("[,:]"))
                .map(this::trim)
                .forEach(split -> {
                    var m = map.computeIfAbsent(split[0], k -> new HashMap<>());
                    for (int i = 1; i < split.length; i += 2) {
                        m.put(split[i], Integer.parseInt(split[i + 1]));
                    }
                });

        for (var s : suehas.entrySet()) {
            List<String> notsue = new ArrayList<>();
            for (var m : map.entrySet()) {
                if (m.getValue().containsKey(s.getKey())) {
                    if (q2 && (s.getKey().equals("cats") || s.getKey().equals("trees"))) {
                        if (m.getValue().get(s.getKey()) <= s.getValue()) {
                            notsue.add(m.getKey());
                        }
                    } else if (q2 && (s.getKey().equals("pomeranians") || s.getKey().equals("goldfish"))) {
                        if (m.getValue().get(s.getKey()) >= s.getValue()) {
                            notsue.add(m.getKey());
                        }
                    } else if (!m.getValue().get(s.getKey()).equals(s.getValue())) {
                        notsue.add(m.getKey());
                    }
                }
            }
            notsue.forEach(map::remove);
        }

        return map.keySet()
                .stream()
                .findFirst()
                .orElse("")
                .substring(4);
    }

    @Override
    public String getDay() {
        return "16";
    }

}

