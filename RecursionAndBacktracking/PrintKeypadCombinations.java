package RecursionAndBacktracking;

import java.util.*;

public class PrintKeypadCombinations {
    private static final String[] codes = { ".;", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tu", "vwx", "yz" };

    public static void main(String[] args) throws Exception {
        try (Scanner scn = new Scanner(System.in)) {
            String str = scn.next();
            printKPC(str, "");
        }
    }

    public static void printKPC(String str, String asf) {
        if (str.length() == 0) {
            System.out.println(asf);
            return;
        }

        char ch = str.charAt(0);
        String ch_code = codes[ch - '0'];
        String ros = str.substring(1); // rest of string
        for (int i = 0; i < ch_code.length(); i++) {
            char codeChar = ch_code.charAt(i);
            printKPC(ros, asf + codeChar);
        }
    }
}
