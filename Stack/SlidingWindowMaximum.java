import java.io.*;
import java.util.*;

public class SlidingWindowMaximum {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }
        int k = Integer.parseInt(br.readLine());

        // code
        int[] nge = new int[n]; // store indices
        nge[n - 1] = n; // bcz 0 < k < n. So storing anything >=n will help further in reducing
                        // conditions. We can store Integer.MAX_VALUE also

        Stack<Integer> st = new Stack<>();
        st.push(n - 1);

        for (int i = n - 2; i >= 0; i--) {
            while (st.size() > 0 && a[i] >= a[st.peek()]) {
                st.pop();
            }

            if (st.size() == 0) {
                nge[i] = n; // anything >= n bcz 0 < k < n So anything >=n will be out of window
            } else {
                nge[i] = st.peek();
            }

            st.push(i);
        }

        for (int i = 0, j = 0; i <= n - k; i++) {
            if (j < i) {
                j = i;
            }

            while (nge[j] < i + k) { // i --> starting index of window. (i+k-1) ---> last index of window
                j = nge[j];
            }
            System.out.println(a[j]);
        }
    }
}