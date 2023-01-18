package bin.strategyreader;

import bin.parser.Parser;

import java.io.File;

import static java.lang.Character.isWhitespace;

public class StringReader extends AbstractReader {
    public StringReader(int sortFlag, File fileName) {
        super(sortFlag, fileName);
    }

    @Override
    public boolean validLine(String input) {
        if (input.isEmpty()) return false;
        for (char sym : input.toCharArray()) {
            if (isWhitespace(sym) && sym != '\n' && sym != '\r') {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean compare(String first, String second) {
        if (sort_flag_ == Parser.Flag.INCREASE.ordinal())
            return first.compareTo(second) <= 0;
        else
            return first.compareTo(second) > 0;
    }

}
