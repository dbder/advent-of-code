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

        Map<String, List<String>> map = new HashMap<>();
        for (String[] s : input) {
            map.putIfAbsent(s[0], new ArrayList<>());
            map.get(s[0]).add(s[1]);
        }
        count(str, 0, map);

        replacements.remove(str);
        return replacements.size();
    }

    void count(String in, int index, Map<String, List<String>> map) {
        if (index >= in.length()) return;
        for (String s : map.keySet()) {
            if (in.indexOf(s, index) == index) {
                for (String r : map.get(s)) {
                    var newStr = in.substring(0, index) + r + in.substring(index + s.length());
                    replacements.add(newStr);
                    //count(newStr, index + r.length(), map);
                }
            }
        }
        count(in, index + 1, map);
    }


    Set<String> replacements = new HashSet<>();


    public String getDay() {
        return "19";
    }

    ;

}

