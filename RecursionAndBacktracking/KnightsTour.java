package RecursionAndBacktracking;

import java.util.*;

/*
    Note -> When moving from (r, c) to the possible 8 options give first precedence to (r - 2, c + 1) and 
            move in clockwise manner to explore other options.
*/
public class KnightsTour {
    private static final int[][] MOVE_CHANGE = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 },
            { -1, -2 }, { -2, -1 } };

    public static void main(String[] args) throws Exception {
        try (Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();
            int start_row = scn.nextInt();
            int start_col = scn.nextInt();

            int[][] chess = new int[n][n];
            printKnightsTour(chess, start_row, start_col, 1);
        }
    }

    public static void printKnightsTour(int[][] chess, int row, int col, int move_number) {
        // Non-feasable Case - Out of chess board or already visited
        if (row < 0 || col < 0 || row >= chess.length || col >= chess[row].length || chess[row][col] > 0) {
            return;
        }

        // base case
        if (move_number == chess.length * chess.length) {
            chess[row][col] = move_number;
            displayBoard(chess);
            chess[row][col] = 0;
            return;
        }

        chess[row][col] = move_number;

        // call in all 8 direction without checking its feasibility. Non-feasable cases
        // will be handled above
        for (int i = 0; i < MOVE_CHANGE.length; i++) {
            int dr = MOVE_CHANGE[i][0]; // change in row
            int dc = MOVE_CHANGE[i][1]; // change in col
            printKnightsTour(chess, row + dr, col + dc, move_number + 1);
        }

        chess[row][col] = 0; // undo move while backtracking to explore another option
    }

    public static void displayBoard(int[][] chess) {
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[0].length; j++) {
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }
}
