package RecursionAndBacktracking;

import java.util.*;

/*
    for n = 4, output will be
    0-1, 1-3, 2-0, 3-2, .
    0-2, 1-0, 2-3, 3-1, .
*/
public class NQueen {
    public static void main(String[] args) throws Exception {
        try (Scanner scn = new Scanner(System.in)) {
            int n = scn.nextInt();
            int[][] board = new int[n][n];

            // since there are n queens and n x n board; so every row will have 1 queen if
            // answer is possible
            printNQueens(board, "", 0);
        }
    }

    public static boolean isMovePossible(int[][] board, int row, int col) {
        // check upwards
        for (int i = row - 1, j = col; i >= 0; i--) {
            if (board[i][j] == 1)
                return false;
        }

        // check above-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }

        // check above-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < board[0].length; i--, j++) {
            if (board[i][j] == 1)
                return false;
        }

        // No need to check sidewards (in current row) or downwards bcz no queen will be
        // present there
        return true;
    }

    public static void printNQueens(int[][] board, String ans, int row) {
        if (row == board.length) {
            System.out.println(ans + ".");
            return;
        }

        for (int col = 0; col < board[row].length; col++) {
            if (isMovePossible(board, row, col)) {
                board[row][col] = 1; // place queen at row-col
                printNQueens(board, ans + row + "-" + col + ", ", row + 1); // check for next row
                board[row][col] = 0; // Remove queen from row-col while backtracking
            }
        }
    }
}
