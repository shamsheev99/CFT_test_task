package bin.sort;

import bin.model.SortType;
import bin.parser.Parser;
import bin.parser.SortDataType;
import bin.strategyreader.AbstractReader;
import bin.strategyreader.IntegerReader;
import bin.strategyreader.StringReader;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MergeSort {
    private final Parser parameters;

    public MergeSort(Parser parameters) throws IllegalArgumentException, IOException {
        this.parameters = parameters;
        FileWriter writer = new FileWriter(parameters.getPathOutput());
        ArrayList<AbstractReader> reader =
                getReader(parameters.getPathFiles(), parameters.getDataType(), parameters.getSortType());
        while (reader.size() > 0) {
            int min_index = 0;
            for (int i = 1; i < reader.size(); i++) {
                if (reader.get(i).validLine(reader.get(min_index).getLine())) {
                    min_index = i;
                }
            }
            writer.write(reader.get(min_index).getLine() + "\n");
            try {
                reader.get(min_index).setNext();
            } catch (EOFException | NoSuchElementException e) {
                reader.remove(min_index);
            }
        }
        writer.close();
        System.out.println("Sort finished.");
    }

    private ArrayList<AbstractReader> getReader(ArrayList<String> path_files, SortDataType typeFlag, SortType sortFlag) {
        ArrayList<AbstractReader> reader =  new ArrayList<>();
        for (String filename : path_files) {
            try {
                switch (typeFlag){
                    case INTEGER -> reader.add(new IntegerReader(sortFlag, new File(filename)));
                    case STRING -> reader.add(new StringReader(sortFlag, new File(filename)));
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return reader;
    }

}
