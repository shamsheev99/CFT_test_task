package bin;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reader {
    File file_;

    public Reader(String file_path) {
        file_ = new File(file_path);

    }

    public boolean checkFileExtension() {
        String regex = "^[A-Za-z0-9+_.-]+.txt$";
        Pattern pattern = Pattern.compile(regex) ;
        Matcher matcher = pattern.matcher(file_.getName());
        if (!matcher.matches()) {
            //TODO exception
            System.out.println("incorrect parameters");
            return false;
        }
        System.out.println(file_.getName());
            return true;
        }


}
