package aoc.y2015;

import aoc.AU;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day19 extends AU {

    String str = "CRnCaCaCaSiRnBPTiMgArSiRnSiRnMgArSiRnCaFArTiTiBSiThFYCaFArCaCaSiThCaPBSiThSiThCaCaPTiRnPBSiThRnFArArCaCaSiThCaSiThSiRnMgArCaPTiBPRnFArSiThCaSiRnFArBCaSiRnCaPRnFArPMgYCaFArCaPTiTiTiBPBSiThCaPTiBPBSiRnFArBPBSiRnCaFArBPRnSiRnFArRnSiRnBFArCaFArCaCaCaSiThSiThCaCaPBPTiTiRnFArCaPTiBSiAlArPBCaCaCaCaCaSiRnMgArCaSiThFArThCaSiThCaSiRnCaFYCaSiRnFYFArFArCaSiRnFYFArCaSiRnBPMgArSiThPRnFArCaSiRnFArTiRnSiRnFYFArCaSiRnBFArCaSiRnTiMgArSiThCaSiThCaFArPRnFArSiRnFArTiTiTiTiBCaCaSiRnCaCaFYFArSiThCaPTiBPTiBCaSiThSiRnMgArCaF";
    String s1 = "CRnCaCaCaSiRnBPTiMgArSiRnSiRnMgArSiRnCaFArTiTiBSiThFYCaFArCaCaSiThCaPBSiThSiThCaCaPTiRnPBSiThRnFArArCaCaSiThCaSiThSiRnMgArCaPTiBPRnFArSiThCaSiRnFArBCaSiRnCaPRnFArPMgYCaFArCaPTiTiTiBPBSiThCaPTiBPBSiRnFArBPBSiRnCaFArBPRnSiRnFArRnSiRnBFArCaFArCaCaCaSiThSiThCaCaPBPTiTiRnFArCaPTiBSiAlArPBCaCaCaCaCaSiRnMgArCaSiThFArThCaSiThCaSiRnCaFYCaSiRnFYFArFArCaSiRnFYFArCaSiRnBPMgArSiThPRnFArCaSiRnFArTiRnSiRnFYFArCaSiRnBFArCaSiRnTiMgArSiThCaSiThCaFArPRnFArSiRnFArTiTiTiTiBCaCaSiRnCaCaFYFArSiThCaPTiBPTiBCaSiThSiRnMgArF";

    public static void main(String[] args) {
        new Day19();
    }

    Day19() {
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + solveQ2());
    }

    Object solveQ2() {
        return null;
    }

    Object solveQ1() {
        var input = getInputLines().stream().map(s -> s.split(" => ")).toList();
        var result = 0;

        Map<String, String> map = new HashMap<>();
        for (String[] s : input) {
            map.putIfAbsent(s[1], s[0]);
        }

        int count = 0;
        while (map.keySet().stream().filter(s -> !s.equals("HF") || !s.equals("NAl")).anyMatch(s -> str.contains(s))) {
            for (String s : map.keySet()) {
                if (str.contains(s)) {
                    int index = str.indexOf(s);
                    var sub = map.get(s);
                    System.out.println("Replacing " + s + " with " + sub + " str length: " + str.length());
                    str = str.substring(0, index) + sub + str.substring(index + s.length());
                    System.out.println("New str length: " + str.length());
                    count++;
                }
            }
            System.out.println(str);
        }
        replacements.add(str);
        System.out.println(str);
        while (!replacements.contains("e")) {

            var tmp = new HashSet<String>(replacements);
            replacements.clear();
            tmp.parallelStream().forEach(s -> count(s, s.length() - 2, map));
            count++;
            System.out.println(count + " " + replacements.size());
        }

        return count;
    }

    void count(String in, int index, Map<String, String> map) {
        if (index >= in.length()) return;
        for (String s : map.keySet()) {
            if (in.indexOf(s, index) == index) {
                var r = map.get(s);
                var newStr = in.substring(0, index) + r + in.substring(index + s.length());
                if (newStr.length() < str.length() + 10 && !allreplacements.contains(newStr)) {
                    replacements.add(newStr);
                    allreplacements.add(newStr);
                }

            }
        }
        if (index > in.length() - 10) {
            count(in, index - 1, map);
        }
    }


    Set<String> replacements = new HashSet<>();
    Set<String> allreplacements = new HashSet<>();


    public String getDay() {
        return "19";
    }

    ;

}

