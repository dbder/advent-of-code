package aoc.y2015;

import aoc.AU;

import java.util.List;
import java.util.regex.Pattern;


public class Day08 extends AU {
    private static String day = "08";

    public static void main(String[] args) {

        var input = getInputAsStream("src/aoc/y2015/input/day" + day).toList();

        solveQ1(input);
        solveQ2(input);

    }

    static void solveQ2(List<String> input) {
        var total = 0;
        var totalAfter = 0;
        for (var line : input) {
            total += line.length();

            line = line.replaceAll("\\\\\\\\", "####");
            line = line.replaceAll("\\\\\"", "\\\\\\\\\\\\\"");
            line = line.replaceAll("\\\\x", "\\\\\\\\x");

            line = line.substring(0, line.length()-1) + "\\\"\"";
            line = "\"\\\"" + line.substring(1);
            totalAfter += line.length();
        }
        println("Day " + day + " Q2: " + (totalAfter - total));
    }

    static void solveQ1(List<String> input) {
        var total = 0;
        var totalAfter = 0;
        Pattern p = Pattern.compile("\\\\x[0-9a-f]{2}");
        for (var line : input) {
            total += line.length();
            line = line.substring(1, line.length() - 1);
            line = line.replace("\\\\", "#");
            line = line.replace("\\\"", "@");
            var m = p.matcher(line);
            while (m.find()) {
                var c1 = line.charAt(m.end() - 1);
                var c2 = line.charAt(m.end() - 2);
                if (isHexDigit(c1) && isHexDigit(c2)) {
                    char c = (char) Integer.parseInt(line.substring(m.start() + 2, m.end()), 16);
                    line = m.replaceFirst(String.valueOf(c));
                } else {
                    println("c1: " + c1 + " c2: " + c2);
                    println(line);
                    line = m.replaceFirst("----");
                }
                m = p.matcher(line);
            }
            totalAfter += line.length();
        }
        println("Day " + day + " Q1: " + (total - totalAfter));
    }

    static boolean isHexDigit(char c) {
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
    }
}
