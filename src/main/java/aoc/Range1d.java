package aoc;

public record Range1d(int start, int end) {

    public Range1d {
        if (start > end) {
            var tmp = start;
            start = end;
            end = tmp;
        }
    }

    public static Range1d of(int start, int end) {
        return new Range1d(start, end);
    }

    public boolean overlaps(Range1d other) {
        return start <= other.end && end >= other.start;
    }

    public boolean contains(Range1d other) {
        return start <= other.start && end >= other.end;
    }

}
