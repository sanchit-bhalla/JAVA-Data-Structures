import java.io.*;
import java.util.*;

public class MergeSort {

  public static int[] mergeSort(int[] arr, int lo, int hi) {
    if (lo == hi) {
      int[] ba = new int[1];
      ba[0] = arr[lo];
      return ba;
    }

    int m = lo + (hi - lo) / 2; // another way of (lo + hi) / 2

    int[] sa1 = mergeSort(arr, lo, m);
    int[] sa2 = mergeSort(arr, m + 1, hi);
    int[] res = mergeTwoSortedArrays(sa1, sa2);

    return res;
  }

  public static int[] mergeTwoSortedArrays(int[] a, int[] b) {
    int n1 = a.length;
    int n2 = b.length;
    int[] newArr = new int[n1 + n2];
    int i = 0, j = 0, k = 0;

    while (i < n1 && j < n2) {
      if (a[i] < b[j]) {
        newArr[k++] = a[i++];
      } else {
        newArr[k++] = b[j++];
      }
    }

    while (i < n1) {
      newArr[k++] = a[i++];
    }

    while (j < n2) {
      newArr[k++] = b[j++];
    }

    return newArr;
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
    int[] sa = mergeSort(arr, 0, arr.length - 1);
    System.out.print("Sorted Array -> ");
    print(sa);
  }
}