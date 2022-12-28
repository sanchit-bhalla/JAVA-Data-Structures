import java.io.*;
import java.util.*;

public class LargestAreaOfReactangleInHistogram {
    // next smallest element on the left
    public static int[] nse_left(int[] arr) {
        int[] ans = new int[arr.length];
        ans[0] = -1;
        Stack<Integer> st = new Stack<>();
        st.push(0);
        for (int i = 1; i < arr.length; i++) {
            while (st.size() > 0 && arr[i] <= arr[st.peek()]) {
                st.pop();
            }
            if (st.size() == 0) {
                ans[i] = -1;
            } else {
                ans[i] = st.peek();
            }

            st.push(i);
        }
        return ans;
    }

    // next smallest element on the right
    public static int[] nse_right(int[] arr) {
        int[] ans = new int[arr.length];
        ans[arr.length - 1] = arr.length;
        Stack<Integer> st = new Stack<>();
        st.push(arr.length - 1);
        for (int i = arr.length - 2; i >= 0; i--) {
            while (st.size() > 0 && arr[i] <= arr[st.peek()]) {
                st.pop();
            }
            if (st.size() == 0) {
                ans[i] = arr.length;
            } else {
                ans[i] = st.peek();
            }

            st.push(i);
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[] sol = nse_left(arr);
        int[] sor = nse_right(arr);

        int ans = arr[0];
        for (int i = 0; i < arr.length; i++) {
            int w = sor[i] - sol[i] - 1;
            ;

            int possArea = w * arr[i];
            if (possArea > ans) {
                ans = possArea;
            }
        }

        System.out.println(ans);
    }
}