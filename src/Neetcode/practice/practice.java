package Neetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

import Neetcode.LinkedList.ListNode;
import Neetcode.Trees.algos.TreeNode;

public class practice {
    public static void main(String[] args) {
        var res = lengthOfLongestSubstring("abcabcbb");
    }

    public static int[] twoSum(int[] nums, int target) {
        var map = new HashMap<Integer,Integer>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            var diff = target - nums[i];
            if (map.containsKey(diff)) {
                res[0] = map.get(diff);
                res[1] = i;
                return res;
            }
            map.put(nums[i], i);
        }

        return res;
    }

    public boolean isAnagram(String s, String t) {
        int[] sArr = new int[26];
        int[] tArr = new int[26];

        for (int i = 0; i < s.length(); i++) {
            sArr[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            tArr[t.charAt(i) - 'a']++;
        }

        return Arrays.equals(sArr, tArr);
    }

    //Merge Two Sorted Linked Lists
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode res = new ListNode();
        ListNode curr = res;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            }
            else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }
        if (list1 != null) {
            curr.next = list1;
        }
        else {
            curr.next = list2;
        }

        return res.next;
    }

    //Contains Duplicate
    public boolean hasDuplicate(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (Integer num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }

        return false;
    }

    //Invert Binary Tree (Recursion)
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        var left = invertTree(root.left);
        var right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }

    //Invert Binary Tree (BFS)
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            var elem = q.poll();
            var temp = elem.left;
            elem.left = elem.right;
            elem.right = temp;

            if (elem.left != null) {
                q.offer(elem.left);
            }
            if (elem.right != null) {
                q.offer(elem.right);
            }
        }

        return root;
    }

    //Reverse Linked List
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode prev = null;
        ListNode curr = head;
        ListNode next = head.next;

        while (curr != null) {
            curr.next = prev;
            prev = curr;
            curr = next;
            if (next != null) {
                next = next.next;
            }
        }

        return prev;
    }

    //Subtree of Another Tree
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        if (subRoot == null) {
            return true;
        }

        if (root.val == subRoot.val && isSameTree(root, subRoot)) {
            return true;
        }

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null && q != null) {
            return false;
        }
        if (p != null && q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }

        var left = isSameTree(p.left, q.left);
        var right = isSameTree(p.right, q.right);

        if (left && right) {
            return true;
        }

        return false;
    }

    //Maximum Depth of Binary Tree (Recursion)
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    //Maximum Depth of Binary Tree (BFS)
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int maxDepth = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            maxDepth++;
            var size = q.size();
            for (int i = 0; i < size; i++) {
                var elem = q.poll();
                if (elem.left != null) {
                    q.offer(elem.left);
                }
                if (elem.right != null) {
                    q.offer(elem.right);
                }
            }
        }

        return maxDepth;
    }

    //Valid Palindrome
    public static boolean isPalindrome(String s) {
        int l = 0;
        int r = s.length() - 1;

        while (l < r) {
            while (l <= s.length() - 1 && !isAlphaNumeric(s.charAt(l))) {
                l++;
            }
            while (r >= 0 && !isAlphaNumeric(s.charAt(r))) {
                r--;
            }

            if (l >= r) {
                return true;
            }

            if (Character.toLowerCase(s.charAt(l)) == Character.toLowerCase(s.charAt(r))) {
                l++;
                r--;
            }
            else {
                return false;
            }
        }

        return true;
    }

    public static boolean isAlphaNumeric(Character ch) {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9');
    }

    //Same Binary Tree
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null && q != null) {
            return false;
        }
        if (p != null && q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }

        var left = isSameTree2(p.left, q.left);
        var right = isSameTree2(p.right, q.right);

        return left && right;
    }

    //Valid Parentheses
    public boolean isValid(String s) {
        Stack<Character> stk = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                stk.push(ch);
            }
            else {
                if (stk.isEmpty()) {
                    return false;
                }
                var pop = stk.pop();
                if ((ch == ')' && pop == '(') || (ch == '}' && pop == '{') || (ch == ']' && pop == '[')) {
                    continue;
                }
                return false;
            }
        }

        return stk.isEmpty();
    }

    //Linked List Cycle Detection
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode s = head;
        ListNode f = head;

        while (f != null) {
            s = s.next;
            if (f.next != null) {
                f = f.next.next;
            }
            else {
                return false;
            }

            if (s == f) {
                return true;
            }
        }

        return false;
    }

    //Best Time to Buy and Sell Stock
    public int maxProfit(int[] prices) {
        int l = 0;
        int r = 0;

        int maxProfit = 0;
        int size = prices.length - 1;
        while (r <= size) {
            while (r <= size && prices[r] >= prices[l]) {
                maxProfit = Math.max(maxProfit, prices[r] - prices[l]);
                r++;
            }
            if (r <= size) {
                l = r;
            }
        }

        return maxProfit;
    }

    //Number of Connected Components in an Undirected Graph (DFS)
    public int countComponents(int n, int[][] edges) {
        HashMap<Integer,List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            map.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            map.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }

        HashSet<Integer> visited = new HashSet<>();
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                countComponentsHelper(i, -1, visited, map);
                count++;
            }
        }

        return count;
    }

    public void countComponentsHelper(int curr, int parent, HashSet<Integer> visited, HashMap<Integer,List<Integer>> preMap) {
        if (visited.contains(curr)) {
            return;
        }

        visited.add(curr);
        var deps = preMap.get(curr);
        if (deps == null) {
            return;
        }
        for (Integer dep : deps) {
            if (dep == parent) {
                continue;
            }
            countComponentsHelper(dep, curr, visited, preMap);
        }
    }

    public class DSU {
        Map<Integer,Integer> parent;
        Map<Integer,Integer> rank;
        public int components;

        public DSU(int n) {
            parent = new HashMap<>();
            rank = new HashMap<>();
            components = n;
            for (int i = 0; i < n; i++) {
                parent.put(i, i);
                rank.put(i, 0);
            }
        }

        public int find(int x) {
            if (x != parent.get(x)) {
                parent.put(x, find(parent.get(x)));
            }

            return parent.get(x);
        }

        public void union(int p, int q) {
            var f1 = find(p);
            var f2 = find(q);

            if (f1 == f2) {
                return;
            }
            else if (rank.get(f1) > rank.get(f2)) {
                parent.put(f2, f1);
            }
            else if (rank.get(f1) < rank.get(f2)) {
                parent.put(f1, f2);
            }
            else {
                parent.put(f2, f1);
            }

            components--;
        }
    }

    //Number of Connected Components in an Undirected Graph (DSU-F)
    public int countComponents2(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        for (int[] edge : edges) {
            dsu.union(edge[0], edge[1]);
        }

        return dsu.components;
    }

    //3Sum
    public List<List<Integer>> threeSum(int[] nums) {
        var res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            int j = i+1;
            int k = nums.length - 1;
            int target = -nums[i];

            while (j < k) {
                int sum = nums[j] + nums[k];
                if (sum > target) {
                    k--;
                }
                else if (sum < target) {
                    j++;
                }
                else {
                    var ans = new ArrayList<Integer>();
                    ans.add(nums[i]);
                    ans.add(nums[j++]);
                    ans.add(nums[k--]);
                    res.add(ans);

                    while (j<k && nums[j] == nums[j-1]) {
                        j++;
                    }
                    while (j<k && nums[k] == nums[k+1]) {
                        k--;
                    }
                }
            }
        }

        return res;
    }

    //Combination Sum
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        cSumHelper(nums, 0, new ArrayList<>(), res, target, 0);
        return res;
    }

    public void cSumHelper(int[] nums, int sum, List<Integer> curr, List<List<Integer>> res, int target, int i) {
        if (target == sum) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int j = i; j < nums.length; j++) {
            if (sum + nums[j] > target) {
                continue; // or break since the array is sorted, will reduce the no. of iterations
            }

            curr.add(nums[j]);
            cSumHelper(nums, sum+nums[j], curr, res, target, j);
            curr.remove(curr.size()-1);
        }
    }

    //Word Search
    public boolean exist(char[][] board, String word) {
        int r = board.length;
        int c = board[0].length;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (board[i][j] == word.charAt(0) && wordSearchHelper(board, i, j, 0, word, new boolean[r][c])) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean wordSearchHelper(char[][] board, int r, int c, int i, String word, boolean[][] visited) {
        if (r >= board.length || c >= board[0].length || r < 0 || c < 0 || visited[r][c] || board[r][c] != word.charAt(i)) {
            return false;
        }
        if (i == word.length()-1) {
            return true;
        }

        visited[r][c] = true;
        boolean res = wordSearchHelper(board, r-1, c, i+1, word, visited)
            || wordSearchHelper(board, r, c-1, i+1, word, visited)
            || wordSearchHelper(board, r, c+1, i+1, word, visited)
            || wordSearchHelper(board, r+1, c, i+1, word, visited);
        
        visited[r][c] = false;
        return res;
    }

    //Longest Substring Without Repeating Characters
    public static int lengthOfLongestSubstring(String s) {
        int l = 0;
        int r = 0;
        int len = s.length() - 1;
        HashSet<Character> set = new HashSet<>();
        int max = 0;

        while (r <= len) {
            while (r <= len && !set.contains(s.charAt(r))) {
                max = Math.max(max, r-l+1);
                set.add(s.charAt(r));
                r++;
            }
            if (r <= len) {
                while (s.charAt(l) != s.charAt(r)) {
                    set.remove(s.charAt(l++));
                }
                set.remove(s.charAt(l++));
            }
        }   

        return max;
    }

    //Find Minimum in Rotated Sorted Array
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {
            int m = l + (r-l)/2;

            if (m > 0 && nums[m] < nums[m-1]) {
                return nums[m];
            }
            if (nums[m] > nums[r]) {
                l = m+1;
            }
            else {
                r = m-1;
            }
        }

        return nums[l];
    }

}
