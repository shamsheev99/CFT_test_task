package bin.strategyreader;

import bin.model.IntegerData;
import bin.model.SortType;
import bin.model.Sortable;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;

public class IntegerReader extends AbstractReader {

    public IntegerReader(SortType sort_flag, File file) throws FileNotFoundException, EOFException {
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
                try {
                    return new IntegerData(Integer.parseInt(result));
                } catch (NumberFormatException e) {
                    System.out.println("Error input: " + file + " Convert to int error: " + result);
                    return getNext();
                }
            }
        } else {
            throw new EOFException("empty file or incorrect: " + file);
        }
    }
}
