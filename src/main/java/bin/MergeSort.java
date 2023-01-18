package bin;

import bin.parser.Parser;
import bin.strategyreader.AbstractReader;
import bin.strategyreader.IntegerReader;
import bin.strategyreader.StringReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MergeSort {
    private String [] args_;
    private int data_flag_;
    private int sort_flag_;

    private FileWriter writer_ = null;
    private File output_file_;
    private ArrayList<File> files_;
    ArrayList<AbstractReader> reader_;

//TODO tmp class, would be refactor
    public MergeSort(String [] args) {
        this.args_ = args;
        files_ = new ArrayList<>();
        reader_ = new ArrayList<>();
    }

    public void Sort() {
        try {
            callParser();
            validFiles();
            setReaders();
            startSort();
            writer_.close();
        }  catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void startSort() throws IOException {
        int j = 0;
        while (j < 20) {
            ArrayList<String> currentLines = new ArrayList<String>();
            for (int i = 0; i < reader_.size(); i++) {
                reader_.get(i).getNext();
                currentLines.add(reader_.get(i).getCurrent());
            }
            write(currentLines.get(comparator(currentLines)));
            j++;
        }
    }

    private int comparator(ArrayList<String> lines) {
        int index_min = 0;
        for(int i = 0; i < lines.size(); i++) {
            if (sort_flag_ == Parser.Flag.INCREASE.ordinal()) {
                if (lines.get(i).compareTo(lines.get(index_min)) < 0) index_min = i;
            } else {
                if (lines.get(i).compareTo(lines.get(index_min)) > 0) index_min = i;
            }
        }
        return index_min;
    }

    private void write(String result) throws IOException {
        writer_.write(result + "\n");
    }

    private void callParser() throws Exception {
        Parser local_parser= new Parser(args_);
        local_parser.parseString();
        sort_flag_ = local_parser.getSort_type();
        data_flag_ = local_parser.getData_type();
        for (String it : local_parser.getPath_files()) {
            files_.add(new File(it));
        }
    }

    private void setReaders() {
        for (File it : files_) {
            if (data_flag_ == Parser.Flag.INTEGER.ordinal()) {
                reader_.add(new IntegerReader(sort_flag_,it));
            } else {
                reader_.add(new StringReader(sort_flag_,it));
            }
        }
    }

    private void validFiles() throws Exception {
        checkOutputFile(files_.get(0));
        files_.remove(0);
        int i = 0;
        while (i < files_.size()) {
            if (!checkFileExtension(files_.get(i)) || !files_.get(i).isFile() || files_.get(i).length() == 0 ) {
                files_.remove(i);
            }
            ++i;
        }
    }
    private boolean checkFileExtension(File file) {
        String regex = "^[A-Za-z0-9+_.-]+.txt$";
        Pattern pattern = Pattern.compile(regex) ;
        Matcher matcher = pattern.matcher(file.getName());
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    private void checkOutputFile(File file) throws Exception {
        System.out.println(file.getName());
        if (!checkFileExtension(file)) throw new Exception("Error file extension. You can use just *.txt files");
        if (file.isFile()) {
            System.out.println("Output file already exist\n Do you want overwrite it? y/n");
            Scanner readTerm = new Scanner(System.in);
            String Answer = readTerm.next();
            switch (Answer.charAt(0)) {
                case 'y'|'Y' : {
//                        FileOutputStream fos = new FileOutputStream(file);
                        writer_ = new FileWriter(file);
                        break;
                }
                default: throw new Exception("Output file already exist");
            }
        } else {
//            file.createNewFile();
            writer_ = new FileWriter(file);
        }
    }
}


