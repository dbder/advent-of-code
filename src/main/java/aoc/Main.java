//package aoc;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Set;
//import java.util.function.BiConsumer;
//import java.util.function.BinaryOperator;
//import java.util.function.Function;
//import java.util.function.Supplier;
//import java.util.stream.Collector;
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//public class Main {
//
//    public static void main(String[] args) {
//        try (Stream<String> stream = Files.lines(Path.of("input"))) {
//
//            int sum = stream.collect(ElfCollector.get())
//                    .stream()
//                    .map(s -> String.join("", s))
//                    .map(Main::findSharedChar)
//                    .mapToInt(Main::getScore).sum();
//
//            System.out.println(sum);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public static int getScore(Character sharedChar) {
//        if (sharedChar >= 'A' && sharedChar <= 'Z')
//            return sharedChar - 64 + 26;
//        else
//            return sharedChar - 96;
//    }
//
//    private static Character findSharedChar(String s) {
//        for (int i = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//            if (s.indexOf(c) > -0)
//                return s.charAt(i);
//        }
//        throw new RuntimeException("No shared char found");
//    }
//
//}
//
//class ElfCollector<String> implements Collector<String, List<List<String>>, List<List<String>>> {
//
//    static ElfCollector<java.lang.String> get() {
//        return new ElfCollector<>();
//    }
//
//    int counter = 0;
//
//    @Override
//    public Supplier<List<List<String>>> supplier() {
//        return ArrayList::new;
//    }
//
//    @Override
//    public BiConsumer<List<List<String>>, String> accumulator() {
//        return (outerList, elf) -> {
//            Stream.of("").collect(Collectors.toList())
//            if (outerList.size() < 1) {
//                outerList.add(new ArrayList<>());
//            }
//            if (outerList.get(counter).size() < 3) {
//                outerList.get(counter).add(elf);
//            } else {
//                ArrayList<String> newList = new ArrayList<>();
//                newList.add(elf);
//                outerList.add(newList);
//                counter++;
//            }
//        };
//    }
//
//    @Override
//    public BinaryOperator<List<List<String>>> combiner() {
//        return null;
//    }
//
//    @Override
//    public Function<List<List<String>>, List<List<String>>> finisher() {
//        return Collections::unmodifiableList;
//    }
//
//    @Override
//    public Set<Characteristics> characteristics() {
//        return Set.of(Characteristics.IDENTITY_FINISH);
//    }
//}
//
