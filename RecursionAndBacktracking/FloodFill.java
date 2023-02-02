package RecursionAndBacktracking;

import java.util.*;

public class FloodFill {
    public static void main(String[] args) {
        try (Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();
            int m = scn.nextInt();
            int[][] arr = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    arr[i][j] = scn.nextInt();
                }
            }

            boolean[][] visited = new boolean[n][m];
            floodfill(arr, 0, 0, "", visited);
        }
    }

    // maze contains 0 or 1
    // 1 means obstacle
    // If all four moves are available make moves in the order 't', 'l', 'd' and 'r'
    public static void floodfill(int[][] maze, int sr, int sc, String path, boolean[][] visited) {
        // Out of maze of obstacle encountered or already visited
        if (sr < 0 || sr >= maze.length || sc < 0 || sc >= maze[0].length || maze[sr][sc] == 1 || visited[sr][sc]) {
            return;
        }

        // Reached destination
        if (sr == maze.length - 1 && sc == maze[0].length - 1) {
            System.out.println(path);
            return;
        }

        // Mark current as visited
        visited[sr][sc] = true;

        floodfill(maze, sr - 1, sc, path + 't', visited); // top move
        floodfill(maze, sr, sc - 1, path + 'l', visited); // left move
        floodfill(maze, sr + 1, sc, path + 'd', visited); // down move
        floodfill(maze, sr, sc + 1, path + 'r', visited); // right move

        // Unmark the current cell
        visited[sr][sc] = false;
    }
}
