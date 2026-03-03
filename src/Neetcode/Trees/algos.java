package Neetcode.Trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class algos {
    public static void main(String[] args) {
        
    }

    public static class TreeNode {
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

    // Recursion
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

    // BFS
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            var elem = queue.poll();
            var temp = elem.left;
            elem.left = elem.right;
            elem.right = temp;

            if (elem.left != null) {
                queue.offer(elem.left);
            }
            if (elem.right != null) {
                queue.offer(elem.right);
            }
        }

        return root;
    }
    
    // Iterative DFS
    public TreeNode invertTree3(TreeNode root) {
        if (root == null) {
            return null;
        }

        Stack<TreeNode> stk = new Stack<>();
        stk.push(root);
        while (stk.size() > 0) {
            var elem = stk.pop();
            var temp = elem.left;
            elem.left = elem.right;
            elem.right = temp;

            if (elem.left != null) {
                stk.push(elem.left);
            }
            if (elem.right != null) {
                stk.push(elem.right);
            }
        }

        return root;
    }

    // Recursion
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        var leftMax = maxDepth(root.left);
        var rightMax = maxDepth(root.right);

        return 1 + Math.max(leftMax, rightMax);
    }

    // BFS
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;

        while (!queue.isEmpty()) {
            level++;
            int qLen = queue.size();
            while (qLen > 0) {
                var elem = queue.poll();
                if (elem.left != null) {
                    queue.offer(elem.left);
                }
                if (elem.right != null) {
                    queue.offer(elem.right);
                }

                qLen--;
            }
        }

        return level;
    }

    class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    // Iterative DFS
    public int maxDepth3(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int maxDepth = 0;
        Stack<Pair<TreeNode,Integer>> stk = new Stack<>();
        stk.push(new Pair<TreeNode,Integer>(root, 1));

        while (!stk.isEmpty()) {
            var elem = stk.pop();
            var node = elem.getKey();
            var val = elem.getValue();
            maxDepth = Math.max(maxDepth, val);

            if (node.right != null) {
                stk.push(new Pair<TreeNode,Integer>(node.right, val + 1));
            }
            if (node.left != null) {
                stk.push(new Pair<TreeNode,Integer>(node.left, val + 1));
            }
        }

        return maxDepth;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        int[] res = new int[1];
        dfs(root, res);
        return res[0];
    }

    public int dfs(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }

        int left = dfs(root.left, res);
        int right = dfs(root.right, res);

        res[0] = Math.max(res[0], left+right);
        return 1+Math.max(left, right);
    }

    public boolean isBalanced(TreeNode root) {
        boolean[] res = new boolean[1];
        res[0] = true;
        dfs(root, res);
        return res[0];
    }

    public int dfs(TreeNode root, boolean[] res) {
        if (root == null) {
            return 0;
        }

        int left = dfs(root.left, res);
        int right = dfs(root.right, res);
        if (Math.abs(left-right) > 1) {
            res[0] = res[0] && false;
        }

        return 1+Math.max(left, right);
    }

    // Recursion
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        else if (p == null && q != null) {
            return false;
        }
        else if (p != null && q == null) {
            return false;
        }

        var left = isSameTree(p.left, q.left);
        var right = isSameTree(p.right, q.right);

        if (left && right && p.val == q.val) {
            return true;
        }
        else {
            return false;
        }
    }

    // BFS
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        else if (p == null && q != null) {
            return false;
        }
        else if (p != null && q == null) {
            return false;
        }

        Queue<Pair<TreeNode,TreeNode>> queue = new LinkedList<>();
        queue.offer(new Pair<>(p, q));

        while (!queue.isEmpty()) {
            var elem = queue.poll();
            var pNode = elem.getKey();
            var qNode = elem.getValue();
            if (pNode == null && qNode == null) {
                continue;
            }
            if ((pNode == null && qNode != null) || (pNode != null && qNode == null) || pNode.val != qNode.val) {
                return false;
            }

            queue.offer(new Pair<>(pNode.left, qNode.left));
            queue.offer(new Pair<>(pNode.right, qNode.right));
        }

        return true;
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) {
            return false;
        }
        if (subRoot == null) {
            return true;
        }

        if (isSameTree(root, subRoot)) {
            return true;
        }

        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    public void reverseString(char[] s) {
        if (s == null || s.length == 0 || s.length == 1) {
            return;
        }

        int l = 0;
        int r = s.length - 1;
        while (l <= r) {
            var temp = s[l];
            s[l] = s[r];
            s[r] = temp;

            l++;
            r--;
        }
    }

    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int i = 0;
        int n = nums.length;

        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[--n];
            }
            else {
                i++;
            }
        }

        return n;
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (i >= strs[0].length()) {
                break;
            }
            
            var ch = strs[0].substring(i, i+1);
            boolean cont = true;
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length()) {
                    cont = false;
                    break;
                }
                if (!ch.equals(strs[j].substring(i,i+1))) {
                    cont = false;
                    break;
                }
            }

            if(!cont) {
                break;
            }
            sb.append(ch);
            i++;
        }
        
        return sb.toString();
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {

            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            long itarget = target - nums[i];
            for (int j = i+1; j < nums.length; j++) {

                if (j > i+1 && nums[j] == nums[j-1]) {
                    continue;
                }

                long jtarget = itarget - nums[j];
                int k = j+1;
                int l = nums.length-1;

                while (k < l) {
                    long sum = nums[k] + nums[l];
                    if (sum > jtarget) {
                        l--;
                    }
                    else if (sum < jtarget) {
                        k++;
                    }
                    else {
                        var ls = new ArrayList<Integer>();
                        ls.add(nums[i]);
                        ls.add(nums[j]);
                        ls.add(nums[k++]);
                        ls.add(nums[l--]);

                        res.add(ls);
                        while (k<l && nums[k] == nums[k-1]) {
                            k++;
                        }
                        while (k<l && nums[l] == nums[l+1]) {
                            l--;
                        }
                    }
                }
            }
        }

        return res;
    }

    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return;
        }

        int n = nums.length;
        k = k % n;
        int[] nums2 = new int[n];
        for (int i = 0; i < n; i++) {
            nums2[i] = nums[i];
        }

        int j = 0;
        for (int i = n-k; i < n; i++) {
            nums[j++] = nums2[i]; 
        }
        for (int i = 0; i <= n-k-1; i++) {
            nums[j++] = nums2[i];
        }
    }

    public void rotate2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0 || k % nums.length == 0) {
            return;
        }

        int n = nums.length;
        k = k % n;
        while (k > 0) {
            int last = nums[n-1];
            for (int i = nums.length - 1; i > 0; i--) {
                nums[i] = nums[i-1];
            }
            nums[0] = last;

            k--;
        }
    }

    public void rotate3(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0 || k % nums.length == 0) {
            return;
        }

        int n = nums.length;
        k = k % n;
        
        reverse(nums, 0, n-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, n-1);
    }

    public void reverse(int[] nums, int s, int e) {
        while (s < e) {
            var temp = nums[s];
            nums[s] = nums[e];
            nums[e] = temp;
            s++;
            e--;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        else if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        else {
            return root;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            List<Integer> ls = new ArrayList<>();
            int count = q.size();
            while (count > 0) {
                var elem = q.poll();
                ls.add(elem.val);

                if (elem.left != null) {
                    q.offer(elem.left);
                }
                if (elem.right != null) {
                    q.offer(elem.right);
                }

                count--;
            }

            res.add(ls);
        }

        return res;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int len = q.size();
            while (len > 0) {
                var elem = q.poll();
                if (elem.left != null) {
                    q.offer(elem.left);
                }
                if (elem.right != null) {
                    q.offer(elem.right);
                }
                if (len == 1) {
                    res.add(elem.val);
                }

                len--;
            }
        }

        return res;
    }

    public int goodNodes(TreeNode root) {
        return dfs2(root, root.val);
    }

    public int dfs2(TreeNode root, int max) {
        if (root == null) {
            return 0;
        }

        if (root.val >= max) {
            return 1 + dfs2(root.left, root.val) + dfs2(root.right, root.val);
        }

        return dfs2(root.left, max) + dfs2(root.right, max);
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) 
            return true;
        return dfs3(root.left, root.val, Long.MIN_VALUE) 
            && dfs3(root.right, Long.MAX_VALUE, root.val);
    }

    public boolean dfs3(TreeNode root, long max, long min) {
        if (root == null) {
            return true;
        }

        if (root.val <= min || root.val >= max) {
            return false;
        }

        return dfs3(root.left, root.val, min) 
            && dfs3(root.right, max, root.val);
    }

    public static int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }

        int count[] = new int[1];
        count[0] = 0;
        TreeNode res[] = new TreeNode[1];
        dfs4(root, k, count, res);
        return res[0].val;
    }

    public static void dfs4(TreeNode root, int k, int[] count, TreeNode[] res) {
        if (root == null) {
            return;
        }

        dfs4(root.left, k, count, res);
        if (count[0] + 1 == k) {
            res[0] = root;
        }
        count[0]++;

        dfs4(root.right, k, count, res);
    }

    int pre_idx = 0;
    Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }

        return build(preorder, 0, inorder.length-1);
    }

    public TreeNode build(int[] preOrder, int l, int r) {
        if (l > r) {
            return null;
        }

        int root_val = preOrder[pre_idx++];
        var root = new TreeNode(root_val);
        var mid = map.get(root_val);

        root.left = build(preOrder, l, mid-1);
        root.right = build(preOrder, mid+1, r);

        return root;
    }

    static int max = 0;
    public static int maxPathSum(TreeNode root) {
        max = root.val;
        dfs5(root);
        return max;
    }

    public static int dfs5(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = dfs5(root.left);
        int right = dfs5(root.right);

        int maxVal1 = Math.max(root.val + left, root.val + right);
        int maxVal2 = Math.max(maxVal1, root.val);
        int maxVal3 = Math.max(root.val + left + right, maxVal2);

        max = Math.max(max, maxVal3);

        return maxVal2;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "N";
        }

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            var elem = q.poll();
            if (elem == null) {
                sb.append("N,");
            }
            else {
                sb.append(elem.val + ",");
                q.offer(elem.left);
                q.offer(elem.right);
            }
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("N")) {
            return null;
        }

        String[] elems = data.split(",");
        var root = new TreeNode(Integer.parseInt(elems[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int index = 1;

        while (!q.isEmpty()) {
            var elem = q.poll();

            var left = elems[index++];
            if (!left.equals("N") && !left.isEmpty()) {
                elem.left = new TreeNode(Integer.parseInt(left));
                q.offer(elem.left);
            }

            var right = elems[index++];
            if (!right.equals("N") && !right.isEmpty()) {
                elem.right = new TreeNode(Integer.parseInt(right));
                q.offer(elem.right);
            }
        }

        return root;
    }
}
