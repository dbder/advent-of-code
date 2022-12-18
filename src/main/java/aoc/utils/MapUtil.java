package aoc.utils;

import aoc.misc.AocException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;



public interface MapUtil {

    default Map<Integer, List<String>> mapCountT(String[] arr) {
        return mapCountT(Arrays.asList(arr));
    }
    default <T> Map<Integer, List<T>> mapCountT(List<T> str) {
        var map = mapTCount(str);
        Map<Integer, List<T>> result = new HashMap<>();

        for (var entry : map.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();
            var list = result.computeIfAbsent(value, i -> new ArrayList<>());
            list.add(key);
        }

        return result;
    }


    default Map<Integer, List<String>> mapCountChars(String str) {
        var map = mapCharCount(str);
        Map<Integer, List<String>> result = new HashMap<>();

        for (var entry : map.entrySet()) {
            var key = entry.getKey();
            var value = Math.toIntExact(entry.getValue());
            var list = result.computeIfAbsent(value, i -> new ArrayList<>());
            list.add(key);
        }

        return result;
    }

    default Map<String, Integer> mapStringCount(String[] arr) {
        return mapTCount(Arrays.asList(arr));
    }

    default <T> Map<T, Integer> mapTCount(List<T> str) {
        return str.stream().collect(groupingBy(s -> s, Collectors.counting()))
                .entrySet()
                .stream()
                .map(e -> Map.entry(e.getKey(), Math.toIntExact(e.getValue())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }



    /**
     * splits to chars and counts them as strings
     */
    default Map<String, Integer> mapCharCount(String str) {
        return Arrays
                .asList(str.split(""))
                .stream()
                .collect(groupingBy(string -> string, Collectors.counting()))
                .entrySet()
                .stream()
                .map(e -> Map.entry(e.getKey(), Math.toIntExact(e.getValue())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }



    default int toInt(String str, int radix) {
        return Integer.parseInt(str.trim(), radix);
    }


    default int getNextStepTo(Map<String, Set<String>> map, String from, String to) {

        record Node(String name, Node parent) {
        }
        if (from.equals(to)) return 0;
        if (map.get(from).contains(to)) return 1;
        var visited = new HashSet<String>();
        var level = new ArrayList<Node>();
        var current = new Node(from, null);
        var root = current;
        visited.add(from);
        level.add(current);
        int count = 0;
        while (!level.isEmpty()) {
            count++;
            var next = new ArrayList<Node>();
            for (var node : level) {
                var links = map.get(node.name);
                for (var link : links) {
                    if (visited.contains(link)) continue;
                    visited.add(link);
                    if (link.equals(to)) {
                        return count;
                    }
                    next.add(new Node(link, node));
                }
            }
            level = next;
        }
        throw new AocException("No path found");
    }


}
