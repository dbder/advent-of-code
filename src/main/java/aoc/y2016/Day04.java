package aoc.y2016;

import aoc.AU;
import aoc.misc.AocException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day04 extends AU {

    @Override
    protected String getDay() {
        return "04";
    }


    public static void main(String[] args) {
        new Day04();
    }

    Day04() {
        println("Day " + getDay() + " Q2: " + solveQ2());
    }


    Object solveQ2() {
        var input = getInputLines();
        var result = 0;

        for (var l : input) {

            Pattern pat = Pattern.compile("([a-z-]+)-(\\d+)\\[([a-z]+)]");
            Matcher m = pat.matcher(l);
            m.find();
            String str = m.group(1).replace("-", "");
            String id = m.group(2);
            String checksum = m.group(3);
            if (id.length() != 3) {
                System.out.println("id = " + id);
                throw new AocException("Invalid id");
            }
            if (checksum.length() != 5) {
                throw new AocException("Invalid checksum");
            }

            int[] counts = new int[26];
            var ca = new char[str.length()];
            int index = 0;
            for (var c : str.toCharArray())
                {
                ca[index++] = (char)((((c - 'a') + Integer.parseInt(id)) % 26) + 'a');
                counts[((c - 'a') + Integer.parseInt(id)) % 26]++;
            }
            if (new String(ca).contains("northpole")) {
                return id;
            }


            record Tuple(int count, char c) implements Comparable<Tuple> {
                @Override
                public int compareTo(Tuple o) {
                    if (count == o.count) {
                        return c - o.c;
                    }
                    return o.count - count;
                }
            }

            List<Tuple> tuples = new ArrayList<>();

            for (var i = 0; i < counts.length; i++) {
                if (counts[i] > 0) {

                    tuples.add(new Tuple(counts[i], (char) i));
                }
            }
            tuples.sort(Tuple::compareTo);

            for (var i = 0; i < 5; i++) {
                if (tuples.get(i).c != checksum.charAt(i)) {
                    break;
                }
                if (i == 4) {
                    result += Integer.parseInt(id);
                }
            }

        }
        return result;
    }

}


