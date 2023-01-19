package bin.strategyreader;

import bin.model.SortType;
import bin.model.Sortable;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class AbstractReader {
    protected Sortable current;
    protected Sortable previous = null;
    protected final Scanner scanner;
    protected final SortType sort_flag;

    protected final File file;

    public AbstractReader(SortType sort_flag, File file) throws FileNotFoundException, EOFException {
        this.sort_flag = sort_flag;
        this.file = file;
        scanner = new Scanner(file);
        setNext();
    }

    public Sortable getLine() {
        return current;
    }

    public  void setNext() throws EOFException {
        previous = current;
        current = getNext();
        while (previous != null && compare(current, previous)) {
            System.out.println("Error input: " + file + " Not sorted file, skip:\t" + current + "\t(previous: " + previous + ")");
            current = getNext();
        }
    }

    public  boolean compare(Sortable current, Sortable previous) {
        if (sort_flag == SortType.DESC) {
            return current.compareTo(previous) > 0;
        }
        return current.compareTo(previous) < 0;
    }

    public  boolean validLine(Sortable other) {
        if (sort_flag == SortType.DESC) {
            return current.compareTo(other) > 0;
        }
        return current.compareTo(other) < 0;
    }

    protected abstract  Sortable getNext() throws EOFException;

}
