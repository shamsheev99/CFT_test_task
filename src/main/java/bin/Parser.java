package bin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
//    private: // TODO private
    boolean flag_sort_type = false;
    boolean flag_data_type = false;

    String [] parse_args;
    Character [] flag_args;
    String [] path_files;


    public Parser(String[] parse_args) {
        this.parse_args = parse_args;
    }

    void parseString() {
        if (parse_args.length == 0) {
            //TODO exception
            System.out.println("empty");
        }
        int use_flag_d = 0;
        int use_flag_a = 0;
        int use_flag_i = 0;
        int use_flag_s = 0;

        for (String it : parse_args) {
            if (it.charAt(0) == '-') {
                if (it.charAt(1) == 'd') {
                    ++use_flag_d;
                    checkRepeatFlag(use_flag_d);
                    System.out.println("flag d");
                } else if (it.charAt(1) == 'a') {
                    ++use_flag_a;
                    checkRepeatFlag(use_flag_a);
                    System.out.println("flag a");
                } else if (it.charAt(1) == 'i') {
                    ++use_flag_i;
                    checkRepeatFlag(use_flag_d);
                    System.out.println("flag i");
                } else if (it.charAt(1) == 's') {
                    ++use_flag_s;
                    checkRepeatFlag(use_flag_a);
                    System.out.println("flag s");
                } else {
                    System.out.println("incorrect flags, use -a,-d,-i,-s");
                    // TODO exception incorrect flag
                }
                checkAntiFlag(use_flag_d, use_flag_a);
                checkAntiFlag(use_flag_i, use_flag_s);
            } else {
                checkFileExtension(it);
            }
        }
    }

    private void checkAntiFlag(int flag1, int flag2) {
        if (flag1 > 0 && flag2 > 0) {
            //TODO exception
            System.out.println("use -a or -d, -s or -i");
        }
    }

    private void checkRepeatFlag(int flag) {
        if (flag > 1) {
            //TODO exception
            System.out.println("you can use flag once");
        }
    }

    private void checkFileExtension(String it) {
        String regex = "^[A-Za-z0-9+_.-]+.txt$";
        Pattern pattern = Pattern.compile(regex) ;
        Matcher matcher = pattern.matcher(it);
        if (!matcher.matches()) {
            //TODO exception
            System.out.println("incorrect parameters");
        } else {
            System.out.println(it);
        }

    }


}
