import java.util.*;
import java.io.*;

public class SelectionSort {
    public static void selectionSort(int[] arr) {
        // We need n-1 iteration to sort
        // In each iteration, a smallest element (out of remaining elements) is put at
        // its correct position
        for (int itr = 0; itr < arr.length - 1; itr++) {
            int min_index = itr;
            for (int j = itr + 1; j < arr.length; j++) {
                if (isSmaller(arr, j, min_index)) {
                    min_index = j;
                }
            }
            swap(arr, itr, min_index);
        }
    }

    // used for swapping ith and jth elements of array
    public static void swap(int[] arr, int i, int j) {
        System.out.println("Swapping " + arr[i] + " and " + arr[j]);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // return true if ith element is smaller than jth element
    public static boolean isSmaller(int[] arr, int i, int j) {
        System.out.println("Comparing " + arr[i] + " and " + arr[j]);
        if (arr[i] < arr[j]) {
            return true;
        } else {
            return false;
        }
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void main(String[] args) throws Exception {
        try (Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scn.nextInt();
            }
            selectionSort(arr);
            print(arr);
        }
    }
}
