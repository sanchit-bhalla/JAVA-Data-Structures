package RecursionAndBacktracking;

import java.util.*;

public class AllSubsequence {

    public static void main(String[] args) throws Exception {
        try (Scanner scn = new Scanner(System.in)) {
            String str = scn.next();

            ArrayList<String> res = gss(str);
            System.out.println(res);
        }
    }

    public static ArrayList<String> gss(String str) {
        // base case
        if (str.length() == 0) {
            ArrayList<String> ans = new ArrayList<>();
            ans.add("");
            return ans;
        }

        char ch = str.charAt(0); // "a"
        String remaining_str = str.substring(1); // "bc"
        ArrayList<String> remaining_ss = gss(remaining_str);

        ArrayList<String> all_ss = new ArrayList<>();

        // ch("a") not included
        // ["", "b", "c", "bc"]
        for (int i = 0; i < remaining_ss.size(); i++) {
            all_ss.add(remaining_ss.get(i));
        }

        // ch("a") included
        // ["", "c", "b", "bc", "a", "ac", "ab", "abc"]
        for (int i = 0; i < remaining_ss.size(); i++) {
            all_ss.add(ch + remaining_ss.get(i));
        }
        return all_ss;
    }

}