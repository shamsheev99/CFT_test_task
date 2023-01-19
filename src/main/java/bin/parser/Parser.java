package bin.parser;

import bin.model.SortType;

import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    private final ArrayList<String> path_files = new ArrayList<>();
    private String path_output = null;
    private SortDataType data_type = SortDataType.STRING;
    private SortType sort_type = SortType.ASC;

    public Parser(String[] args) throws IllegalArgumentException {
        boolean flag_output_empty = true;
        boolean flag_empty_input = true;
        int flag_data_flags = 0;
        int flag_sort_flags = 0;

        for (String arg : args) {
            if (arg.charAt(0) == '-') {
                switch (arg) {
                    case "-i" -> {
                        data_type = SortDataType.INTEGER;
                        flag_data_flags++;
                    }
                    case "-s" -> {
                        data_type = SortDataType.STRING;
                        flag_data_flags++;
                    }
                    case "-a" -> {
                        sort_type = SortType.ASC;
                        flag_sort_flags++;
                    }
                    case "-d" -> {
                        sort_type = SortType.DESC;
                        flag_sort_flags++;
                    }
                    default -> throw new IllegalArgumentException("Unknown argument: " + arg);
                }
            } else {
                if (path_output == null) {
                    path_output = arg;
                    flag_output_empty = false;
                } else if (arg.equals(path_output)) {
                    throw new IllegalArgumentException("Error argument: input and output files must be different: " + arg);
                } else {
                    path_files.add(arg);
                    flag_empty_input = false;
                }
            }
        }
        if (flag_output_empty || flag_empty_input || flag_data_flags != 1 || flag_sort_flags > 1) { // generate error
            String error_output = "Error argument: (" + Arrays.toString(args) + ")";
            if (flag_output_empty) error_output += " No output file!";
            if (flag_empty_input) error_output += " No input file!";
            if (flag_data_flags != 1) error_output += " Use one type flag -i or -s!";
            if (flag_sort_flags > 1) error_output += " Use only one flag -a or -d! ";
            throw new IllegalArgumentException(error_output);
        }
    }

    public ArrayList<String> getPathFiles() {
        return path_files;
    }

    public String getPathOutput() {
        return path_output;
    }

    public SortDataType getDataType() {
        return data_type;
    }

    public SortType getSortType() {
        return sort_type;
    }
}
