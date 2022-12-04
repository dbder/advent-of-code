package aoc;

import java.util.Arrays;
import java.util.List;

public record Pos2D(int row, int col) {


    public Pos2D add(int[] mx) {
        return new Pos2D(row + mx[0], col + mx[1]);
    }
    public Pos2D add(Pos2D mx) {
        return new Pos2D(row + mx.row, col + mx.col);
    }
    public Pos2D add(int row, int col) {
        return new Pos2D(this.row + row, this.col + col);
    }

    public static Pos2D of(int row, int col) {
        return new Pos2D(row, col);
    }

    public static Pos2D of(int[] arr) {
        return Pos2D.of(arr[0], arr[1]);
    }

    public boolean isIN(int[][] mx) {
        int rows = mx.length;
        int cols = mx[0].length;
        return row >= 0 && col >= 0 && row < rows && col < cols;
    }

    public boolean isIN(char[][] mx) {
        int rows = mx.length;
        int cols = mx[0].length;
        return row >= 0 && col >= 0 && row < rows && col < cols;
    }

    public Pos2D up() {
        return new Pos2D(row + 1, col);
    }

    public Pos2D down() {
        return new Pos2D(row - 1, col);
    }

    public Pos2D left() {
        return new Pos2D(row, col - 1);
    }

    public Pos2D right() {
        return new Pos2D(row, col + 1);
    }

    public Pos2D up(int n) {
        return new Pos2D(row + n, col);
    }

    public Pos2D down(int n) {
        return new Pos2D(row - n, col);
    }

    public Pos2D left(int n) {
        return new Pos2D(row, col - n);
    }

    public Pos2D right(int n) {
        return new Pos2D(row, col + n);
    }

    public List<Pos2D> tps() {
        return Arrays.asList(up(), down(), left(), right());
    }

    public Pos2D move(char dir) {
        dir = Character.toUpperCase(dir);
        return switch (dir) {
            case 'U' -> up();
            case 'D' -> down();
            case 'L' -> left();
            case 'R' -> right();
            default -> throw new AocException("Invalid direction: " + dir);
        };
    }

    public Pos2D move(char dir, int n) {
        dir = Character.toUpperCase(dir);
        return switch (dir) {
            case 'U' -> up(n);
            case 'D' -> down(n);
            case 'L' -> left(n);
            case 'R' -> right(n);
            default -> throw new AocException("Invalid direction: " + dir);
        };
    }

    public Pos2D move(String dir) {
        return move(dir.charAt(0));
    }

    public Pos2D move(String dir, int n) {
        return move(dir.charAt(0), n);
    }



    public int manhattan() {
        return Math.abs(row) + Math.abs(col);
    }
}
