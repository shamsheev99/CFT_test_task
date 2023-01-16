package bin.Sort;

import bin.strategyreader.AbstractReader;

public class MergeSort {
    AbstractReader reader;
//TODO tmp class, would be refactor
    public MergeSort(AbstractReader reader) {
        this.reader = reader;
    }

    public void Sort() {
        try {
            while (true) {
                reader.setNextToCurrent();
                System.out.println(reader.getCurrent());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
