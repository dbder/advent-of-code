package aoc;

public record V1(int start, int end) {

    public V1{
        if (start > end) {
            var tmp = start;
            start = end;
            end = tmp;
        }
    }

    public static V1 of(int start, int end) {
        return new V1(start, end);
    }

    public boolean overlaps(V1 other) {
        return start <= other.end && end >= other.start;
    }

    public boolean contains(V1 other) {
        return start <= other.start && end >= other.end;
    }

}
