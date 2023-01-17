package bin.parser;

import java.util.ArrayList;


public class Parser {
    private final String [] parse_args;

    private int sort_type_;

    private int data_type_;
    private ArrayList<String> path_files;


    public Parser(String[] args) {
        this.parse_args = args;
        path_files = new ArrayList<>();
    }

    public void parseString() throws Exception {
        if (parse_args.length == 0) {
            sendException("Empty args, please enter parameters (-a or -d, -i or -s, outputfile(*.txt) input files(*.txt))");
        }
        int use_flag_d = 0;
        int use_flag_a = 0;
        int use_flag_i = 0;
        int use_flag_s = 0;

        for (String it : parse_args) {
            if (it.charAt(0) == '-') {
                if (it.charAt(1) == 'd') {
                    ++use_flag_d;
                    if (checkRepeatFlag(use_flag_d)) sort_type_ = Flag.DECREASE.ordinal();
                } else if (it.charAt(1) == 'a') {
                    ++use_flag_a;
                    if (checkRepeatFlag(use_flag_a)) sort_type_ = Flag.INCREASE.ordinal();
                } else if (it.charAt(1) == 'i') {
                    ++use_flag_i;
                    if (checkRepeatFlag(use_flag_i)) data_type_ = Flag.INTEGER.ordinal();
                } else if (it.charAt(1) == 's') {
                    ++use_flag_s;
                    if (checkRepeatFlag(use_flag_s)) data_type_ = Flag.STRING.ordinal();
                } else {
                    sendException("-"+it.charAt(1) + " - incorrect flag, use -a,-d,-i,-s");
                }
                checkAntiFlag(use_flag_d, use_flag_a);
                checkAntiFlag(use_flag_i, use_flag_s);
            } else {
                path_files.add(it);
            }
        }
    }

    private void checkAntiFlag(int flag1, int flag2) throws Exception {
        if (flag1 > 0 && flag2 > 0) {
            sendException("You can use -a and -d in one run, -s or -i");
        }
    }

    private boolean checkRepeatFlag(int flag) throws Exception {
        if (flag > 1) {
            sendException("Flag can use once");
        }
        return true;
    }

    private void sendException(String message) throws Exception {
        path_files.clear();
        throw new Exception(message);
    }

    public ArrayList<String> getPath_files() {
        return path_files;
    }
    public int getSort_type() {
        return sort_type_;
    }
    public int getData_type() {
        return data_type_;
    }
    public enum Flag {
        INCREASE, DECREASE, INTEGER, STRING
    }

}

