package bin;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String [] testing_parser = {"-d", "-s","C:\\Users\\shams\\IdeaProjects\\CFT_test_task\\src\\test\\aboba.txt"};
//        "C:\\Users\\shams\\IdeaProjects\\CFT_test_task\\src\\test\\test.exe",
                Parser test = new Parser(testing_parser);
        try {
            test.parseString();
            ArrayList<Character> flags = test.getFlag_args();
            ArrayList<String> files = test.getPath_files();
            for (String it : files) {
                Reader file = new Reader(it);
                if (file.checkFileExtension()) {
                    //TODO check sorting file, processing file etc
                }
            }
            for (Character flag : flags) {
                System.out.println(flag);
            }
            for (String path : files) {
                System.out.println(path);
            }
        } catch (Exception e) {
            System.out.println(e);
//            throw new RuntimeException(e);
        }
    }
}