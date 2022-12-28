
// WAY 1 : Use 2 stacks --> 1 for stack operations and 2nd stack gives minimum element in constant time
// WAY 2 : Refer Below code --> Watch pepcoding Minimum Stack - 2 video for clearity
// We need a way to know the next minimum when current minimum element is popped.
import java.io.*;
import java.util.*;

public class MinStack {
    Stack<Integer> data;
    int min;

    public MinStack() {
        data = new Stack<>();
    }

    int size() {
        return data.size();
    }

    void push(int val) {
        // If current element(val) is greater than or equal to min -> Simply push
        /*
         * If current element(val) is smaller than min we have to update min.
         * But while popping elements we also need to know current min in constant time
         * when val is popped. So we don't push val directly in stack. Instead we push
         * [val + (val - min)] Advantage of this is since val < min => [val + (val -
         * min)] will be less than min. This will act as indicator while popping that
         * whenever top ele < min, it means actual stack top element is min and next min
         * ele we get using the stack top i.e next min = (2*currMin - stackTop)
         */
        if (size() == 0) {
            data.push(val);
            min = val; // set min as val for the first ele
        } else if (val >= min) {
            data.push(val);
        } else {
            data.push(val + (val - min)); // detection, storing a fake smaller value than original val
            min = val; // original value stored in min
        }
    }

    int pop() {
        // See logic from push comments or watch Minimum Stack - 2 video of pepcoding
        if (size() == 0) {
            System.out.println("Stack underflow");
            return -1;
        }

        if (data.peek() >= min) {
            return data.pop();
        } else {
            int val = min; // return the actual ele which is stored in min in this case
            min = 2 * min - data.peek(); // get next min
            data.pop(); // pop current element
            return val;
        }
    }

    int top() {
        // See logic from push comments or watch Minimum Stack - 2 video of pepcoding
        if (size() == 0) {
            System.out.println("Stack underflow");
            return -1;
        }

        if (data.peek() >= min) {
            return data.peek();
        } else {
            return min;
        }
    }

    int min() {
        if (size() == 0) {
            System.out.println("Stack underflow");
            return -1;
        }
        return min;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        MinStack st = new MinStack();

        String str = br.readLine();
        while (str.equals("quit") == false) {
            if (str.startsWith("push")) {
                int val = Integer.parseInt(str.split(" ")[1]);
                st.push(val);
            } else if (str.startsWith("pop")) {
                int val = st.pop();
                if (val != -1) {
                    System.out.println(val);
                }
            } else if (str.startsWith("top")) {
                int val = st.top();
                if (val != -1) {
                    System.out.println(val);
                }
            } else if (str.startsWith("size")) {
                System.out.println(st.size());
            } else if (str.startsWith("min")) {
                int val = st.min();
                if (val != -1) {
                    System.out.println(val);
                }
            }
            str = br.readLine();
        }
    }
}
