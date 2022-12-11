package aoc.misc;

import java.util.Arrays;
import java.util.List;

public record V2(int row, int col) {


    public V2 add(int[] mx) {
        return new V2(row + mx[0], col + mx[1]);
    }

    public V2 add(V2 mx) {
        return new V2(row + mx.row, col + mx.col);
    }

    public V2 add(int row, int col) {
        return new V2(this.row + row, this.col + col);
    }

    public static V2 of(int row, int col) {
        return new V2(row, col);
    }

    public static V2 of(int[] arr) {
        return V2.of(arr[0], arr[1]);
    }

    public boolean isIN(int[][] mx) {
        int rows = mx.length;
        int cols = mx[0].length;
        return row >= 0 && col >= 0 && row < rows && col < cols;
    }
    public boolean isIN(int[][][] mx) {
        int rows = mx.length;
        int cols = mx[0].length;
        return row >= 0 && col >= 0 && row < rows && col < cols;
    }

    public boolean isIN(char[][] mx) {
        int rows = mx.length;
        int cols = mx[0].length;
        return row >= 0 && col >= 0 && row < rows && col < cols;
    }

    public V2 up() {
        return new V2(row + 1, col);
    }

    public V2 down() {
        return new V2(row - 1, col);
    }

    public V2 left() {
        return new V2(row, col - 1);
    }

    public V2 right() {
        return new V2(row, col + 1);
    }

    public V2 up(int n) {
        return new V2(row + n, col);
    }

    public V2 down(int n) {
        return new V2(row - n, col);
    }

    public V2 left(int n) {
        return new V2(row, col - n);
    }

    public V2 right(int n) {
        return new V2(row, col + n);
    }

    public List<V2> tps() {
        return Arrays.asList(up(), down(), left(), right());
    }

    public V2 move(char dir) {
        dir = Character.toUpperCase(dir);
        return switch (dir) {
            case 'U' -> up();
            case 'D' -> down();
            case 'L' -> left();
            case 'R' -> right();
            default -> throw new AocException("Invalid direction: " + dir);
        };
    }

    public V2 move(char dir, int n) {
        dir = Character.toUpperCase(dir);
        return switch (dir) {
            case 'U' -> up(n);
            case 'D' -> down(n);
            case 'L' -> left(n);
            case 'R' -> right(n);
            default -> throw new AocException("Invalid direction: " + dir);
        };
    }

    public V2 move(String dir) {
        return move(dir.charAt(0));
    }

    public V2 move(int dir, int n) {
        return switch (dir) {
            case 0 -> up(n);
            case 1 -> right(n);
            case 2 -> down(n);
            case 3 -> left(n);
            default -> throw new AocException("Invalid direction: " + dir);
        };
    }

    public V2 move(int dir) {
        return switch (dir) {
            case 0 -> up();
            case 1 -> right();
            case 2 -> down();
            case 3 -> left();
            default -> throw new AocException("Invalid direction: " + dir);
        };
    }

    public V2 move(String dir, int n) {
        return move(dir.charAt(0), n);
    }


    public int manhattan() {
        return Math.abs(row) + Math.abs(col);
    }

    public int manhattan(V2 v) {
        return Math.abs(row - v.row) + Math.abs(col - v.col);
    }

    public V2 diff(V2 other) {
        return new V2(row - other.row, col - other.col);
    }

    public V2 diffAbs(V2 other) {
        return new V2(Math.abs(row - other.row), Math.abs(col - other.col));
    }

}
