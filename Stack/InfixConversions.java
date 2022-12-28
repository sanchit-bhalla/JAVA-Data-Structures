import java.io.*;
import java.util.*;

public class InfixConversions {

    public static int precedence(char ch) {
        if (ch == '+' || ch == '-') {
            return 1;
        } else if (ch == '*' || ch == '/') {
            return 2;
        }
        return -1;
    }

    // This function will pop 1 operator from operator stack and 2 exp from pre and
    // post stack. Then it creates new preExp and postExp and then pushes it back to
    // pre and post stack
    public static void process(Stack<String> pre, Stack<String> post, Stack<Character> operator) {
        String pre2 = pre.pop();
        String pre1 = pre.pop();
        String post2 = post.pop();
        String post1 = post.pop();
        char opr = operator.pop();
        String preExp = opr + pre1 + pre2;
        String postExp = post1 + post2 + opr;
        pre.push(preExp);
        post.push(postExp);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();

        // code
        Stack<String> pre = new Stack<>();
        Stack<String> post = new Stack<>();
        Stack<Character> operator = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if (Character.isLetterOrDigit(ch)) {
                pre.push(ch + "");
                post.push(ch + "");
            } else if (ch == '(') {
                operator.push(ch);
            } else if (ch == ')') {
                while (operator.peek() != '(') {
                    process(pre, post, operator);
                }
                operator.pop();
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                // pop all equal or high priority operator
                while (operator.size() > 0 && operator.peek() != '(' && precedence(ch) <= precedence(operator.peek())) {
                    process(pre, post, operator);
                }
                operator.push(ch);
            }
        }

        while (operator.size() != 0) {
            process(pre, post, operator);
        }

        System.out.println(post.peek());
        System.out.println(pre.peek());
    }
}