package RecursionAndBacktracking;

import java.util.*;
import java.io.*;

/*
 OutPut: 
    n
    n - 1
    n - 2
    ..
    1
    1
    2
    3
    ..
    n
 */
public class PrintDecreasingIncreasing {
    public static void printDecreasingIncreasing(int n) {
        if (n == 0)
            return;

        System.out.println(n);
        printDecreasingIncreasing(n - 1);
        System.out.println(n);
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        printDecreasingIncreasing(n);
    }
}