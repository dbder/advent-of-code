package aoc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Main {

    record Folder(Set<Folder>folders, List<Integer> files) {}
    public static void main(String[] args) {
        var root = new Folder(new HashSet<>(), new ArrayList<>());

        var current = root;
        current.files.add(10);
        current.files.add(10);

        var newFolder = new Folder(new HashSet<>(), new ArrayList<>());
        current.folders.add(newFolder);

        current = newFolder;
        current.files.add(20);

        System.out.println("---");
        System.out.println(root);
        System.out.println("---");
        printSizes(root);


    }


    static int printSizes(Folder folder) {
        var size = folder.files.stream().mapToInt(Integer::intValue).sum();
        size += folder.folders.stream().mapToInt(Main::printSizes).sum();
        System.out.println("size: " + size);
        return size;
    }

}