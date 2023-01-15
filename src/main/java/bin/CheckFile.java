package bin;

import java.io.File;
import java.io.FileReader;
import java.nio.file.DirectoryStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckFile {
    //TODO private
    File file_;

    public CheckFile() {}

    public void validFile(String path, boolean [] flags) throws Exception {
        file_ = new File(path);
        if (!checkFileExtension()) throw new Exception("File extension should be *.txt");
    }

    private boolean checkFileExtension() {
        String regex = "^[A-Za-z0-9+_.-]+.txt$";
        Pattern pattern = Pattern.compile(regex) ;
        Matcher matcher = pattern.matcher(file_.getName());
        if (!matcher.matches()) {
            return false;
        }
        System.out.println(file_.getName());
        return true;
    }

//    private void checkSortFile(boolean sort_type) throws Exception {
//        if (file_.length() == 0) throw new Exception("Empty file");
//        Scanner reader = new Scanner(file_);
//        String current_line = reader.nextLine();
//        String buffer_line = current_line;
//        while (reader.hasNextLine()) {
//            current_line = reader.nextLine();
//            if (sort_type) {
//
//            } else {
//
//            }
//        }
//    }
}
