import java.io.*;
import java.util.*;

public class MergeIntervals {

    public static void main(String[] args) throws Exception {
        // write your code here
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        for (int j = 0; j < n; j++) {
            String line = br.readLine();
            arr[j][0] = Integer.parseInt(line.split(" ")[0]);
            arr[j][1] = Integer.parseInt(line.split(" ")[1]);
        }

        mergeOverlappingIntervals(arr);
    }

    public static void mergeOverlappingIntervals(int[][] arr) {
        // merge overlapping intervals and print in increasing order of start time
        Pair[] pairs = new Pair[arr.length];
        for (int i = 0; i < arr.length; i++) {
            pairs[i] = new Pair(arr[i][0], arr[i][1]);
        }

        Arrays.sort(pairs);

        // Way 1 After sorting
        // System.out.print(pairs[0].st + " ");
        // int curr_et = pairs[0].et;
        // for (int i = 1; i < arr.length; i++) {
        // if (pairs[i].st > curr_et) {
        // System.out.println(curr_et);
        // System.out.print(pairs[i].st + " ");
        // curr_et = pairs[i].et;
        // } else {
        // curr_et = Math.max(curr_et, pairs[i].et);
        // }

        // // print end time of last merged result
        // if (i == arr.length - 1) {
        // System.out.println(Math.max(curr_et, pairs[i].et));
        // }
        // }

        // Way 2 After sorting --> Using stack
        Stack<Pair> st = new Stack<>();
        st.push(pairs[0]);

        for (int i = 1; i < arr.length; i++) {
            Pair top = st.peek();

            if (pairs[i].st > top.et) {
                st.push(pairs[i]);
            } else {
                top.et = Math.max(top.et, pairs[i].et);
            }
        }

        // Now we have to print merged results, but first we need to copy it in another
        // stack bcz we have to print it in increasing order
        Stack<Pair> res = new Stack<>();
        while (st.size() > 0) {
            res.push(st.pop());
        }

        while (res.size() > 0) {
            Pair top = res.pop();
            System.out.println(top.st + " " + top.et);
        }
    }

    public static class Pair implements Comparable<Pair> {
        int st;
        int et;

        Pair(int st, int et) {
            this.st = st;
            this.et = et;
        }

        // this > other --> return +ve
        // this = other --> return =
        // this < other --> return -ve
        public int compareTo(Pair other) {
            if (this.st != other.st) {
                return this.st - other.st;
            } else {
                // sort acc to end time
                return this.et - other.et;
            }
        }
    }

}