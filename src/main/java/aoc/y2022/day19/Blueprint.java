package aoc.y2022.day19;

import java.util.Arrays;
import java.util.stream.IntStream;

public record Blueprint(int name, int[] ore, int[] clay, int[] obsidian, int[] geode) {

    int maxOreNeeded() {
        return IntStream.of(ore[0], clay[0], obsidian[0], geode[0]).max().orElse(0);
    }

    int maxClayNeeded() {
        return obsidian[1];
    }

    int maxObsidianNeeded() {
        return geode[2];
    }

    @Override
    public String toString() {
        return "Blueprint{" +
                "name=" + name +
                ", ore=" + Arrays.toString(ore) +
                ", clay=" + Arrays.toString(clay) +
                ", obsidian=" + Arrays.toString(obsidian) +
                ", geode=" + Arrays.toString(geode) +
                '}';


    }
}