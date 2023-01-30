package RecursionAndBacktracking;

// import java.io.*;
import java.util.*;

public class GetMazePaths {

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

        ArrayList<String> horizontalPaths = new ArrayList<>();
        ArrayList<String> verticalPaths = new ArrayList<>();
        ArrayList<String> allPaths = new ArrayList<>();

        if (sc < dc)
            horizontalPaths = getMazePaths(sr, sc + 1, dr, dc);
        if (sr < dr)
            verticalPaths = getMazePaths(sr + 1, sc, dr, dc);

        for (String hPath : horizontalPaths)
            allPaths.add("h" + hPath);
        for (String vPath : verticalPaths)
            allPaths.add("v" + vPath);

        return allPaths;
    }

}