package RecursionAndBacktracking;

import java.util.*;

public class PrintMazePathsWithJumps {
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

        // horizontal moves
        for (int move_size = 1; move_size <= dc - sc; move_size++) {
            printMazePaths(sr, sc + move_size, dr, dc, pathSourceToDestination + "h" + move_size);
        }

        // vertical moves
        for (int move_size = 1; move_size <= dr - sr; move_size++) {
            printMazePaths(sr + move_size, sc, dr, dc, pathSourceToDestination + "v" + move_size);
        }

        // diagonal moves
        for (int move_size = 1; move_size <= dc - sc && move_size <= dr - sr; move_size++) {
            printMazePaths(sr + move_size, sc + move_size, dr, dc, pathSourceToDestination + "d" + move_size);
        }
    }
}
