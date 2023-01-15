package bin;

import java.io.File;

public class Reader {
    File file_;

    public Reader(String file_path) {
        file_ = new File(file_path);
    }
}
