package aoc.y2021;

import aoc.AU;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Day12 extends AU {

    @Override
    protected String getDay() {
        return "12";
    }

    private static final String START = "start";

    public static void main(String[] args) {

            new Day12();

    }

    Day12() {
        var input = getInputLines();

        solveQ1(input);
        solveQ2(input);

    }

    /**
     * After reviewing the available paths, you realize you might have time to visit a single small cave twice.
     * Given these new rules, how many paths through this cave system are there?
     */
    static void solveQ2(List<String> input) {
        var graph = getGraph(input);

        HashSet<String> sm = new HashSet<>();
        sm.add(START);

        count(graph.get(START), sm, new HashMap<>(), false, new ArrayList<>());

        Set<String> ps = new HashSet<>(paths);

        println("Day 12 Q1: " + ps.size());
    }

    /**
     * How many paths through this cave system are there that visit small caves at most once?
     */
    static void solveQ1(List<String> input) {
        var graph = getGraph(input);

        HashSet<String> sm = new HashSet<>();
        sm.add(START);

        count(graph.get(START), sm, new HashMap<>(), true, new ArrayList<>());

        Set<String> ps = new HashSet<>(paths);

        println("Day 12 Q1: " + ps.size());
    }

    private static Map<String, Node> getGraph(List<String> input) {
        Map<String, Node> nodes = new HashMap<>();
        for (String str : input) {
            String[] split = str.split("-");
            String n1 = split[0];
            String n2 = split[1];

            Node node1 = nodes.get(n1);
            Node node2 = nodes.get(n2);
            if (node1 == null) {
                node1 = new Node(n1);
                nodes.put(n1, node1);
            }
            if (node2 == null) {
                node2 = new Node(n2);
                nodes.put(n2, node2);
            }
            if (!node2.name.equals(START)) {
                node1.edges.add(node2);
            }
            if (!node1.name.equals(START)) {
                node2.edges.add(node1);
            }
        }
        return nodes;
    }

    static List<String> paths = new ArrayList<>();


    public static void count(Node node, Set<String> small, Map<String, Set<String>> toBig, boolean single, List<String> chars) {

        if (node.name.equals("end")) {
            paths.add(chars.stream().collect(Collectors.joining(",", "start,", "")));
            return;
        }
        for (Node n : node.edges) {
            if (small.contains(n.name)) {
                continue;
            }

            if (!n.bigcave) {
                final List<String> collect = new ArrayList<>(chars);

                collect.add(n.name);
                if (single) {
                    final Set<String> sm = new HashSet<>(small);
                    sm.add(n.name);
                    count(n, sm, toBig, true, collect);
                } else {
                    count(n, small, toBig, true, collect);
                    final Set<String> sm = new HashSet<>(small);
                    sm.add(n.name);
                    count(n, sm, toBig, true, collect);
                }

            } else {
                final Set<String> strings = toBig.get(node.name);
                if (strings != null && strings.contains(n.name) && !single) {
                    if (n.mvisit) {
                        continue;
                    } else {
                        n.mvisit = true;
                    }

                }

                Map<String, Set<String>> collect = toBig.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> new HashSet<>(e.getValue())));
                Set<String> strings1 = collect.get(node.name);
                if (strings1 == null) {
                    strings1 = new HashSet<>();
                    strings1.add(n.name);
                    collect.put(node.name, strings1);
                }

                final List<String> collect4 = new ArrayList<>(chars);
                collect4.add(n.name);

                count(n, small, collect, single, collect4);
            }
        }
    }


    static class Node {
        Set<Node> edges = new HashSet<>();
        final String name;

        boolean mvisit = false;

        final boolean bigcave;

        Node(final String name) {
            this.name = name;
            bigcave = Character.isUpperCase(name.charAt(0));

        }


        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            final Node node = (Node) o;
            return name.equals(node.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }


        @Override
        public String toString() {
            return name + "|" + edges.stream().map(e -> e.name).collect(Collectors.joining(","));
        }
    }


}

