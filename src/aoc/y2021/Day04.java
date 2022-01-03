package aoc.y2021;

import aoc.AU;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day04 extends AU {

    public static void main(String[] args) {

        var input = getInputAsStream("src/aoc/y2021/input/day04")
                .toList();

        var picks = Arrays.stream(input.get(0).split(","))
                .map(Integer::parseInt)
                .toList();

        var cards = parseCards(input);

        solveQ1(picks, cards);
        solveQ2(picks, cards);

    }

    static void solveQ2(List<Integer> picks, List<int[][]> cards) {
        for (Integer pick : picks) {
            var toRemove = new ArrayList<int[][]>();
            for (int[][] card : cards) {
                if (isBingo(card, pick)) {
                    toRemove.add(card);
                }
            }
            if (cards.size() == 1 && toRemove.size() == 1) {
                print("Q2: " + getScore(cards.get(0), pick));
            }
            cards.removeAll(toRemove);
        }
    }

    static void solveQ1(List<Integer> picks, List<int[][]> cards) {
        for (Integer pick : picks) {
            for (int[][] card : cards) {
                if (isBingo(card, pick)) {
                    print("Q1: " + getScore(card, pick));
                    return;
                }
            }
        }
    }


    static List<int[][]> parseCards(List<String> input) {
        var cards = new ArrayList<int[][]>();
        int[][] card = new int[5][];
        int row = 0;

        for (int x = 2; x < input.size(); x++) {
            if (input.get(x).isBlank()) {
                continue;
            }
            card[row] = Arrays.stream(input.get(x).strip().split("[ ]+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            row++;
            if (row == 5) {
                cards.add(card);
                card = new int[5][];
                row = 0;
            }
        }
        return cards;
    }

    /**
     * Removes number if present and returns 'true' if bingo
     */
    static boolean isBingo(int[][] card, int nr) {

        for (int r = 0; r < card.length; r++) {
            for (int c = 0; c < card[0].length; c++) {
                if (card[r][c] == nr) {
                    card[r][c] = -1;
                    return isBingo(card, r, c);
                }
            }
        }
        return false;
    }


    static boolean isBingo(int[][] card, int r, int c) {
        return isHorizontalBingo(card, r) || isVerticalBingo(card, c);
    }

    static boolean isHorizontalBingo(int[][] card, int r) {
        for (var col : card[r]) {
            if (col != -1) {
                return false;
            }
        }
        return true;
    }

    static boolean isVerticalBingo(int[][] card, int c) {
        for (var row : card) {
            if (row[c] != -1) {
                return false;
            }
        }
        return true;
    }

    static int getScore(int[][] card, int number) {
        int score = 0;
        for (int[] ints : card) {
            for (int c = 0; c < card[0].length; c++) {
                if (ints[c] != -1) {
                    score += ints[c];
                }
            }
        }
        return score * number;
    }

}

