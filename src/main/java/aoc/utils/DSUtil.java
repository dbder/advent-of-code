package aoc.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

public interface DSUtil {


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
}
