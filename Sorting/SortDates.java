import java.io.*;
import java.util.*;

// Application of Radix Sort
public class SortDates {

    public static void sortDates(String[] arr) {
        countSort(arr, 1000000, 100, 32); // sort acc to days (DD). Range(1-31)
        countSort(arr, 10000, 100, 13); // sort acc to Months (MM). Range(1-12)
        countSort(arr, 1, 10000, 2501); // sort acc to years (YYYY). Range 0-2500 given in que
    }

    public static void countSort(String[] arr, int div, int mod, int range) {
        String[] ans = new String[arr.length];

        int[] freq = new int[range];
        // fill freq array
        for (int i = 0; i < arr.length; i++) {
            int idx = Integer.parseInt(arr[i], 10) / div % mod; // In case of parse Int we need to pass base
                                                                // (decimal=10) also otherwise string '06' when
                                                                // converted to integer, machine consisder it as octal
                                                                // number
            freq[idx]++;
        }

        // prefix sum array
        for (int i = 1; i < freq.length; i++) {
            freq[i] += freq[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            int val = Integer.parseInt(arr[i], 10) / div % mod;
            int pos = freq[val];
            int idx = pos - 1;
            ans[idx] = arr[i];
            freq[val]--;
        }

        // copy ans back into arr
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ans[i];
        }
    }

    public static void print(String[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            String str = scn.next();
            arr[i] = str;
        }
        sortDates(arr);
        print(arr);
    }

}