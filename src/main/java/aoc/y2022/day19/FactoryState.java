package aoc.y2022.day19;

import java.util.ArrayList;
import java.util.List;

public record FactoryState(int ore, int clay, int obsidian, int geode, int oreRobot, int clayRobot, int obsidianRobot,
                           int geodeRobot, int minute) {

    static FactoryState zeroFactoryState = new FactoryState(0, 0, 0, 0, 0, 0, 0, 0, 0);

    public FactoryState buildOreRobot(Blueprint bp) {
        if (oreRobot == 0) return zeroFactoryState;
        if (oreRobot >= bp.maxOreNeeded()) return zeroFactoryState;

        var current = this;
        while (current.ore < bp.ore()[0]) {
            current = current.nextMinute(bp);
        }

        current = current.nextMinute(bp);
        return new FactoryState(current.ore - bp.ore()[0], current.clay, current.obsidian, current.geode, current.oreRobot + 1, current.clayRobot, current.obsidianRobot, current.geodeRobot, current.minute);
    }

    public FactoryState buildClayRobot(Blueprint bp) {
        if (oreRobot == 0) return zeroFactoryState;
        if (clayRobot >= bp.maxClayNeeded()) return zeroFactoryState;

        var current = this;
        while (current.ore < bp.clay()[0]) {
            current = current.nextMinute(bp);
        }
        current = current.nextMinute(bp);
        return new FactoryState(current.ore - bp.clay()[0], current.clay, current.obsidian, current.geode, current.oreRobot, current.clayRobot + 1, current.obsidianRobot, current.geodeRobot, current.minute);
    }

    public FactoryState buildObsidianRobot(Blueprint bp) {
        if (oreRobot == 0) return zeroFactoryState;
        if (clayRobot == 0) return zeroFactoryState;
        if (obsidianRobot >= bp.maxObsidianNeeded()) return zeroFactoryState;

        var current = this;
        while (current.ore < bp.obsidian()[0] || current.clay < bp.obsidian()[1]) {
            current = current.nextMinute(bp);
        }
        current = current.nextMinute(bp);
        return new FactoryState(current.ore - bp.obsidian()[0], current.clay - bp.obsidian()[1], current.obsidian, current.geode, current.oreRobot, current.clayRobot, current.obsidianRobot + 1, current.geodeRobot, current.minute);
    }

    public FactoryState buildGeodeRobot(Blueprint bp) {
        if (oreRobot == 0) return zeroFactoryState;
        if (obsidianRobot == 0) return zeroFactoryState;

        var current = this;
        while (current.ore < bp.geode()[0] || current.obsidian < bp.geode()[2]) {
            current = current.nextMinute(bp);
        }

        current = current.nextMinute(bp);
        return new FactoryState(current.ore - bp.geode()[0], current.clay, current.obsidian - bp.geode()[2], current.geode, current.oreRobot, current.clayRobot, current.obsidianRobot, current.geodeRobot + 1, current.minute);
    }


    @Override
    public String toString() {
        return "State{" + "ore=" + ore + ", clay=" + clay + ", obsidian=" + obsidian + ", geode=" + geode + ", oreRobot=" + oreRobot + ", clayRobot=" + clayRobot + ", obsidianRobot=" + obsidianRobot + ", geodeRobot=" + geodeRobot + ", minute=" + minute + '}';
    }

    public FactoryState nextMinute(Blueprint bp) {
        var nextOre = ore + oreRobot;
        if (oreRobot >= bp.maxOreNeeded()) nextOre = Math.min(nextOre, bp.maxOreNeeded());

        var nextClay = clay + clayRobot;
        if (clayRobot >= bp.maxClayNeeded()) nextClay = Math.min(nextClay, bp.maxClayNeeded());

        var nextObsidian = obsidian + obsidianRobot;
        if (obsidianRobot >= bp.maxObsidianNeeded()) nextObsidian = Math.min(nextObsidian, bp.maxObsidianNeeded());

        return new FactoryState(nextOre, nextClay, nextObsidian, geode + geodeRobot, oreRobot, clayRobot, obsidianRobot, geodeRobot, minute + 1);
    }


    public static List<FactoryState> getPeaks(List<FactoryState> states, Blueprint bp) {
        List<FactoryState> peaks = new ArrayList<>();
        for (var state : states) {
            var isPeak = isPeak(state, peaks, bp);
            if (isPeak) {
                peaks = filter(peaks, state, bp);
                peaks.add(state);
            }
        }
        return peaks;
    }


    public static boolean isPeak(FactoryState cur, List<FactoryState> states, Blueprint bp) {
        for (var state : states) {
            if (state.canBuyGeodeBotEveryMinute(bp)) {
                if (!cur.canBuyGeodeBotEveryMinute(bp)) {
                    return cur.geode() > state.geode();
                }
            }
            if (state.ore() >= cur.ore() && state.clay() >= cur.clay && state.obsidian >= cur.obsidian && state.geode >= cur.geode
                    && state.oreRobot >= cur.oreRobot && state.clayRobot >= cur.clayRobot && state.obsidianRobot >= cur.obsidianRobot && state.geodeRobot >= cur.geodeRobot && state.minute >= cur.minute) {
                return false;
            }
        }
        return true;
    }

    private static List<FactoryState> filter(List<FactoryState> states, FactoryState peak, Blueprint bp) {
        List<FactoryState> filtered = new ArrayList<>();
        var canbuyGeodeBotEveryMinute = peak.canBuyGeodeBotEveryMinute(bp);
        for (var state : states) {
            if (canbuyGeodeBotEveryMinute) {
                if (state.geode() > peak.geode() || peak.minute < state.minute) {
                    filtered.add(state);
                }
            } else if (state.ore > peak.ore || state.clay > peak.clay || state.obsidian > peak.obsidian || state.geode > peak.geode ||
                    state.oreRobot > peak.oreRobot || state.clayRobot > peak.clayRobot || state.obsidianRobot > peak.obsidianRobot || state.geodeRobot > peak.geodeRobot && state.minute > peak.minute) {
                filtered.add(state);
            }
        }
        return filtered;
    }


    public static FactoryState start() {
        return new FactoryState(0, 0, 0, 0, 1, 0, 0, 0, 1);
    }

    public boolean canBuyGeodeBotEveryMinute(Blueprint bp) {
        return oreRobot >= bp.obsidian()[0] && obsidianRobot >= bp.obsidian()[2];
    }

}
