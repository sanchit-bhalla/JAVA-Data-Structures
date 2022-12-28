import java.io.*;
import java.util.*;

public class Sort012 {

    public static void sort012(int[] arr) {
        /*
         * Approach
         * 0 to j-1 --> contains 0
         * j to i-1 --> contains 1
         * i to k --> unknown
         * k+1 to end --> 2
         */
        int i = 0, j = 0, k = arr.length - 1;
        while (i <= k) {
            if (arr[i] == 0) {
                swap(arr, i, j);
                j++;
                i++;
            } else if (arr[i] == 2) {
                swap(arr, i, k);
                k--;
            } else { // arr[i] == 1
                i++;
            }
        }
    }

    // used for swapping ith and jth elements of array
    public static void swap(int[] arr, int i, int j) {
        System.out.println("Swapping index " + i + " and index " + j);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        sort012(arr);
        print(arr);
    }

}