package KK.Recursion.Backtracking;

import java.util.Arrays;

public class Maze {
    public static void main(String[] args) {
        //System.out.println(count(3, 3));
        //printSteps("", 3, 3);

        boolean[][] maze = {
            {true, true, true},
            {true, true, true},
            {true, true, true}
        };
        
        //mazeRestrictions("", maze, 2, 0);

        //mazeAllPathBacktracking("", maze, 0, 0);

        int[][] path = new int[maze.length][maze[0].length];
        mazeAllPathPrintSteps("", maze, 0, 0, path, 1);
    }

    public static int count(int r, int c) {
        if (r == 1 || c == 1) {
            return 1;
        }

        int right = count(r, c-1);
        int down = count(r-1, c);

        return right+down;
    }

    public static void printSteps(String p, int r, int c) {
        if (r == 1 && c == 1) {
            System.out.println(p);
            return;
        }

        if (r > 1) {
            printSteps(p + "D", r-1, c);
        }
        if (c > 1) {
            printSteps(p + "R", r, c-1);
        }
    }

    public static void mazeRestrictions(String p, boolean[][] maze, int r, int c) {
        if (r == maze.length - 1 && c == maze[0].length - 1) {
            System.out.println(p);
            return;
        }

        if (!maze[r][c]) {
            return;
        }

        if (r < maze.length - 1) {
            mazeRestrictions(p + "D", maze, r+1, c);
        }

        if (c < maze[0].length - 1) {
            mazeRestrictions(p + "R", maze, r, c+1);
        }
    }

    public static void mazeAllPathBacktracking(String p, boolean[][] maze, int r, int c) {
        if (r == maze.length - 1 && c == maze[0].length - 1) {
            System.out.println(p);
            return;
        }

        if (!maze[r][c]) {
            return;
        }

        maze[r][c] = false;

        if (r < maze.length - 1) {
            mazeAllPathBacktracking(p + "D", maze, r+1, c);
        }

        if (c < maze[0].length - 1) {
            mazeAllPathBacktracking(p + "R", maze, r, c+1);
        }

        if (r > 0) {
            mazeAllPathBacktracking(p + "U", maze, r-1, c);
        }

        if (c > 0) {
            mazeAllPathBacktracking(p + "L", maze, r, c-1);
        }

        maze[r][c] = true;
    }

    public static void mazeAllPathPrintSteps(String p, boolean[][] maze, int r, int c, int[][] path, int step) {
        if (r == maze.length - 1 && c == maze[0].length - 1) {
            
            path[r][c] = step;
            for (int[] a : path) {
                System.out.println(Arrays.toString(a));
            }
            System.out.println(p);
            System.out.println();

            path[r][c] = 0;
            return;
        }

        if (!maze[r][c]) {
            return;
        }

        maze[r][c] = false;
        path[r][c] = step;

        if (r < maze.length - 1) {
            mazeAllPathPrintSteps(p + "D", maze, r+1, c, path, step+1);
        }

        if (c < maze[0].length - 1) {
            mazeAllPathPrintSteps(p + "R", maze, r, c+1, path, step+1);
        }

        if (r > 0) {
            mazeAllPathPrintSteps(p + "U", maze, r-1, c, path, step+1);
        }

        if (c > 0) {
            mazeAllPathPrintSteps(p + "L", maze, r, c-1, path, step+1);
        }

        maze[r][c] = true;
        path[r][c] = 0;
    }
}
