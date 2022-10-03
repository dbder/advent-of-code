package aoc.y2015;

import aoc.AU;
import aoc.AocException;
import aoc.Position2D;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;

public class Day04 extends AU {

    public static void main(String[] args) {
        var input = getInputAsString("src/aoc/y2015/input/day03");
        solveQ1(input);
        solveQ2(input);
    }

    static void solveQ1(String input) {
        for (int x = 1; x < 10000000; x++) {
            if (getMd5("ckczppom" + x).startsWith("00000")) {
                println("Day 03 Q1: " + x);
                break;
            }
        }
    }


    static void solveQ2(String input) {
        for (int x = 1; x < 10000000; x++) {
            if (getMd5("ckczppom" + x).startsWith("000000")) {
                println("Day 03 Q2: " + x);
                break;
            }
        }
    }

    public static String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}