package RecursionAndBacktracking;

import java.util.*;

public class PrintSubSequences {
    public static void main(String[] args) {
        try (Scanner scn = new Scanner(System.in)) {
            String str = scn.next();
            printSS(str, "");
        }
    }

    public static void printSS(String str, String ans) {
        if (str.length() == 0) {
            System.out.println(ans);
            return;
        }

        char ch = str.charAt(0);
        printSS(str.substring(1), ans + ch); // include ch
        printSS(str.substring(1), ans); // exclude ch
    }
}
