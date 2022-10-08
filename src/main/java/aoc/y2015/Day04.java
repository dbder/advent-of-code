package aoc.y2015;

import aoc.AU;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Day04 extends AU {

    public static void main(String[] args) {
        new Day04();
    }

    Day04() {
        solveQ1();
        solveQ2();
    }

    static void solveQ1() {
        for (int x = 1; x < 10000000; x++) {
            if (getMd5("ckczppom" + x).startsWith("00000")) {
                println("Day 04 Q1: " + x);
                break;
            }
        }
    }


    static void solveQ2() {
        for (int x = 1; x < 10000000; x++) {
            if (getMd5("ckczppom" + x).startsWith("000000")) {
                println("Day 04 Q2: " + x);
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

    @Override
    public String getDay() {
        return "04";
    }
}