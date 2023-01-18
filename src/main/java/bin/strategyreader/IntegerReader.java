package bin.strategyreader;

import bin.parser.Parser;

import java.io.File;

import static java.lang.Character.isDigit;

public class IntegerReader extends AbstractReader {
    public IntegerReader(int sortFlag, File fileName) {
        super(sortFlag, fileName);
    }
    @Override
    public boolean validLine(String input) {
        if (input.isEmpty()) {
            return false;
        }
            for (char sym : input.toCharArray()) {
                if (!isDigit(sym) && sym != '\n' && sym != '\r') {
                    return false;
                }
            }
        return true;
    }
    @Override
    public boolean compare(String first, String second) {
        if (sort_flag_ == Parser.Flag.INCREASE.ordinal())
            return Integer.parseInt(first) <= Integer.parseInt(second);
        else
            return Integer.parseInt(first) > Integer.parseInt(second);
    }
}
