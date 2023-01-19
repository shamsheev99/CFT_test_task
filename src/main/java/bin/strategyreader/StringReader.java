package bin.strategyreader;

import bin.model.SortType;
import bin.model.Sortable;
import bin.model.StringData;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;

public class StringReader extends AbstractReader {
    public StringReader( SortType sort_flag, File file) throws FileNotFoundException, EOFException {
        super( sort_flag, file);
    }

    @Override
    protected Sortable getNext() throws EOFException {
        if (scanner.hasNextLine()) {
            String result = scanner.nextLine();
            if (result.contains(" ") || result.contains("\t") || result.length() == 0) {
                System.out.println("Error input: " + file + " String contains a whitespace character or empty:" + result);
                return getNext();
            } else {
                return new StringData(result);
            }
        } else {
            throw new EOFException("empty file or incorrect: " + file);
        }
    }

}
