import java.io.*;
import java.util.*;

public class PrefixEvalAndConversion {

    public static int operation(int v1, int v2, char operator) {
        int res = 0;

        if (operator == '+') {
            res = v1 + v2;
        } else if (operator == '-') {
            res = v1 - v2;
        } else if (operator == '*') {
            res = v1 * v2;
        } else if (operator == '/') {
            res = v1 / v2;
        }

        return res;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();

        // code
        Stack<Integer> vs = new Stack<>(); // value stack
        Stack<String> is = new Stack<>(); // Infix stack
        Stack<String> ps = new Stack<>(); // postfix stack

        for (int i = exp.length() - 1; i >= 0; i--) {
            char ch = exp.charAt(i);
            if (Character.isDigit(ch)) {
                vs.push(ch - '0');
                is.push(ch + "");
                ps.push(ch + "");
            } else {
                int v1 = vs.pop();
                int v2 = vs.pop();
                int res = operation(v1, v2, ch);
                vs.push(res);

                String iv1 = is.pop();
                String iv2 = is.pop();
                String newInfixExp = "(" + iv1 + ch + iv2 + ")";
                is.push(newInfixExp);

                String pv1 = ps.pop();
                String pv2 = ps.pop();
                String newPostfixExp = pv1 + pv2 + ch;
                ps.push(newPostfixExp);
            }
        }

        System.out.println(vs.pop());
        System.out.println(is.pop());
        System.out.println(ps.pop());
    }
}
