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
 * AdventUtil, convenience methods for Advent Questions.
 * ( often thing are put/refactored here after the question is finished )
 */
public class AU {


    protected AU() {

    }

    protected static final int[][] moves8 = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}, {-1, -1}, {1, 1}, {1, -1}, {-1, 1}};
    protected static final int[][] moves4 = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public static boolean inRange(int row, int col, int[][] mx) {
        int rows = mx.length;
        int cols = mx[0].length;
        return row >= 0 && col >= 0 && row < rows && col < cols;
    }


    public static void print(Map<? extends Object, ? extends Collection<?>> map) {
        print("-vSTART printing mapv-");
        for (Map.Entry<?, ? extends Collection<?>> entry : map.entrySet()) {
            print(entry.getKey() + " : " + entry.getValue().stream().map(String::valueOf).collect(Collectors.joining(",", "[", "]")));
        }
        print("-^END printing map^-");
    }

    public static void pl(int[] arr) {
        print(Arrays.toString(arr));
    }

    public static void print(Object o) {
        System.out.println(o);
    }

    public static Stream<String> getInputAsStream(String path) {
        try {
            return Files.lines(Path.of(path));
        } catch (IOException e) {
            throw new AocException("could not read file.");
        }
    }

    public static String getInputAsString(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            throw new AocException("could not read file.");
        }
    }
}
