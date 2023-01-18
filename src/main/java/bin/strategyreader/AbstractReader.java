package bin.strategyreader;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class AbstractReader {
    protected String current_;
    protected Scanner scanner_;
    protected final int sort_flag_;
    public AbstractReader(int sortFlag, File file) {
        sort_flag_ = sortFlag;
        setFile(file);
    }
    public void setFile(File file) {
        try {
            scanner_ = new Scanner(file);
            current_ = scanner_.nextLine();
        } catch (FileNotFoundException e) {
            e.getMessage();
        }

    }
    public abstract boolean validLine(String input);
    public abstract boolean compare(String first, String second);
    public String getCurrent() {
        return current_;
    }
    public boolean getNext() {
        if (scanner_.hasNextLine()) {
            boolean local_flag = false;
            String tmp_line = current_;
            do {
                if (compare(current_,tmp_line) && validLine(tmp_line)) {
                    current_ = tmp_line;
                    local_flag = true;
                } else {
                    System.out.println(tmp_line + " - incorrect line. Skipped");
                    tmp_line = scanner_.next();
                }
            }
            while (scanner_.hasNextLine() && local_flag != true);
            return true;
        }
        return false;
    }
}
