package aoc;

public record Pos2D(long row, long col) {


    public Pos2D add(int[] mx) {
        return new Pos2D(row + mx[0], col + mx[1]);
    }

    public static Pos2D of(long row, long col) {
        return new Pos2D(row, col);
    }
}
