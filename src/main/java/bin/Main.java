package bin;

import bin.Sort.MergeSort;
import bin.parser.Parser;
import bin.strategyreader.AbstractReader;
import bin.strategyreader.IntegerReader;
import bin.validators.CheckFile;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String [] testing_parser = { "-d", "-i","src/test/out.txt", "src/test/test.txt", "src/test/aboba.txt"};
//        "C:\\Users\\shams\\IdeaProjects\\CFT_test_task\\src\\test\\test.exe",
                Parser test = new Parser(testing_parser); // TODO change testing_parser to args
        try {
            test.parseString();
            boolean [] flags = test.getFlag_args();
            ArrayList<String> files = test.getPath_files();
            for (String it : files) {
                CheckFile validator = new CheckFile();
                validator.validFile(it);
                AbstractReader tmp = new IntegerReader(flags[1], it);
                MergeSort sort = new MergeSort(tmp);
                sort.Sort();
            }
            String output_file = files.get(0);
            files.remove(0);
            // remove before end
//            for (boolean flag : flags) {
////                System.out.println(flag);
//            }
//            for (String path : files) {
////                System.out.println(path);
//            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}