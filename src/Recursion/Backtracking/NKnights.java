package Recursion.Backtracking;

public class NKnights {
    public static void main(String[] args) {
        System.out.println("Hello");
        int n=3;
        boolean[][] board = new boolean[n][n];
        print(board, 0, 0, n);
    }

    public static void print(boolean[][] board, int r, int c, int knights) {
        if (knights == 0) {
            display(board);
            System.out.println();
            return;
        }

        if (r == board.length) {
            return;
        }

        if (c == board.length) {
            print(board, r+1, 0, knights);
            return;
        }

        if (isSafe(board, r, c)) {
            board[r][c] = true;
            print(board, r, c+1, knights-1);
            board[r][c] = false;
        }

        print(board, r, c+1, knights);
    }

    private static boolean isSafe(boolean[][] board, int r, int c) {
        if (inLimits(board, r-2, c+1)) {
            if (board[r-2][c+1]) {
                return false;
            }
        }

        if (inLimits(board, r-2, c-1)) {
            if (board[r-2][c-1]) {
                return false;
            }
        }

        if (inLimits(board, r-1, c+2)) {
            if (board[r-1][c+2]) {
                return false;
            }
        }

        if (inLimits(board, r+1, c+2)) {
            if (board[r+1][c+2]) {
                return false;
            }
        }

        if (inLimits(board, r+2, c+1)) {
            if (board[r+2][c+1]) {
                return false;
            }
        }

        if (inLimits(board, r+2, c-1)) {
            if (board[r+2][c-1]) {
                return false;
            }
        }

        if (inLimits(board, r-1, c-2)) {
            if (board[r-1][c-2]) {
                return false;
            }
        }

        if (inLimits(board, r+1, c-2)) {
            if (board[r+1][c-2]) {
                return false;
            }
        }

        return true;
    }

    private static boolean inLimits(boolean[][] board, int r, int c) {
        return r >= 0 && r <= board.length-1 && c >= 0 && c <= board.length-1;
    }

    private static void display(boolean[][] board) {
        for(boolean[] row: board) {
            for (int i = 0; i < row.length; i++) {
                if (row[i]) {
                    System.out.print("K");
                }
                else {
                    System.out.print("O");
                }
            }
            System.out.println();
        }
    }


}
