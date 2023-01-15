package bin;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String [] testing_parser = { "-i","C:\\Users\\shams\\IdeaProjects\\CFT_test_task\\src\\test\\aboba.txt"};
//        "C:\\Users\\shams\\IdeaProjects\\CFT_test_task\\src\\test\\test.exe",
                Parser test = new Parser(testing_parser); // TODO change testing_parser to args
        try {
            test.parseString();
            boolean [] flags = test.getFlag_args();
            ArrayList<String> files = test.getPath_files();
            for (String it : files) {
                CheckFile validator = new CheckFile();
                validator.validFile(it, flags);
            }
            // remove before end
            for (boolean flag : flags) {
                System.out.println(flag);
            }
            for (String path : files) {
                System.out.println(path);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}