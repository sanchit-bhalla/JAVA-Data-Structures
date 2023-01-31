package RecursionAndBacktracking;

import java.util.*;

public class PrintStairPaths {

    public static void main(String[] args) throws Exception {
        try (Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();
            printStairPaths(n, "");
        }
    }

    public static void printStairPaths(int n, String path) {
        if (n == 0) { // Reached on top Stair
            System.out.println(path);
            return;
        } else if (n < 0) { // Taking some wrong step. Destination can't be possible from here
            return;
        } else {
            printStairPaths(n - 1, path + "1");
            printStairPaths(n - 2, path + "2");
            printStairPaths(n - 3, path + "3");
        }
    }
}
