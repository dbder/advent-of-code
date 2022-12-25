package aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

class Main {

    record Folder(Set<Folder> folders, List<Integer> files) {
    }

    public static void main(String[] args) {

        var ints2021 = new int[3];
        input2021.lines().forEach(l -> {
            var split = l.split("\\s+");

            ints2021[0] += Integer.parseInt(split[3]);
            ints2021[1] += Integer.parseInt(split[6]);
            ints2021[2] += ints2021[0] + ints2021[1];

            System.out.println(Arrays.toString(ints2021));
        });

        System.out.println("----------");

        var ints2022 = new int[3];
        input2022.lines().forEach(l -> {
            var split = l.split("\\s+");

            ints2022[0] += Integer.parseInt(split[3]);
            ints2022[1] += Integer.parseInt(split[6]);
            ints2022[2] += ints2022[0] + ints2022[1];

            System.out.println(Arrays.toString(ints2022));
        });


        System.out.println("----------");
        System.out.println(Arrays.toString(ints2021));

        System.out.println(Arrays.toString(ints2022));

        System.out.println("p1: " + (ints2021[0] - ints2022[0]));
        System.out.println("p2: " + (ints2021[1] - ints2022[1]));
        System.out.println("p3: " + (ints2021[2] - ints2022[2]));

//        var root = new Folder(new HashSet<>(), new ArrayList<>());
//
//        var current = root;
//        current.files.add(10);
//        current.files.add(10);
//
//        var newFolder = new Folder(new HashSet<>(), new ArrayList<>());
//        current.folders.add(newFolder);
//
//        current = newFolder;
//        current.files.add(20);
//
//        System.out.println("---");
//        System.out.println(root);
//        System.out.println("---");
//        printSizes(root);


    }

    public static List<Integer> toIntList(String str) {
        List<String> arr = new ArrayList<>();
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(str);
        while (m.find()) {
            arr.add(m.group());
        }
        return arr.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static Integer[] toInts(String str) {
        return toIntList(str).stream()
                .toArray(Integer[]::new);
    }

    public static int toInt(String str) {
        return toInts(str)[0];
    }

    static String input2022 = """
             24   01:17:53  1448      0   01:33:44   1469      0
             23   02:13:32  3263      0   02:16:27   3058      0
             22   02:00:35  3202      0   08:34:18   3130      0
             21   00:22:59  2378      0   09:38:49   9630      0
             20   00:37:12   837      0   01:33:15   1936      0
             19   01:17:54   590      0   05:06:19   2131      0
             18   00:08:46  1125      0   00:54:19   1845      0
             17   01:51:00  2790      0   03:10:07   1928      0
             16   02:07:54  2009      0       >24h  12869      0
             15   00:16:38   384      0   01:30:31   2363      0
             14   00:26:57  1461      0   00:34:08   1472      0
             13   02:43:15  8544      0   02:54:21   7968      0
             12   00:39:34  2870      0   00:51:47   3150      0
             11   00:57:12  5474      0   01:36:57   4877      0
             10   00:09:52   973      0   00:31:44   2484      0
              9   00:36:20  5156      0   00:57:54   4087      0
              8   00:21:35  3549      0   01:13:25   7956      0
              7   00:51:53  5067      0   00:57:15   4360      0
              6   00:07:42  4374      0   00:09:14   4037      0
              5   00:14:42  1353      0   00:35:33   5428      0
            """;

    static String input2021 = """
            24   08:12:52   2984      0   08:35:52   2960      0
             23       >24h  10270      0       >24h   7554      0
             22   01:20:36   4235      0   13:55:19   6538      0
             21   01:15:32   5082      0   07:52:37   7088      0
             20   03:39:12   5438      0   03:40:22   5154      0
             19   02:38:04   1159      0       >24h   9018      0
             18   06:55:00   4951      0   07:13:51   4910      0
             17   01:54:33   5967      0   02:46:51   6306      0
             16   05:11:50   8085      0   05:59:40   7548      0
             15   00:53:45   3566      0   01:33:12   3111      0
             14   00:44:51   6925      0   01:46:21   5182      0
             13   02:09:29   9786      0   02:28:52   9589      0
             12   01:47:02   6932      0   04:00:44   9211      0
             11   00:46:46   4330      0   00:50:45   4214      0
             10   00:26:43   5754      0   00:49:59   6352      0
              9   00:18:27   4289      0   00:55:23   4531      0
              8   00:11:13   2452      0   00:50:39   1472      0
              7   01:23:14  14381      0   01:51:53  14810      0
              6   00:14:41   5043      0   00:15:26   1260      0
              5   00:35:24   4853      0   00:53:34   4553      0
            """;


    static int printSizes(Folder folder) {
        var size = folder.files.stream().mapToInt(Integer::intValue).sum();
        size += folder.folders.stream().mapToInt(Main::printSizes).sum();
        System.out.println("size: " + size);
        return size;
    }

}