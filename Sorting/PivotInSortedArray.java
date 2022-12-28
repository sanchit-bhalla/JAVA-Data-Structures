import java.io.*;
import java.util.*;

public class PivotInSortedArray {

    public static int findPivot(int[] arr) {
        /*
         * Pivot is smallest element in sorted Array. But array can be rotated in
         * clockwise direction.
         * We need to find smallest ele in O(logN)
         */
        int lo = 0, hi = arr.length - 1;
        while (lo < hi) {
            int m = (lo + hi) / 2;
            if (arr[m] < arr[hi]) {
                // new Pivot can only be possible in left side
                hi = m;
            } else {
                // new Pivot can only be possible in right side
                lo = m + 1;
            }
        }
        return arr[lo]; // arr[hi]
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        int pivot = findPivot(arr);
        System.out.println(pivot);
    }

}