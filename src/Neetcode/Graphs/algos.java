package Neetcode.Graphs;

public class algos {
    public static void main(String[] args) {
        
    }

    public int numIslands(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int islands = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    islandHelper(grid, r, c);
                    islands++;
                }
            }
        }

        return islands;
    }

    public void islandHelper(char[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';

        islandHelper(grid, r+1, c);
        islandHelper(grid, r-1, c);
        islandHelper(grid, r, c+1);
        islandHelper(grid, r, c-1);

        return;
    }

    public int maxAreaOfIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int maxArea = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    maxArea = Math.max(maxArea, islandAreaHelper(grid, r, c));
                }
            }
        }

        return maxArea;
    }

    public int islandAreaHelper(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0) {
            return 0;
        }

        int count = 1;
        grid[r][c] = 0;

        count += islandAreaHelper(grid, r+1, c);
        count += islandAreaHelper(grid, r-1, c);
        count += islandAreaHelper(grid, r, c+1);
        count += islandAreaHelper(grid, r, c-1);

        return count;
    }
}
