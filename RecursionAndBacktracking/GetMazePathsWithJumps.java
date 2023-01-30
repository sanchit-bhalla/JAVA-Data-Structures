package RecursionAndBacktracking;

import java.util.*;

public class GetMazePathsWithJumps {

    public static void main(String[] args) throws Exception {
        try (Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();
            int m = scn.nextInt();
            ArrayList<String> res = getMazePaths(1, 1, n, m);
            System.out.println(res);
        }
    }

    // sr - source row
    // sc - source column
    // dr - destination row
    // dc - destination column
    public static ArrayList<String> getMazePaths(int sr, int sc, int dr, int dc) {
        // base case
        if (sr == dr && sc == dc) { // already on destination
            ArrayList<String> base_res = new ArrayList<>();
            base_res.add("");
            return base_res;
        }

        ArrayList<String> allPaths = new ArrayList<>();

        // horizontal move
        for (int ms = 1; ms <= dc - sc; ms++) { // ms === move size
            ArrayList<String> horizontalPaths = getMazePaths(sr, sc + ms, dr, dc);
            for (String hPath : horizontalPaths)
                allPaths.add("h" + ms + hPath); // "h"+ms === h1, h2, h3, ...
        }
        // verical move
        for (int ms = 1; ms <= dr - sr; ms++) { // ms === move size
            ArrayList<String> verticalPaths = getMazePaths(sr + ms, sc, dr, dc);
            for (String vPath : verticalPaths)
                allPaths.add("v" + ms + vPath); // "v"+ms === v1, v2, v3, ...
        }
        // diagonal move
        for (int ms = 1; ms <= dr - sr && ms <= dc - sc; ms++) { // ms === move size
            ArrayList<String> diagonalPaths = getMazePaths(sr + ms, sc + ms, dr, dc);
            for (String dPath : diagonalPaths)
                allPaths.add("d" + ms + dPath); // "d"+ms === d1, d2, d3, ...
        }

        return allPaths;
    }

}