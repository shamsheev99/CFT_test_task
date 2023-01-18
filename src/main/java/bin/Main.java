package bin;


public class Main {
    public static void main(String[] args) {
        String [] testing_parser = { "-a", "-i","src/test/sout.txt", "src/test/test.txt", "src/test/out.txt", "src/test/aboba.txt"};
//        "C:\\Users\\shams\\IdeaProjects\\CFT_test_task\\src\\test\\test.exe",
       // TODO change testing_parser to args
        MergeSort sort = new MergeSort(testing_parser);
        sort.Sort();
                    // remove before end
//            for (boolean flag : flags) {
////                System.out.println(flag);
//            }
//            for (String path : files) {
////                System.out.println(path);
//            }
    }
}