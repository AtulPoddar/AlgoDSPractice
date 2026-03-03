package Neetcode.BackTracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class algos {
    public static void main(String[] args) {
        
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        createSet(0, nums, new ArrayList<Integer>(), res);
        return res;
    }

    public void createSet(int i, int[] nums, List<Integer> curr, List<List<Integer>> res) {
        if (i == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        curr.add(nums[i]);
        createSet(i+1, nums, curr, res);
        curr.remove(curr.size() - 1);

        createSet(i+1, nums, curr, res);
    }

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        cSum(0, nums, target, 0, new ArrayList<Integer>(), res);
        return res;
    }

    public void cSum(int start, int[] nums, int target, int sum, List<Integer> curr, List<List<Integer>> resList) {
        if (sum == target) {
            resList.add(new ArrayList<>(curr));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (sum + nums[i] > target) {
                continue;
            }

            curr.add(nums[i]);
            cSum(i, nums, target, sum+nums[i], curr, resList);
            curr.remove(curr.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        cSum2(0, candidates, target, 0, new ArrayList<Integer>(), res);
        return res;
    }

    public void cSum2(int start, int[] nums, int target, int sum, List<Integer> curr, List<List<Integer>> resList) {
        if (sum == target) {
            resList.add(new ArrayList<>(curr));
            return;
        }

        for (int i = start; i < nums.length; i++) {

            if (i > start && nums[i] == nums[i-1]) {
                continue;
            }

            if (sum + nums[i] > target) {
                continue;
            }

            curr.add(nums[i]);
            cSum2(i+1, nums, target, sum+nums[i], curr, resList);
            curr.remove(curr.size() - 1);
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        return permHelper(nums, 0);
    }

    public static List<List<Integer>> permHelper(int[] nums, int index) {
        if (index == nums.length) {
            List<List<Integer>> ans = new ArrayList<>();
            ans.add(new ArrayList<>());
            return ans;
        }

        var temp = permHelper(nums, index+1);
        List<List<Integer>> res = new ArrayList<>();
        for (List<Integer> list : temp) {
            for (int i = 0; i <= list.size(); i++) {
                var p = new ArrayList<>(list);
                p.add(i, nums[index]);
                res.add(p);
            }
        }

        return res;
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        subDupHelper(nums, 0, new ArrayList<>(), res);
        return res;
    }

    public void subDupHelper(int[] nums, int index, List<Integer> curr, List<List<Integer>> res) {
        if (index == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        curr.add(nums[index]);
        subDupHelper(nums, index+1, curr, res);
        curr.remove(curr.size()-1);

        while (index+1 < nums.length && nums[index] == nums[index+1]) {
            index++;
        }
        subDupHelper(nums, index+1, curr, res);
    }

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        paranHelper(n, 0, 0, sb, res);
        return res;
    }

    public void paranHelper(int n, int open, int close, StringBuilder sb, List<String> res) {
        if (open == close && open == n) {
            res.add(sb.toString());
            return;
        }

        if (open < n) {
            sb.append("(");
            paranHelper(n, open+1, close, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
        
        if (open > close) {
            sb.append(")");
            paranHelper(n, open, close+1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public boolean exist(char[][] board, String word) {
        HashSet<String> set = new HashSet<>();
        int row = board.length;
        int col = board[0].length;

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (board[r][c] == word.charAt(0) && dfs6(r, c, board, word, 0, set)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean dfs6(int r, int c, char[][] board, String word, int index, HashSet<String> set) {
        String pos = r + "," + c;
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || set.contains(pos) || word.charAt(index) != board[r][c]) {
            return false;
        }

        if (index == word.length()-1) {
            return true;
        }

        set.add(pos);

        boolean res = dfs6(r+1, c, board, word, index+1, set)
                        || dfs6(r, c+1, board, word, index+1, set)
                        || dfs6(r-1, c, board, word, index+1, set)
                        || dfs6(r, c-1, board, word, index+1, set);

        set.remove(pos);

        return res;
    }

    public List<String> letterCombinations(String digits) {
        if (digits.equals("")) {
            return new ArrayList<>();
        }
        HashMap<String, String[]> map = new HashMap<>();
        map.put("2", new String[] {"a","b","c"});
        map.put("3", new String[] {"d","e","f"});
        map.put("4", new String[] {"g","h","i"});
        map.put("5", new String[] {"j","k","l"});
        map.put("6", new String[] {"m","n","o"});
        map.put("7", new String[] {"p","q","r","s"});
        map.put("8", new String[] {"t","u","v"});
        map.put("9", new String[] {"w","x","y","z"});

        List<String> res = new ArrayList<>();
        combHelper(digits, new StringBuilder(), res, 0, map);
        return res;
    }

    public void combHelper(String digits, StringBuilder curr, List<String> res, int index, HashMap<String,String[]> map) {
        if (curr.length() == digits.length()) {
            res.add(curr.toString());
            return;
        }

        String key = Character.toString(digits.charAt(index));
        var arr = map.get(key);
        for (int i = 0; i < arr.length; i++) {
            curr.append(arr[i]);
            combHelper(digits, curr, res, index + 1, map);
            curr.deleteCharAt(curr.length()-1);
        }
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        Character[][] board = new Character[n][n];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = '.';
            }
        }
        queenHelper(n, 0, res, board);

        return res;
    }

    public void queenHelper(int n, int r, List<List<String>> res, Character[][] board) {
        if (r == n) {
            res.add(output(board));
            return;
        }

        for (int c = 0; c < n; c++) {
            if (isValidBoard(r, c, board)) {
                board[r][c] = 'Q';
                queenHelper(n, r+1, res, board);
                board[r][c] = '.';
            }
        }
    }

    public boolean isValidBoard(int r, int c, Character[][] board) {
        for (int i = r-1; i >= 0; i--) {
            if (board[i][c] == 'Q') {
                return false;
            }
        }
        for (int i = r-1, j = c-1; i >= 0 && j >= 0; i--,j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        for (int i = r-1, j = c+1; i >= 0 && j < board.length; i--,j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    public List<String> output(Character[][] board) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j]);
            }

            res.add(sb.toString());
        }

        return res;
    }
}
