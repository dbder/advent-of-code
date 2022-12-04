package aoc;

import aoc.utils.CombinationUtil;
import aoc.utils.DSUtil;
import aoc.utils.GridUtil;
import aoc.utils.MapUtil;
import aoc.utils.MathUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * AdventUtil, convenience methods for Advent Questions.
 * ( often thing are put/refactored here after the question is finished )
 */
public abstract class AU implements
        CombinationUtil,
        GridUtil,
        MapUtil,
        MathUtil,
        DSUtil

{
    private static final Logger log = LogManager.getLogger(AU.class);


    protected static final Set<Character> VOWELS = new HashSet<>(List.of('a', 'e', 'i', 'u', 'o', 'A', 'E', 'I', 'O', 'U'));

    protected AU() {
        if (getDay() == null || getDay().length() != 2) {
            throw new AocException("getDay() must be implemented");
        }
        log.warn("-------------------------------------------------------------------");
        log.warn("                    Starting day : " + getDay() + "  " + this.getClass().toString().split("\\.")[1]);
        log.warn("-------------------------------------------------------------------");

        INPUT_PATH = INPUT_PATH_SOURCE.replaceAll("\\{}", this.getClass().toString().split("\\.")[1]);
    }

    protected static final Predicate<String> NOT_EMPTY = s -> !s.isEmpty();


    public static void println(Map<? extends Object, ? extends Collection<?>> map) {
        println("-vSTART printing mapv-");
        for (Map.Entry<?, ? extends Collection<?>> entry : map.entrySet()) {
            println(entry.getKey() + " : " + entry.getValue().stream().map(String::valueOf).collect(Collectors.joining(",", "[", "]")));
        }
        println("-^END printing map^-");
    }

    public static void pl(int[] arr) {
        println(Arrays.toString(arr));
    }

    public static void println(Object o) {
        if (o instanceof List l) {
            printlist(l);
        } else {
            log.info(o);
        }
    }

    public static void printlist(List<Object[]> o) {
        o.forEach(AU::println);
    }

    public static void println(Object[] o) {
        var str = Arrays.stream(o).map(Object::toString).collect(Collectors.joining(",", "[", "]"));
        log.info(str);
    }

    public static void println(String[] o) {
        var str = Arrays.stream(o).map(Object::toString).collect(Collectors.joining(",", "[", "]"));
        log.info(str);
    }

    public static void print(Object o) {
        System.out.print(o);
    }

    private static Stream<String> getInputAsStream(String path) {
        try {
            return Files.lines(Path.of(path));
        } catch (IOException e) {
            throw new AocException("could not read file. " + path);
        }
    }

    private static String getInputAsString(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            throw new AocException("could not read file." + path);
        }
    }

    protected static int[][] parse2DMatrixSingleDigit(List<String> in) {
        int[][] mx = new int[in.size()][in.get(0).length()];
        for (int r = 0; r < in.size(); r++) {
            for (int c = 0; c < in.get(0).length(); c++) {
                mx[r][c] = in.get(r).charAt(c) - '0';
            }
        }
        return mx;
    }


    public static V2 getP2D(int row, int col) {
        return new V2(row, col);
    }








    public static IntStream range(int start, int end) {
        return IntStream.range(start, end);
    }

    protected String getDay() {
        String day = this.getClass().toString().split("\\.")[2].substring(3);
        var dayint = toInt(day);
        if (dayint < 1 || dayint > 25) {
            throw new AocException("Day must be between 1 and 25");
        }
        return day;
    }

    public static final String INPUT_PATH_SOURCE = "src/main/java/aoc/{}/input/";
    public static String INPUT_PATH = "";

    protected String getInputLine() {
        return getInputAsString(INPUT_PATH + "day" + getDay());
    }

    protected List<String> getInputLines(String suffix) {
        return getInputAsStream(INPUT_PATH + "day" + getDay() + suffix).toList();
    }

    protected List<String> getInputLines() {
        return getInputAsStream(INPUT_PATH + "day" + getDay()).collect(Collectors.toList());
    }

    protected Stream<String> getInputStream() {
        return getInputAsStream(INPUT_PATH + "day" + getDay());
    }

    protected String getInputStringT() {
        System.out.println("WARNING ! using test input");
        return getInputAsString(INPUT_PATH + getDay() + "t");
    }

    protected List<String> getInputLinesT() {
        System.out.println("WARNING ! using test input");
        return getInputAsStream(INPUT_PATH + getDay() + "t").toList()
                ;
    }

    protected Stream<String> getInputStreamT() {
        System.out.println("WARNING ! using test input");
        return getInputAsStream(INPUT_PATH + getDay() + "t");
    }

    protected String[] trim(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].trim();
        }
        return arr;
    }



    public static final DigestUtils digestUtils = new DigestUtils("MD5");

    public String getMD5Hash(String input) {
        return digestUtils.digestAsHex(input);
    }

    public List<Integer> toIntList(String str) {
        List<String> arr = new ArrayList<>();
        Pattern p = Pattern.compile("-?\\d+");
        Matcher m = p.matcher(str);
        while (m.find()) {
            arr.add(m.group());
        }
        return arr.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public Integer[] toInts(String str) {
        return toIntList(str).stream()
                .toArray(Integer[]::new);
    }

    public static int toInt(String str) {
        return Integer.parseInt(str.trim());
    }
}