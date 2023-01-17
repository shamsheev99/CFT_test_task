package bin.strategyreader;

import bin.parser.Parser;

import java.io.File;

import static java.lang.Character.isDigit;
import static java.lang.Character.isWhitespace;

public class StringReader extends AbstractReader {
    public StringReader(int sortFlag, File fileName) {
        super(sortFlag, fileName);
    }

    @Override
    public boolean validLine(String input) {
        if (input.isEmpty()) return false;
        for (char sym : input.toCharArray()) {
            if ((isDigit(sym) || isWhitespace(sym)) && sym != '\n' && sym != '\r') {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean compareData(String first, String second)  {
        return sort_flag_ == Parser.Flag.INCREASE.ordinal() ?  first.compareTo(second) < 0 : first.compareTo(second) > 0;
    }
}
