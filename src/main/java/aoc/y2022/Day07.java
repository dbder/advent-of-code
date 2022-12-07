package aoc.y2022;

import aoc.AU;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

public class Day07 extends AU {

    public static void main(String[] args) {


        if (!testData1.get().isEmpty()) new Day07(testData1.get(), true);
        if (!testData2.get().isEmpty()) new Day07(testData2.get(), true);
        new Day07(null, true);
        if (true) return;
        if (!testData1.get().isEmpty()) new Day07(testData1.get(), false);
        if (!testData2.get().isEmpty()) new Day07(testData2.get(), false);
        new Day07(null, false);
    }

//    static Supplier<List<String>> testData1 = () -> """
//            - / (dir)
//              - a (dir)
//                - e (dir)
//                  - i (file, size=584)
//                - f (file, size=29116)
//                - g (file, size=2557)
//                - h.lst (file, size=62596)
//              - b.txt (file, size=14848514)
//              - c.dat (file, size=8504156)
//              - d (dir)
//                - j (file, size=4060174)
//                - d.log (file, size=8033020)
//                - d.ext (file, size=5626152)
//                - k (file, size=7214296)
//            """.lines().collect(toList());


    static Supplier<List<String>> testData2 = () -> """
            """.lines().collect(toList());

    Day07(List<String> testData, boolean q1) {
        switch ((q1 ? "q1" : "q2") + "-" + (testData == null ? "real" : "test")) {
            case "q1-test" -> println("Test Q1: " + solveQ1(testData));
            case "q1-real" -> println("Real Q1: " + solveQ1(getInputLines()));
            case "q2-test" -> println("Test Q2: " + solveQ2(testData));
            case "q2-real" -> println("Real Q2: " + solveQ2(getInputLines()));
        }
    }


    Object solveQ2(List<String> input) {
        var result = 0L;
        //DAY2 !!
        return result;
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
        var result = 0L;
        var map = new HashMap<String, Dir>();
        var current = new Dir("", null, new HashSet<>(), new HashMap<>());
        map.put("", current);

        String mode = "";
        for (var line : input) {
            System.out.println(line);
            var spl = line.split(" ");
            if (line.startsWith("$")) {
                switch (spl[1]) {
                    case "cd" -> {

                        if (spl[2].equals("..")) {
                            System.out.println("parent");
                            current = current.parent;
                        } else {
                            if (!spl[2].equals("/")) {
                                System.out.println("child");
                                System.out.println(current.children);
                                current = current.children.get(spl[2]);
                            }
                        }
                    }
                    case "ls" -> mode = "ls";
                }
            } else if (mode.equals("ls")) {
                if (spl[0].equals("dir")) {
                    current.children.put(spl[1], Dir.of(spl[1], current));
                } else {
                    var size = toInt(spl[0]);
                    current.files.add(new File(spl[1], size));
                }
            }
        }

        var list = new ArrayList<Integer>();
        procesLS(map.get(""), list);

        System.out.println(list);

        var max = 70000000 - max(list);
        System.out.println(max);
        int min = 30000000 - 26401404;

        Collections.sort(list);
        return list.stream().filter(i -> i >= min).findFirst().orElse(-1);

//        return list.stream().filter(i -> i < 100000).mapToInt(i -> i).sum();
    }

    int procesLS(Dir dir, List<Integer> counts) {
        int size = dir.files.stream().mapToInt(f -> f.size).sum();
        int count = 0;
        for (var child : dir.children.entrySet()) {
            count += procesLS(child.getValue(), counts);
        }

        counts.add(size + count);
        return size + count;
    }

    record File(String name, int size) {
    }

    record Dir(String name, Dir parent, Set<File> files, Map<String, Dir> children) {
        static Dir of(String name, Dir parent) {
            return new Dir(name,parent, new HashSet<>(), new HashMap<>());
        }

        @Override
        public String toString() {
            return name;
        }
    }




}

