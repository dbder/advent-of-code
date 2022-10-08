package aoc.y2015;

import aoc.AU;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day19 extends AU {
    private static final Logger log = LogManager.getLogger(Day19.class);
    String str = "CRnCaCaCaSiRnBPTiMgArSiRnSiRnMgArSiRnCaFArTiTiBSiThFYCaFArCaCaSiThCaPBSiThSiThCaCaPTiRnPBSiThRnFArArCaCaSiThCaSiThSiRnMgArCaPTiBPRnFArSiThCaSiRnFArBCaSiRnCaPRnFArPMgYCaFArCaPTiTiTiBPBSiThCaPTiBPBSiRnFArBPBSiRnCaFArBPRnSiRnFArRnSiRnBFArCaFArCaCaCaSiThSiThCaCaPBPTiTiRnFArCaPTiBSiAlArPBCaCaCaCaCaSiRnMgArCaSiThFArThCaSiThCaSiRnCaFYCaSiRnFYFArFArCaSiRnFYFArCaSiRnBPMgArSiThPRnFArCaSiRnFArTiRnSiRnFYFArCaSiRnBFArCaSiRnTiMgArSiThCaSiThCaFArPRnFArSiRnFArTiTiTiTiBCaCaSiRnCaCaFYFArSiThCaPTiBPTiBCaSiThSiRnMgArCaF";

    public static void main(String[] args) {
        new Day19();
    }

    Day19() {
        super();
        println("Day " + getDay() + " Q1: " + solveQ1());
        println("Day " + getDay() + " Q2: " + solveQ2());
    }

    Object solveQ2() {
        var input = getInputLines().stream().map(s -> s.split(" => ")).toList();

        Map<String, String> map = new HashMap<>();
        for (String[] s : input) {
            map.putIfAbsent(s[1], s[0]);
        }


        int count = 0;
        while (map.keySet().stream().filter(s -> !s.equals("HF") && !s.equals("NAl")).anyMatch(s -> str.contains(s))) {
            for (String s : map.keySet()) {
                if (str.contains(s)) {
                    int index = str.indexOf(s);
                    var sub = map.get(s);
                    str = str.substring(0, index) + sub + str.substring(index + s.length());
                    count++;
                }
            }
        }
        replacements.add(str);
        while (!replacements.contains("e")) {

            var tmp = new HashSet<>(replacements);
            replacements.clear();
            tmp.parallelStream().forEach(s -> count2(s, s.length() - 2, map));
            count++;
        }

        return count;
    }

    void count2(String in, int index, Map<String, String> map) {
        if (index >= in.length()) return;
        for (String s : map.keySet()) {
            if (in.indexOf(s, index) == index) {
                var r = map.get(s);
                var newStr = in.substring(0, index) + r + in.substring(index + s.length());
                if (newStr.length() < str.length() + 10) {
                    replacements.add(newStr);
                }
            }
        }
        if (index > in.length() - 10) {
            count2(in, index - 1, map);
        }
    }

    Object solveQ1() {
        var input = getInputLines().stream().map(s -> s.split(" => ")).toList();

        Map<String, List<String>> map = new HashMap<>();
        for (String[] s : input) {
            map.putIfAbsent(s[0], new ArrayList<>());
            map.get(s[0]).add(s[1]);
        }
        count(str, 0, map);

        return replacements.size();
    }

    void count(String in, int index, Map<String, List<String>> map) {
        if (index >= in.length()) return;
        for (var e : map.entrySet()) {
            var s = e.getKey();
            if (in.indexOf(s, index) == index) {
                for (String r : e.getValue()) {
                    var newStr = in.substring(0, index) + r + in.substring(index + s.length());
                    replacements.add(newStr);
                }

            }
        }
        count(in, index + 1, map);
    }


    Set<String> replacements = new HashSet<>();


    @Override
    public String getDay() {
        return "19";
    }

}

