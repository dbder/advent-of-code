package aoc.y2015;

import aoc.AU;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


public class Day12 extends AU {
    private static String day = "12";

    static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {

        var input = getInputAsString("src/aoc/y2015/input/day" + day);

        solveQ1(input);
        solveQ2(input);

    }

    static void solveQ2(String input) throws IOException {
        var json = mapper.readTree(input.getBytes(StandardCharsets.UTF_8));
        println("Day " + day +" Q2: " + getValue(json));
    }

    static int getValue(JsonNode node) {
        boolean[] found = new boolean[1];
        if (!node.isArray()) {
            node.elements().forEachRemaining(e -> {
                if ("red".equals(e.asText())) found[0] = true;
            });
            if (found[0]) return 0;
        }
        final int[] sum = {0};

        node.elements().forEachRemaining(e -> {
            try {
                var i = Integer.parseInt(e.asText());
                sum[0] += i;
            } catch (Exception ex) {
                sum[0] += getValue(e);
            }
        });
        return sum[0];
    }

    static void solveQ1(String input) {
        var result = 0;
        var split = input.split("[,:\\]}\\[]");
        for (var s : split) {
            try {
                if (s.matches(".*\\d+.*")) {
                    result += Integer.parseInt(s);
                }
            } catch (NumberFormatException ignored) {
                System.out.println("catch: " + s);
            }
        }
        println("Day " + day + " Q1: " + result);
    }
}

