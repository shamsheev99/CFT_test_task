package bin.strategyreader;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class AbstractReader {
    protected String current_;

    protected String buffer_;
    protected Scanner scanner_;
    protected final int sort_flag_;
    public AbstractReader(int sortFlag, File file) {
        sort_flag_ = sortFlag;
        try {
            scanner_ = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getBuffer() {
        return buffer_;
    }

    public abstract boolean validLine(String input);

    public String getCurrent() {
        return current_;
    }
    public void setNextToCurrent() throws EOFException {
        buffer_ = current_;
        System.out.println("1");
        if (scanner_.hasNextLine()) {
            String tmp = scanner_.next();
            while (scanner_.hasNextLine() && validLine(tmp)) {
                current_ = tmp;
                tmp = scanner_.next();
            }
        } else {
            throw new EOFException("EOF");
        }
    }
    public abstract boolean compareData(String first, String second);
}
