package aoc.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public interface CombinationUtil {

    public default  <T> List<T[]> getPermutations(Set<T> arr) {
        Class<T> ins = (Class<T>)arr.iterator().next().getClass();
        return getPermutations((T[])arr.stream().toArray((i) -> (T[])Array.newInstance(ins, arr.size())));
    }

    public default  <T> List<T[]> getPermutations(T[] array) {
        List<T[]> permutations = new ArrayList<>();
        Consumer<T[]> consumer = (T[] arr) -> permutations.add(arr);
        getPermutations(array.length, array, consumer);
        return permutations;
    }


    public default  <T> void getPermutations(int n, T[] elements, Consumer<T[]> consumer) {

        if (n == 1) {
            consumer.accept(elements.clone());
        } else {
            for (int i = 0; i < n - 1; i++) {
                getPermutations(n - 1, elements, consumer);
                if (n % 2 == 0) {
                    swap(elements, i, n - 1);
                } else {
                    swap(elements, 0, n - 1);
                }
            }
            getPermutations(n - 1, elements, consumer);
        }
    }


    public static <T> void swap(T[] input, int a, int b) {
        T tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }

}
