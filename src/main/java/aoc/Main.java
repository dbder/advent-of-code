package aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        var l = Files.lines(Path.of("./aoc_2022_day05_large_input-2.txt"));
        int[] c = new int[1];
        l.forEach(i -> System.out.println(++c[0] + " " + i));
//        l.takeWhile(i -> !i.isBlank()).map(Main::letter).forEach(i -> System.out.println(++c[0] + " " + Arrays.toString(i)));

        int[] tops = new int[9];
        int[][] bars = new int[1499995 *3][9];


//        System.out.println(l);
    }

    private static char[] letter(String str) {
        char[] chars = new char[9];
//        int i = 0;
        for (int i = 0, j = 1; i <9; i++, j += 4) {
            chars[i] = str.charAt(j);
        }

        return chars;
    }
}
