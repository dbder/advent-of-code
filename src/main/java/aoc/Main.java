package aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        List<String> input = new ArrayList<>(100000);
        List<String> calcs = new ArrayList<>(100000);
        boolean[] p1 = new boolean[1];
        Files.lines(Path.of("./aoc_2022_day05_large_input-2.txt")).forEach(s -> {
            if (s.isBlank()) p1[0] = true;

            if (p1[0]) {
                System.out.println(s);
                var spl = s.split(" ");

            } else {
                input.add(s);
            }
         });

        long time = System.currentTimeMillis();

        int[] c = new int[1];
//        l.forEach(i -> System.out.println(++c[0] + " " + i));
//        l.takeWhile(i -> !i.isBlank()).map(Main::letter).forEach(i -> System.out.println(++c[0] + " " + Arrays.toString(i)));

        int[] tops = new int[9];
        int[][] bars = new int[1499995 * 3][9];


        System.out.println("time : " + (System.currentTimeMillis() - time));
    }

    private static char[] letter(String str) {
        char[] chars = new char[9];
//        int i = 0;
        for (int i = 0, j = 1; i < 9; i++, j += 4) {
            chars[i] = str.charAt(j);
        }

        return chars;
    }
}
