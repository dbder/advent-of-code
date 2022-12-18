package aoc.misc;

import java.util.List;

public record V3(int x, int y, int z) {

    public V3 up() { return new V3(x, y + 1, z);}
    public V3 down() { return new V3(x, y - 1, z);}
    public V3 left() { return new V3(x - 1, y, z);}
    public V3 right() { return new V3(x + 1, y, z);}
    public V3 front() { return new V3(x, y, z - 1);}
    public V3 back() { return new V3(x, y, z + 1);}

    public List<V3> getNeighbours6() {
        return List.of(up(), down(), left(), right(), front(), back());
    }


    public boolean contains(V3 other) {return x > other.x && y > other.y && z > other.z;}
    public static V3 of(int x, int y, int z) {return new V3(x, y, z);}
    public static V3 of (int[] ints) {return new V3(ints[0], ints[1], ints[2]);}
    public static V3 of (Integer[] ints) {return new V3(ints[0], ints[1], ints[2]);}

}

