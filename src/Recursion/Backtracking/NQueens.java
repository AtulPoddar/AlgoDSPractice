package Recursion.Backtracking;

public class NQueens {
    public static void main(String[] args) {
        int n = 5;
        var c = count(new boolean[n][n], 0);
        
        System.out.println(c);
    }

    public static int count(boolean[][] board, int r) {
        if (r == board.length) {
            displayBoard(board);
            System.out.println();
            return 1;
        }

        int countNum = 0;

        for (int col = 0; col < board.length; col++) {
            if (isSafe(board, r, col)) {
                board[r][col] = true;
                countNum += count(board, r+1);
                board[r][col] = false;
            }
        }

        return countNum;
    }

    private static boolean isSafe(boolean[][] board, int r, int col) {
        // Is Safe? - UP
        for (int i = 0; i < r; i++) {
            if (board[i][col]) {
                return false;
            }
        }

        //Is Safe? - Left Diagonal
        int maxLeft = Math.min(r, col);
        for (int i = 1; i <= maxLeft; i++) {
            if (board[r-i][col-i]) {
                return false;
            }
        }

        //Is Safe? - Right Diagonal
        int maxRight = Math.min(r, board.length - col - 1);
        for (int i = 1; i <= maxRight; i++) {
            if (board[r-i][col+i]) {
                return false;
            }
        }

        return true;
    }

    private static void displayBoard(boolean[][] board) {
        for(boolean[] row: board) {
            for (int i = 0; i < row.length; i++) {
                if (row[i]) {
                    System.out.print("Q");
                }
                else {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }
}
