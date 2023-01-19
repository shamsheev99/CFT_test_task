package bin;

import bin.parser.Parser;
import bin.sort.MergeSort;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // new MergeSort(new Parser(args));
            new MergeSort(new Parser(new String[]{"-i", "-a", "src/test/out.txt" , "src/test/aboba.txt" ,  "src/test/test.txt"}));
        } catch (IllegalArgumentException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}