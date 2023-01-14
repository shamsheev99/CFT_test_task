package bin;

public class Main {
    public static void main(String[] args) {
        String [] testing_parser = {"-z","-d", "-s","-s","test.txt", "aboba.exe", ".txt", "my_test.txt"};
        Parser test = new Parser(testing_parser);
        test.parseString();
    }
}