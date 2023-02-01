package RecursionAndBacktracking;

import java.util.*;

public class PrintEncoding {
    public static void main(String[] args) {
        try (Scanner scn = new Scanner(System.in)) {
            String str = scn.next();
            printEncodings(str, "");
        }
    }

    public static void printEncodings(String str, String encodedStr) {
        if (str.length() == 0) {
            System.out.println(encodedStr);
            return;
        }

        int firstChar = str.charAt(0) - '0';
        if (firstChar > 0) {
            char encodedChar = (char) ('a' + firstChar - 1);
            printEncodings(str.substring(1), encodedStr + encodedChar);
        }

        if (str.length() >= 2) {
            int firstTwoChars = Integer.parseInt(str.substring(0, 2));
            if (firstTwoChars >= 10 && firstTwoChars <= 26) { // >=0 will give wrong answer. e.g try for 103
                char encodedChar = (char) ('a' + firstTwoChars - 1);
                printEncodings(str.substring(2), encodedStr + encodedChar);
            }
        }
    }
}
