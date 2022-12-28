import java.io.*;
import java.util.*;

// Same Approach can be Used wherever we have to do partition in 2 types eg.
//  - Separate odd-even
//  - Separate 0 - 1
//  - Separate 0 - non zero
public class Partition {
    /*
     * Approach - Place elements such that at any point of time
     * 0 to j-1 index will have elements <= pivot
     * j to i-1 index will have elements > pivot
     * i to end index will have unknown elements which we are going to adjust
     */
    public static void partition(int[] arr, int pivot) {
        // Way 1
        // for (int i = 0, j = 0; i < arr.length; i++) {
        // if (arr[i] <= pivot) {
        // swap(arr, i, j);
        // j++;
        // }
        // }

        // Way 2
        int i = 0, j = 0;
        while (i < arr.length) {
            if (arr[i] <= pivot) {
                swap(arr, i, j);
                i++;
                j++;
            } else {
                i++;
            }
        }
    }

    // used for swapping ith and jth elements of array
    public static void swap(int[] arr, int i, int j) {
        System.out.println("Swapping " + arr[i] + " and " + arr[j]);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        int pivot = scn.nextInt();
        partition(arr, pivot);
        print(arr);
    }

}