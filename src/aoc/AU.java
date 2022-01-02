package aoc;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * AdventUtil, convenience statics for Avent Questions.
 * ( often thing are put/refactored here after the question is finished )
 */
public class AU {

    public static final int[][] moves8 = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}, {-1, -1}, {1, 1}, {1, -1}, {-1, 1}};
    public static final int[][] moves4 = {{0, -1}, {0, 1}, {1, 0}, {-1, 0}};

    public static boolean inRange(int row, int col, int[][] mx) {
        int rows = mx.length;
        int cols = mx[0].length;
        if (row < 0 || col < 0 || row >= rows || col >= cols) return false;
        return true;
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

}
