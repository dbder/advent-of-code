package aoc;

public record Position2D(long row, long col) {


    public Position2D add(int[] mx) {
        return new Position2D(row + mx[0], col + mx[1]);
    }
}
