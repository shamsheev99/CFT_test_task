package bin.strategyreader;

import static java.lang.Character.isDigit;
import static java.lang.Character.isWhitespace;

public class StringReader extends AbstractReader {
    public StringReader(boolean sortFlag, String fileName) {
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
        return sort_flag_ ?  first.compareTo(second) < 0 : first.compareTo(second) > 0;
    }
}
