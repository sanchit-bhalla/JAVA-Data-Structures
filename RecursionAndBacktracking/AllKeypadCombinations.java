package RecursionAndBacktracking;

// import java.io.*;
import java.util.*;

/*
    0 -> .;
    1 -> abc
    2 -> def
    3 -> ghi
    4 -> jkl
    5 -> mno
    6 -> pqrs
    7 -> tu
    8 -> vwx
    9 -> yz
*/
public class AllKeypadCombinations {
    private static final String[] codes = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static void main(String[] args) throws Exception {
        try (Scanner scn = new Scanner(System.in)) {
            String str = scn.next();
            ArrayList<String> res = getKPC(str);
            System.out.println(res);
        }
    }

    public static ArrayList<String> getKPC(String str) {
        if (str.length() == 0) {
            ArrayList<String> ans = new ArrayList<>();
            ans.add("");
            return ans;
        }

        // str = "678"
        char ch = str.charAt(0); // '6'
        String remaining_str = str.substring(1); // "78"
        ArrayList<String> sa = getKPC(remaining_str); // sa = small_answer = 6 words of 78
        ArrayList<String> ans = new ArrayList<>(); // 24 words for 678

        String code_for_ch = codes[ch - '0']; // "pqrs"
        for (int i = 0; i < code_for_ch.length(); i++) {
            char curr_ch = code_for_ch.charAt(i);
            for (String s : sa) {
                String new_str = curr_ch + s;
                ans.add(new_str);
            }
        }
        return ans;
    }

}