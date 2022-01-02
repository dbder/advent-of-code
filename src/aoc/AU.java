package aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * AdventUtil, convenience statics for Avent Questions.
 * ( often thing are put/refactored here after the question is finished )
 */
public class AU {


    protected AU() {

    }

    public static final int[][] moves8 = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}, {-1, -1}, {1, 1}, {1, -1}, {-1, 1}};
    public static final int[][] moves4 = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public static boolean inRange(int row, int col, int[][] mx) {
        int rows = mx.length;
        int cols = mx[0].length;
        return row >= 0 && col >= 0 && row < rows && col < cols;
    }


    public static void print(Map<? extends Object, ? extends Collection> map) {
        System.out.println("-vSTART printing mapv-");
        for (Map.Entry<?, ? extends Collection> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue().stream().collect(Collectors.joining(",", "[", "]")));
        }
        System.out.println("-^END printing map^-");
    }

    public static void pl(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static void print(Object o) {
        System.out.println(o);
    }

    public static Stream<String> getInputAsStream(String path) {
        try {
            return Files.lines(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException("could not read file.");
        }
    }
}
