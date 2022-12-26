package aoc.y2022;

import aoc.AU;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class Day07 extends AU {

    public static void main(String[] args) {


        if (!testData1.get().isEmpty()) new Day07(testData1.get(), true);
        new Day07(null, true);
        if (!testData1.get().isEmpty()) new Day07(testData1.get(), false);
        new Day07(null, false);
    }

    Day07(List<String> testData, boolean q1) {
        switch ((q1 ? "q1" : "q2") + "-" + (testData == null ? "real" : "test")) {
            case "q1-test" -> println("Test Q1: " + solveQ1(testData));
            case "q1-real" -> println("Real Q1: " + solveQ1(getInputLines()));
            case "q2-test" -> println("Test Q2: " + solveQ2(testData));
            case "q2-real" -> println("Real Q2: " + solveQ2(getInputLines()));
        }
    }


    Object solveQ2(List<String> input) {

        Dir root = buildTree(input);


        var list = new ArrayList<Integer>();
        sumAllIntoMemo(root, list);


        int min = 30000000 - 26401404;
        Collections.sort(list);
        return list.stream().filter(i -> i >= min).findFirst().orElse(-1);
    }

    Dir buildTree(List<String> input) {

        var current = new Dir("", null, new ArrayList<>(), new HashMap<>());
        var root = current;

        for (var line : input) {
            var spl = line.split(" ");

            // ls does not really do anything, so we can skip it
            if (line.equals("$ ls")) continue;

            if (line.equals("$ cd /")) { // cd /  goes to root
                current = root;
            } else if (line.equals("$ cd ..")) { // cd .. goes to parent
                current = current.parent;
            } else if (line.startsWith("$ cd ")) { // if there is still a cd, we need to go to a child
                current = current.children.get(spl[2]);
            } else if (line.startsWith("dir")) { // if there is a dir, we need to add a child to the current dir
                current.children.put(spl[1], Dir.of(spl[1], current));
            } else { // only option left is : a file. we only need it's size
                current.files.add(Integer.parseInt(spl[0]));
            }

        }
        return root;
    }

    static Supplier<List<String>> testData1 = () -> """
            $ cd /
            $ ls
            dir a
            14848514 b.txt
            8504156 c.dat
            dir d
            $ cd a
            $ ls
            dir e
            29116 f
            2557 g
            62596 h.lst
            $ cd e
            $ ls
            584 i
            $ cd ..
            $ cd ..
            $ cd d
            $ ls
            4060174 j
            8033020 d.log
            5626152 d.ext
            7214296 k
            """.lines().collect(toList());

    Object solveQ1(List<String> input) {

        Dir root = buildTree(input);

        var list = new ArrayList<Integer>();
        sumAllIntoMemo(root, list);
        return list.stream().filter(i -> i < 100000).mapToInt(i -> i).sum();

    }

    int sumAllIntoMemo(Dir dir, List<Integer> countMemo) {

        // sum up all files
        int size = dir.files.stream().mapToInt(f -> f).sum();

        // add all child Dir filetotals
        for (var child : dir.children.entrySet()) {
            size += sumAllIntoMemo(child.getValue(), countMemo);
        }

        // add to the memo
        countMemo.add(size);

        //return the size so that the parent can add it to it's total
        return size;
    }

    record Dir(String name, Dir parent, List<Integer> files, Map<String, Dir> children) {
        static Dir of(String name, Dir parent) {
            return new Dir(name, parent, new ArrayList<>(), new HashMap<>());
        }

        @Override
        public String toString() {
            return name;
        }
    }
}

