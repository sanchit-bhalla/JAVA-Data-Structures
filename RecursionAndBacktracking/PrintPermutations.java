package RecursionAndBacktracking;

import java.util.*;

public class PrintPermutations {
    public static void main(String[] args) throws Exception {
        try (Scanner scn = new Scanner(System.in)) {
            String str = scn.next();
            printAllPermutations(str, "");
        }
    }

    public static void printAllPermutations(String str, String res) {
        if (str.length() == 0) {
            System.out.println(res);
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String rem_str = str.substring(0, i) + str.substring(i + 1);
            printAllPermutations(rem_str, res + ch);
        }
    }
}
