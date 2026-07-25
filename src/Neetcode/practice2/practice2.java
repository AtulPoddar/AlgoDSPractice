package Neetcode.practice2;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class practice2 {
    public static void main(String[] args) {
        
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // Invert Tree
    // DFS
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

    // Invert Tree
    //BFS
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

    // Linked List Cycle Detection
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode s = head;
        ListNode f = head.next.next;

        while (s != null && f != null) {
            if (s == f) {
                return true;
            }
            
            s = s.next;
            if (f.next == null) {
                return false;
            }

            f = f.next.next;
        }

        return false;
    }

    // Valid Anagram
    public boolean isAnagram(String s, String t) {
        int[] s1 = new int[26];
        int[] t1 = new int[26];
        
        for (int i = 0; i < s.length(); i++) {
            s1[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            t1[t.charAt(i) - 'a']++;
        }

        return Arrays.equals(s1, t1);
    }

    // Reverse Linked List
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
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

    // Valid Palindrome
    public boolean isPalindrome(String s) {
        if (s.length() == 1) {
            return true;
        }

        int l = 0;
        int r = s.length() - 1;

        while (l < r) {
            while (l < r && !isAlphaNumeric(s.charAt(l))) {
                l++;
            }
            while (l < r && !isAlphaNumeric(s.charAt(r))) {
                r--;
            }
            if (l == r) {
                return true;
            }
            if (l < r && Character.toLowerCase(s.charAt(l)) == Character.toLowerCase(s.charAt(r))) {
                l++;
                r--;
                continue;
            }

            return false;
        }

        return true;
    }

    public boolean isAlphaNumeric(char c) {
        return (c >= 'a' && c <= 'z')  || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
    }

    // Last Stone Weight
    public int lastStoneWeight(int[] stones) {
        if (stones.length == 1) {
            return stones[0];
        }
        if (stones.length == 2) {
            return Math.abs(stones[0] - stones[1]);
        }

        var maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        for (Integer st : stones) {
            maxHeap.offer(st);
        }

        while (maxHeap.size() > 1) {
            var diff = Math.abs(maxHeap.poll() - maxHeap.poll());
            if (diff > 0) {
                maxHeap.offer(diff);
            }
        }

        return maxHeap.size() == 1 ? maxHeap.peek() : 0;
    }

    // Valid Parentheses
    public boolean isValid(String s) {
        var stk = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            var ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '[') {
                stk.push(ch);
            }
            else {
                if (stk.isEmpty()) {
                    return false;
                }
                var popped = stk.pop();
                if (!((ch == ')' && popped == '(') || (ch == '}' && popped == '{') || (ch == ']' && popped == '['))) {
                    return false;
                }
            }
        }

        return stk.isEmpty();
    }

    // Contains Duplicate
    public boolean hasDuplicate(int[] nums) {
        var set = new HashSet<Integer>();
        for (Integer num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }

        return false;
    }

    // Balanced Binary Tree
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean[] res = new boolean[1];
        res[0] = true;
        treeHeight(root, res);
        return res[0];
    }

    public int treeHeight(TreeNode node, boolean[] res) {
        if (node == null) {
            return 0;
        }

        var left = treeHeight(node.left, res);
        var right = treeHeight(node.right, res);
        if (Math.abs(right - left) > 1) {
            res[0] = false;
        }

        return 1 + Math.max(left, right);
    }

    // Maximum Depth of Binary Tree
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        var left = maxDepth(root.left);
        var right = maxDepth(root.right);

        return 1 + Math.max(left, right);
    }

    // Diameter of Binary Tree
    public int diameterOfBinaryTree(TreeNode root) {
        int[] diameter = new int[1];
        diameter[0] = 0;
        tHeight(root, diameter);
        return diameter[0];
    }

    public int tHeight(TreeNode node, int[] diameter) {
        if (node == null) {
            return 0;
        }

        var left = tHeight(node.left, diameter);
        var right = tHeight(node.right, diameter);

        diameter[0] = Math.max(diameter[0], left + right);

        return 1 + Math.max(left, right);
    }

    // Binary Search
    public int search(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int m = l + (r-l)/2;
            if (nums[m] == target) {
                return m;
            }
            else if (nums[m] > target) {
                r = m-1;
            }
            else {
                l = m+1;
            }
        }

        return -1;
    }

    // Best Time to Buy and Sell Stock
    public int maxProfit(int[] prices) {
        int l = 0;
        int r = 0;
        int maxP = 0;
        while (r < prices.length) {
            if (prices[l] <= prices[r]) {
                maxP = Math.max(maxP, prices[r] - prices[l]);
                r++;
            }
            else {
                l = r;
            }
        }

        return maxP;
    }

    // Two Sum
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            var diff = target - nums[i];
            if (map.containsKey(diff)) {
                res[0] = map.get(diff);
                res[1] = i;
                return res;
            }
            else {
                map.put(nums[i], i);
            }
        }

        return res;
    }

    // Subtree of Another Tree
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }

        if (isSameTree(root, subRoot)) {
            return true;
        }

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    // Same Binary Tree
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if ((p == null && q != null) || (p != null && q == null)) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }

        var v2 = isSameTree(p.left, q.left);
        var v3 = isSameTree(p.right, q.right);

        return v2 && v3;
    }

    // Merge Two Sorted Linked Lists
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode resHead = new ListNode();
        ListNode h0 = resHead;
        ListNode h1 = list1;
        ListNode h2 = list2;

        while (h1 != null && h2 != null) {
            if (h1.val <= h2.val) {
                h0.next = h1;
                h1 = h1.next;
                h0 = h0.next;
            }
            else {
                h0.next = h2;
                h2 = h2.next;
                h0 = h0.next;
            }
        }
        if (h1 != null) {
            h0.next = h1;
        }
        if (h2 != null) {
            h0.next = h2;
        }

        return resHead.next;
    }

    /////.  Medium problems ///////////////
    
    // Valid Binary Search Tree
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }

        return isValidBST(node.left, min, node.val) &&
            isValidBST(node.right, node.val, max);
    }

    // Remove Nth Node From End of List
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode curr = head;
        int count = 0;
        while (curr != null) {
            curr = curr.next;
            count++;
        }

        int r = count-n-1;
        curr = head;
        if (r < 0) {
            head = curr.next;
        }
        else {
            for (int i = 0; i < r; i++) {
                curr = curr.next;
            }
            curr.next = curr.next.next;
        }

        return head;
    }

    
}
