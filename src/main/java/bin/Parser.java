package bin;

import java.io.File;

import java.util.ArrayList;


public class Parser {
//    private: // TODO private
    boolean flag_sort_type = false;
    boolean flag_data_type = false;

    String [] parse_args;
    boolean [] flag_args;
    ArrayList<String> path_files;



    public Parser(String[] parse_args) {
        this.parse_args = parse_args;
        if (parse_args.length != 0) {
            flag_args = new boolean[2];
            path_files = new ArrayList<>();
        }
    }

    void parseString() throws Exception {
        if (parse_args.length == 0) {
            sendException("Please enter parameters");
//            System.out.println("empty");
        }

        int use_flag_d = 0;
        int use_flag_a = 0;
        int use_flag_i = 0;
        int use_flag_s = 0;

        for (String it : parse_args) {
            if (it.charAt(0) == '-') {
                if (it.charAt(1) == 'd') {
                    ++use_flag_d;
                    if (checkRepeatFlag(use_flag_d)) flag_args[0] = true;
                } else if (it.charAt(1) == 'a') {
                    ++use_flag_a;
                    if (checkRepeatFlag(use_flag_a)) flag_args[0] = false;
                } else if (it.charAt(1) == 'i') {
                    ++use_flag_i;
                    if (checkRepeatFlag(use_flag_i)) flag_args[1] = false;
                } else if (it.charAt(1) == 's') {
                    ++use_flag_s;
                    if (checkRepeatFlag(use_flag_s)) flag_args[1] = true;
                } else {
                    sendException("incorrect flags, use -a,-d,-i,-s");
                }
                checkAntiFlag(use_flag_d, use_flag_a);
                checkAntiFlag(use_flag_i, use_flag_s);
            } else {
                if (checkFile(it)) path_files.add(it);
            }
        }
    }

    private void checkAntiFlag(int flag1, int flag2) throws Exception {
        if (flag1 > 0 && flag2 > 0) {
            sendException("use -a or -d, -s or -i");
//            System.out.println("use -a or -d, -s or -i");
        }
    }

    private boolean checkRepeatFlag(int flag) throws Exception {
        if (flag > 1) {
            sendException("Flag can use once");
//            System.out.println("you can use flag once");
        }
        return true;
    }

    private boolean checkFile(String it) throws Exception {
        File checking_file = new File(it);
        if (!checking_file.isFile()) {
            //TODO exception error file
            sendException("No such file or directory");
//            System.out.println("No such file or directory");
        }
        return true;
    }

    private void sendException(String message) throws Exception {
        path_files.clear();
        throw new Exception(message);
    }

    public boolean[] getFlag_args() {
        return flag_args;
    }

    public ArrayList<String> getPath_files() {
        return path_files;
    }
}
