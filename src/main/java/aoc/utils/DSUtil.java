package aoc.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface DSUtil {


    default <T> List<T[]> chunkN(T[] arr, int n) {
        List<T[]> chunks = new ArrayList<>();
        for (int i = 0; i < arr.length; i += n) {
            T[] chunk = Arrays.copyOfRange(arr, i, i + n);
            chunks.add(chunk);
        }
        return chunks;
    }

    default List<LinkedList<String>> chunk(List<String> input) {
        var lists = new ArrayList<LinkedList<String>>();
        var list = new LinkedList<String>();
        for (var string : input) {
            if (string.isBlank()) {
                lists.add(list);
                list = new LinkedList<>();
            } else {
                list.add(string);
            }
        }
        lists.add(list);
        return lists;
    }

    default String stringSort(String s) {
        var chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    default int max(int... arr) {
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = Math.max(max, i);
        }
        return max;
    }

    default int max(Integer... arr) {
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = Math.max(max, i);
        }
        return max;
    }

    default int min(int... arr) {
        int min = Integer.MAX_VALUE;
        for (int i : arr) {
            min = Math.min(min, i);
        }
        return min;
    }

    default <T extends Object & Comparable<? super T>> T min(Collection<? extends T> coll) {
        Iterator<? extends T> i = coll.iterator();
        T candidate = i.next();

        while (i.hasNext()) {
            T next = i.next();
            if (next.compareTo(candidate) < 0)
                candidate = next;
        }
        return candidate;
    }

    default <T> T min(Collection<? extends T> coll, Comparator<? super T> comp) {
        if (comp == null)
            return (T) min((Collection) coll);

        Iterator<? extends T> i = coll.iterator();
        T candidate = i.next();

        while (i.hasNext()) {
            T next = i.next();
            if (comp.compare(next, candidate) < 0)
                candidate = next;
        }
        return candidate;
    }

    default <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll) {
        Iterator<? extends T> i = coll.iterator();
        T candidate = i.next();

        while (i.hasNext()) {
            T next = i.next();
            if (next.compareTo(candidate) > 0)
                candidate = next;
        }
        return candidate;
    }

    default <T> T max(Collection<? extends T> coll, Comparator<? super T> comp) {
        if (comp == null)
            return (T) max((Collection) coll);

        Iterator<? extends T> i = coll.iterator();
        T candidate = i.next();

        while (i.hasNext()) {
            T next = i.next();
            if (comp.compare(next, candidate) > 0)
                candidate = next;
        }
        return candidate;
    }

    default void addBi(Map<String, Set<String>> map, String a, String b) {
        map.computeIfAbsent(a, k -> new HashSet<>()).add(b);
        map.computeIfAbsent(b, k -> new HashSet<>()).add(a);
    }

    default void addUni(Map<String, Set<String>> map, String a, String b) {
        map.computeIfAbsent(a, k -> new HashSet<>()).add(b);
    }
}
