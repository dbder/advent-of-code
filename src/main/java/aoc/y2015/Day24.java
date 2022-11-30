package aoc.y2015;

import aoc.AU;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class Day24 extends AU {

    @Override
    protected String getDay() {
        return "24";
    }


    public static void main(String[] args) {
        new Day24();
    }

    Day24() {
        boolean cached = true;
        if (cached) {
            println("Warning: cached mode");
        }
        println("Day " + getDay() + " Q1: " + (cached ? 11846773891L : solveQ1()));
        println("Day " + getDay() + " Q2: " + (cached ? 80393059 : solveQ1()));
    }

    Object solveQ2() {
        var input = getInputLines().stream().map(Integer::parseInt)
                .toList();

        var arrs = powerSet(input).filter(a -> a.stream().mapToInt(Integer::intValue).sum() == 390).toList();
        record P(List<Integer> list, BigInteger eq, int size) {
        }
        var possible = arrs.stream()
                .map(a -> new P(a, a.stream().map(BigInteger::valueOf).reduce(BigInteger.ONE, BigInteger::multiply), a.size()))
                .sorted((a, b) -> a.eq.compareTo(b.eq)).toList();

        return possible.stream().findFirst().orElse(new P(null, null, 0)).eq;
    }

    long minSize = Integer.MAX_VALUE;
    BigInteger minQE = BigInteger.valueOf(Long.MAX_VALUE).multiply(BigInteger.valueOf(Long.MAX_VALUE));

    Object solveQ1() {
        var input = getInputLines().stream().map(Integer::parseInt)
                .sorted((a, b) -> b - a)
                .mapToInt(i -> i)
                .toArray();
        calc(0, input, new int[0], new int[0], new int[0]);

        return minQE;

    }

    void calc(int index, int[] input, int[] g1, int[] g2, int[] g3) {
        var c1 = Arrays.stream(g1).sum();
        var c2 = Arrays.stream(g2).sum();
        var c3 = Arrays.stream(g3).sum();
        if (c1 > 520) return;
        if (c2 > 520) return;
        if (c3 > 520) return;

        var size = min(g1.length, g2.length, g3.length);
        if (size > minSize) {
            return;
        }

        if (index >= input.length) {
            if (c1 != c2 || c2 != c3) {
                return;
            }
            process(g1, size);
            process(g2, size);
            process(g3, size);
        } else {
            var val = input[index];
            var arr = Arrays.copyOf(g1, g1.length + 1);
            arr[arr.length - 1] = val;

            calc(index + 1, input, arr, g2, g3);
            arr = Arrays.copyOf(g2, g2.length + 1);
            arr[arr.length - 1] = val;
            calc(index + 1, input, g1, arr, g3);

            arr = Arrays.copyOf(g3, g3.length + 1);
            arr[arr.length - 1] = val;
            calc(index + 1, input, g1, g2, arr);
        }
    }

    void process(int[] input, int min) {
        if (input.length > min) {
            return;
        }
        BigInteger beq = BigInteger.ONE;
        if (input.length > minSize) {
            return;
        }

        for (var i : input) {
            beq = beq.multiply(BigInteger.valueOf(i));
        }

        if (input.length < minSize) {
            minSize = input.length;
            minQE = beq;
        } else if (input.length == minSize) {
            minQE = minQE.min(beq);
        }
    }
}

