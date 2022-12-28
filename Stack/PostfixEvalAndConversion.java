import java.io.*;
import java.util.*;

public class PostfixEvalAndConversion {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();

        // code
        Stack<Integer> val = new Stack<>();
        Stack<String> infix = new Stack<>();
        Stack<String> prefix = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if (Character.isDigit(ch)) {
                val.push(ch - '0');
                infix.push(ch + "");
                prefix.push(ch + "");
            } else { // ch is operator
                int v2 = val.pop();
                int v1 = val.pop();
                int res = 0;

                String infv2 = infix.pop();
                String infv1 = infix.pop();

                String prev2 = prefix.pop();
                String prev1 = prefix.pop();

                if (ch == '+') {
                    res = v1 + v2;
                } else if (ch == '-') {
                    res = v1 - v2;
                } else if (ch == '*') {
                    res = v1 * v2;
                } else if (ch == '/') {
                    res = v1 / v2;
                }

                String infix_res = '(' + infv1 + ch + infv2 + ')';
                String prefix_res = ch + prev1 + prev2;

                val.push(res);
                // System.out.println(res);
                infix.push(infix_res);
                prefix.push(prefix_res);
            }

        }
        System.out.println(val.peek());
        System.out.println(infix.peek());
        System.out.println(prefix.peek());
    }
}