package bin.strategyreader;

import static java.lang.Character.isDigit;

public class IntegerReader extends AbstractReader {
    public IntegerReader(boolean sortFlag, String fileName) {
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
    public boolean compareData(String first, String second)  {
        return sort_flag_ ? Integer.parseInt(first) < Integer.parseInt(second) : Integer.parseInt(first) > Integer.parseInt(second);
    }

}
