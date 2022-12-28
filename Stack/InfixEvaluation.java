import java.io.*;
import java.util.*;

public class InfixEvaluation {
    public static int operation(int v1, int v2, char opr) {
        if (opr == '+') {
            return v1 + v2;
        } else if (opr == '-') {
            return v1 - v2;
        } else if (opr == '*') {
            return v1 * v2;
        } else {
            return v1 / v2;
        }
    }

    public static int precedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        }
        return -1;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();

        Stack<Integer> st1 = new Stack<>(); // for operands like 2, 4, 5
        Stack<Character> st2 = new Stack<>(); // for operators

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if (Character.isDigit(c)) {
                st1.push(c - '0');
            } else if (c == '(') {
                st2.push('(');
            } else if (c == ')') {
                while (st2.peek() != '(') {
                    char opr = st2.pop();
                    int v2 = st1.pop();
                    int v1 = st1.pop();
                    int val = operation(v1, v2, opr);
                    st1.push(val);
                }
                st2.pop(); // pop '('
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                // some operator encountered --> evaluate if any high or equal priority operator
                // present
                while (st2.size() > 0 && st2.peek() != '(' && precedence(st2.peek()) >= precedence(c)) {
                    char opr = st2.pop();
                    int v2 = st1.pop();
                    int v1 = st1.pop();
                    int val = operation(v1, v2, opr);
                    st1.push(val);
                }
                st2.push(c);
            }
        }

        while (st2.size() != 0) {
            char opr = st2.pop();
            int v2 = st1.pop();
            int v1 = st1.pop();
            int val = operation(v1, v2, opr);
            st1.push(val);
        }

        System.out.println(st1.peek());
    }
}
