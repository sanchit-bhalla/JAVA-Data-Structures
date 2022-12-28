import java.io.*;
import java.util.*;

public class SmallestNumberFollowingPattern {
    public static void reverse(StringBuilder sb, int si, int ei) {
        int i = si, j = ei;
        while (i < j) {
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, temp);
            i++;
            j--;
        }
    }

    public static void printStack(Stack<Integer> st) {
        while (st.size() > 0) {
            System.out.print(st.pop());
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        // code
        // Way 1 --> Using reverse
        // StringBuilder sb = new StringBuilder();
        // for (int i = 1; i <= str.length() + 1; i++) {
        // sb.append(i);
        // }

        // for (int i = 0; i < str.length();) {
        // if (str.charAt(i) == 'd') {
        // int j = i;
        // while (j < str.length() && str.charAt(j) == 'd') {
        // j++;
        // }
        // reverse(sb, i, j);
        // i = j;
        // } else {
        // i++;
        // }
        // }

        // System.out.println(sb.toString());

        // Way2 --> Using Stack
        Stack<Integer> st = new Stack<>();
        int counter = 1;
        for (int i = 0; i < str.length(); i++) {
            st.push(counter);
            counter++;

            if (str.charAt(i) == 'i') {
                printStack(st); // print and empty stack
            }
        }

        // After loop push counter and print stack
        st.push(counter);
        printStack(st);
    }
}