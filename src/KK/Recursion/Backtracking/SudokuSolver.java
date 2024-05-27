package KK.Recursion.Backtracking;

public class SudokuSolver {
    public static void main(String[] args) {
        int[][] grid = { 
            {3, 0, 6, 5, 0, 8, 4, 0, 0},
            {5, 2, 0, 0, 0, 0, 0, 0, 0},
            {0, 8, 7, 0, 0, 0, 0, 3, 1},
            {0, 0, 3, 0, 1, 0, 0, 8, 0},
            {9, 0, 0, 8, 6, 3, 0, 0, 5},
            {0, 5, 0, 0, 9, 0, 6, 0, 0}, 
            {1, 3, 0, 0, 0, 0, 2, 5, 0},
            {0, 0, 0, 0, 0, 0, 0, 7, 4},
            {0, 0, 5, 2, 0, 6, 3, 0, 0} 
        };

        solveSudoku(grid);
    }

    public static void solveSudoku(int[][] board) {
        solve(board, 0, 0);
    }

    public static void solve(int[][] board, int r, int c) {
        if (r == board.length) {
            display(board);
            return;
        }

        if (c == board.length) {
            solve(board, r+1, 0);
            return;
        }

        if (board[r][c] != 0) {
            solve(board, r, c+1);
            return;
        }

        for (int num = 1; num <= board.length; num++) {
            if (board[r][c] == 0) {
                if (isSafe(board, r, c, num)) {
                    board[r][c] = num;
                    solve(board, r, c+1);
                    board[r][c] = 0;
                }
            }
        }
    }

    private static boolean isSafe(int[][] board, int r, int c, int num) {
        for (int i = 0; i < board.length; i++) {
            if (board[r][i] == num) {
                return false;
            }
        }

        for (int i = 0; i < board.length; i++) {
            if (board[i][c] == num) {
                return false;
            }
        }

        int sqr = (int)(Math.sqrt(board.length));
        int startRow = r - r % sqr;
        int startCol = c - c % sqr;

        for (int i = startRow; i < startRow + sqr; i++) {
            for(int j = startCol; j < startCol + sqr; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void display(int[][] board) {
        for (int[] arr : board) {
            for (int col : arr) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }
}
