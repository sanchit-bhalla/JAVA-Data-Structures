package RecursionAndBacktracking;

import java.util.*;

public class PrintMazePaths {
    public static void main(String[] args) throws Exception {
        try (Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();
            int m = scn.nextInt();
            printMazePaths(1, 1, n, m, "");
        }
    }

    // sr - source row
    // sc - source column
    // dr - destination row
    // dc - destination column
    public static void printMazePaths(int sr, int sc, int dr, int dc, String pathSourceToDestination) {
        if (sr == dr && sc == dc) {
            System.out.println(pathSourceToDestination);
            return;
        }

        if (sc < dc) {
            printMazePaths(sr, sc + 1, dr, dc, pathSourceToDestination + "h");
        }
        if (sr < dr) {
            printMazePaths(sr + 1, sc, dr, dc, pathSourceToDestination + "v");
        }
    }
}
