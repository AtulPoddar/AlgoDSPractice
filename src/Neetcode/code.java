package Neetcode;

import java.util.*;
import java.util.LinkedList;

import Neetcode.LinkedList.ListNode;

public class code {
    public static void main(String[] args) {
        var temp = permute(new int[] {1,2,3});
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

    public static boolean isAnagram(String s, String t) {
        var map = new HashMap<Character, Integer>();
        for (int i=0; i<s.length(); i++) {
            char a = s.charAt(i);
            if (map.containsKey(a)) {
                map.put(a, map.get(a) + 1);
            }
            else {
                map.put(a, 1);
            }
        }
        for (int i=0; i<t.length(); i++) {
            char a = t.charAt(i);
            if (map.containsKey(a)) {
                map.put(a, map.get(a) - 1);
                if (map.get(a) == 0) {
                    map.remove(a);
                }
            }
            else {
                return false;
            }
        }

        return map.isEmpty();
    }

    public int[] twoSum(int[] nums, int target) {
        var map = new HashMap<Integer,Integer>();
        var ans = new int[2];
        for(int i=0; i<nums.length; i++) {
            var diff = target - nums[i];
            if (map.containsKey(diff)) {
                ans[0] = map.get(diff);
                ans[1] = i;
            }
            else {
                map.put(nums[i], i);
            }
        }

        return ans;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        var map = new HashMap<String, List<String>>();
        for (String str:strs) {
            int[] alphs = new int[26];
            for (int i = 0; i < str.length(); i++) {
                alphs[str.charAt(i) - 'a']++;
            }
            
            var key = Arrays.toString(alphs);
            map.putIfAbsent(key, new ArrayList<String>());
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }

    public int[] topKFrequent(int[] nums, int k) {
        var map = new HashMap<Integer,Integer>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        List<Integer>[] arr = new List[nums.length+1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new ArrayList<Integer>();
        }

        for (var entry : map.entrySet()) {
            arr[entry.getValue()].add(entry.getKey());
        }

        int[] result = new int[k];
        int index = 0;
        for (int i = arr.length-1; i >= 0 && index < k; i--) {
            var list = arr[i];
            for (Integer val : list) {
                result[index++] = val;
                if (index == k) {
                    return result;
                }
            }
        }

        return result;
    }

    public static String encode(List<String> strs) {
        if (strs == null || strs.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length()).append("#").append(str);
        }

        return sb.toString();
    }

    public static List<String> decode(String str) {
        if (str == null || str.isEmpty()) {
            return new ArrayList<>();
        }

        var result = new ArrayList<String>();
        int i = 0;
        while (i < str.length()) {
            StringBuilder sb = new StringBuilder();
            int j = i;
            while (str.charAt(j) != '#') {
                sb.append(str.charAt(j));
                j++;
            }

            var num = Integer.parseInt(sb.toString());
            StringBuilder sbb = new StringBuilder();
            for (int k = 1; k <= num; k++) {
                sbb.append(str.charAt(j+k));
            }

            result.add(sbb.toString());
            i = j+1+sbb.length();
        }

        return result;
    }

    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];
        result[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i-1] * nums[i-1];
        }

        int product = 1;
        for (int i = nums.length-1; i >= 0; i--) {
            product = i == nums.length-1 ? 1 : product*nums[i+1];
            result[i] = result[i]*product;
        }

        return result;
    }

    public boolean isValidSudoku(char[][] board) {
        Map<Integer,Set<Character>> rows = new HashMap<>();
        Map<Integer,Set<Character>> cols = new HashMap<>();
        Map<String,Set<Character>> squares = new HashMap<>();

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length; c++) {
                if (board[r][c] == '.') {
                    continue;
                }
                var squareKey = r/3 + "," + c/3;

                if (rows.computeIfAbsent(r, k -> new HashSet<Character>()).contains(board[r][c]) ||
                        cols.computeIfAbsent(c, k -> new HashSet<Character>()).contains(board[r][c]) ||
                            squares.computeIfAbsent(squareKey, k -> new HashSet<Character>()).contains(board[r][c])) {
                    return false;
                }

                rows.get(r).add(board[r][c]);
                cols.get(c).add(board[r][c]);
                squares.get(squareKey).add(board[r][c]);
            }
        }

        return true;
    }

    public static int longestConsecutive(int[] nums) {
        var set = new HashSet<Integer>();
        for (Integer num : nums) {
            set.add(num);
        }

        int maxCount = 0;
        for (Integer num : nums) {
            int count = 0;
            if (!set.contains(num-1)) {
                count++;
                int next = num+1;
                while (set.contains(next)) {
                    count++;
                    next++;
                }
            }

            maxCount = count > maxCount ? count : maxCount;
        }

        return maxCount;
    }

    public boolean isPalindrome(String s) {
        int len = s.length();
        int l = 0;
        int r = len-1;

        while (l <= r) {
            if (!isAlphaNumeric(s.charAt(l))) {
                l++;
                continue;
            }
            if (!isAlphaNumeric(s.charAt(r))) {
                r--;
                continue;
            }

            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) {
                return false;
            }

            l++;
            r--;
        }

        return true;
    }

    public boolean isAlphaNumeric(char ch) {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >='0' && ch <= '9');
    }

    public int[] twoSum2(int[] numbers, int target) {
        int len = numbers.length;
        int l = 0;
        int r = len-1;
        int[] res = new int[2];

        while (l < r) {
            var sum = numbers[l] + numbers[r];
            if (sum > target) {
                r--;
            }
            else if (sum < target) {
                l++;
            }
            else {
                res[0] = l+1;
                res[1] = r+1;
                break;
            }
        }

        return res;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        var res = new ArrayList<List<Integer>>();

        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {

            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            var target = -nums[i];
            int j = i+1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[j] + nums[k];
                if (target > sum) {
                    j++;
                }
                else if (target < sum) {
                    k--;
                }
                else {
                    var ls = new ArrayList<Integer>();
                    ls.add(nums[i]);
                    ls.add(nums[j++]);
                    ls.add(nums[k--]);
                    res.add(ls);

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

    public int maxArea(int[] heights) {
        int l = 0;
        int r = heights.length - 1;
        int maxArea = 0;

        while (l<r) {
            int area = Math.min(heights[l], heights[r]) * (r-l);
            maxArea = Math.max(maxArea, area);

            if (heights[l] <= heights[r]) {
                l++;
            }
            else {
                r--;
            }
        }

        return maxArea;
    }

    public static int trap(int[] height) {
        int l = 0;
        int r = height.length - 1;
        int leftMax = height[l];
        int rightmax = height[r];
        int res = 0;

        while (l < r) {
            if (leftMax < rightmax) {
                l++;
                leftMax = Math.max(leftMax, height[l]);
                res += (leftMax - height[l]);
            }
            else {
                r--;
                rightmax = Math.max(rightmax, height[r]);
                res += (rightmax - height[r]);
            }
        }

        return res;
    }

    public int maxProfit(int[] prices) {
        int l = 0;
        int r = 1;
        int maxProfit = 0;

        while (r < prices.length) {
            if (prices[l] < prices[r]) {
                maxProfit = Math.max(maxProfit, prices[r] - prices[l]);
            }
            else {
                l = r;
            }

            r++;
        }

        return maxProfit;
    }

    public static int lengthOfLongestSubstring(String s) {
        int l = 0;
        int r = 0;
        int count = 0;
        var set = new HashSet<Character>();

        while (r < s.length()) {
            while (r < s.length() && !set.contains(s.charAt(r))) {
                set.add(s.charAt(r));
                count = Math.max(count, r-l+1);
                r++;
            }

            set.remove(s.charAt(l));
            l++;
        }

        return count;
    }

    public static int characterReplacement(String s, int k) {
        int l = 0;
        int r = 0;
        var map = new HashMap<Character,Integer>();
        int maxf = 0;
        int count = 0;

        while (r < s.length()) {
            var ch = s.charAt(r);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            maxf = Math.max(maxf, map.get(ch));

            if (r-l+1 - maxf <= k) {
                count = Math.max(count,r-l+1);
            }
            else {
                map.put(s.charAt(l), map.get(s.charAt(l))-1);
                l++;
            }

            r++;
        }

        return count;
    }

    public boolean checkInclusion(String s1, String s2) {
        int[] alph = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            var ch = s1.charAt(i);
            alph[ch - 'a']++;
        }

        var s1Str = Arrays.toString(alph);
        var s1len = s1.length();

        int l = 0;
        int r = 0;
        int[] s2Alph = new int[26];
        while (r < s2.length()) {
            while (r < s2.length() && r-l+1 <= s1len) {
                s2Alph[s2.charAt(r) - 'a']++;
                r++;
            }

            var s2Str = Arrays.toString(s2Alph);
            if (s1Str.equals(s2Str)) {
                return true;
            }

            s2Alph[s2.charAt(l) - 'a']--;
            l++;
        }

        return false;
    }

    public static String minWindow(String s, String t) {
        if (t.isEmpty() || s.isEmpty()) {
            return "";
        }
        
        var needMap = new HashMap<Character,Integer>();
        var haveMap = new HashMap<Character,Integer>();

        for (int i = 0; i < t.length(); i++) {
            var ch = t.charAt(i);
            needMap.put(ch, needMap.getOrDefault(ch, 0) + 1);
        }

        int needCount = needMap.size();
        int l = 0;
        int r = 0;
        int haveCount = 0;
        int resCount = Integer.MAX_VALUE;
        int[] res = new int[2];

        while (r < s.length()) {
            var ch = s.charAt(r);
            haveMap.put(ch, haveMap.getOrDefault(ch, 0) + 1);

            if (needMap.containsKey(ch) && needMap.get(ch).equals(haveMap.get(ch))) {
                haveCount++;
            }

            while (haveCount == needCount) {
                if ((r-l+1) < resCount) {
                    resCount = r-l+1;
                    res[0] = l;
                    res[1] = r;
                }

                var chl = s.charAt(l);
                haveMap.put(chl, haveMap.get(chl) - 1);
                if (needMap.containsKey(chl) && needMap.get(chl) > haveMap.get(chl)) {
                    haveCount--;
                }

                l++;
            }

            r++;
        }

        return resCount == Integer.MAX_VALUE ? "" : s.substring(res[0], res[1]+1);
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] output = new int[n-k+1];

        int l = 0;
        int r = 0;
        Deque<Integer> q = new LinkedList<>();

        while (r < n) {
            while (!q.isEmpty() && nums[r] > nums[q.getLast()]) {
                q.removeLast();
            }
            q.addLast(r);

            if (l > q.getFirst()) {
                q.removeFirst();
            }

            if ((r+1) >= k) {
                output[l] = nums[q.getFirst()];
                l++;
            }

            r++;
        }

        return output;
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            var ch = s.charAt(i);
            if (stack.isEmpty() || ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            else {
                var elem = stack.peek();
                if (!((ch == ')' && elem == '(') || (ch == '}' && elem == '{') || (ch == ']' && elem == '['))) {
                    return false;
                }
                
                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            var token = tokens[i];

            if (!(token == "+" || token == "-" || token == "*" || token == "/")) {
                stack.push(Integer.parseInt(token));
            }
            else {
                var secondOpr = stack.pop();
                var firstOpr = stack.pop();

                switch (token) {
                    case "+":
                        stack.push(firstOpr + secondOpr);
                        break;
                    case "-":
                        stack.push(firstOpr - secondOpr);
                        break;
                    case "*":
                        stack.push(firstOpr * secondOpr);
                        break;
                    case "/":
                        stack.push(firstOpr / secondOpr);
                        break;
                    default:
                        break;
                }
            }
        }

        return stack.pop();
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        var n = temperatures.length;
        Stack<int[]> stk = new Stack<>();
        int[] res = new int[n];

        for (int i = n-1; i >= 0; i--) {
            while (!stk.isEmpty() && stk.peek()[0] <= temperatures[i]) {
                stk.pop();
            }

            if (stk.isEmpty()) {
                res[i] = 0;
            }
            else {
                var peek = stk.peek();
                res[i] = peek[1] - i;
            }

            stk.push(new int[] {temperatures[i], i});
        }

        return res;
    }

    public static int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        double[][] posTime = new double[n][2];

        for (int i = 0; i < speed.length; i++) {
            posTime[i][0] = position[i];
            posTime[i][1] = ((double)(target - position[i])) / speed[i];
        }

        Arrays.sort(posTime, (a,b) -> Double.compare(a[0], b[0]));
        Stack<Double> stk = new Stack<>();
        int fleet = 0;

        int r = n-1;
        while (r >= 0) {
            if (!stk.isEmpty() && stk.getFirst() < posTime[r][1]) {
                fleet++;
                stk.clear();
            }

            stk.push(posTime[r][1]);
            r--;
        }

        return fleet + 1;
    }

    public int largestRectangleArea(int[] heights) {
        Stack<int[]> stk = new Stack<>();
        int maxArea = 0;

        stk.push(new int[] {heights[0], 0});
        for (int i = 1; i < heights.length; i++) {
            if (stk.peek()[0] == heights[i]) {
                continue;
            }
            else if (stk.peek()[0] < heights[i]) {
                stk.push(new int[] {heights[i], i});
            }
            else {
                int lastPoppedIndex = 0;
                while (!stk.isEmpty() && stk.peek()[0] > heights[i]) {
                    var popped = stk.pop();
                    maxArea = Math.max(maxArea, popped[0] * (i - popped[1]));
                    lastPoppedIndex = popped[1];
                }

                stk.push(new int[] {heights[i], lastPoppedIndex});
            }
        }

        while (!stk.isEmpty()) {
            var popped = stk.pop();
            maxArea = Math.max(maxArea, popped[0] * (heights.length - popped[1]));
        }

        return maxArea;
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int n = nums.length;
        int l = 0;
        int r = n-1;

        while (l <= r) {
            int m = l + (r-l) / 2;
            if (target > nums[m]) {
                l = m+1;
            }
            else if (target < nums[m]) {
                r = m-1;
            }
            else {
                return m;
            }
        }

        return -1;
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[m-1].length;

        int l = 0;
        int r = (m * n) - 1;
        while (l <= r) {
            int md = l + (r - l) / 2;
            int row = md / n;
            int col = md % n;
            if (target > matrix[row][col]) {
                l = md + 1;
            }
            else if (target < matrix[row][col]) {
                r = md - 1;
            }
            else {
                return true;
            }
        }

        return false;
    }

    public int minEatingSpeed(int[] piles, int h) {
        if (piles == null || piles.length == 0) {
            return 0;
        }

        int r = Arrays.stream(piles).max().getAsInt();
        int l = 1;
        int speed = 0;

        while (l <= r) {
            int m = l + (r - l)/2;
            long time = 0;
            for (int pile : piles) {
                time += Math.ceil((double)pile / m);
            }

            if (time <= h) {
                speed = m;
                r = m-1;
            }
            else {
                l = m+1;
            }
        }

        return speed;
    }

    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (m > 0 && nums[m] < nums[m-1]) {
                return nums[m];
            }
            else if (nums[m] > nums[r]) {
                l = m+1;
            }
            else {
                r = m-1;
            }
        }

        return nums[l];
    }

    public int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        var minIndex = findMinIndex(nums);
        var res = binSearch(nums, 0, minIndex-1, target);
        if (res == -1) {
            return binSearch(nums, minIndex, nums.length-1, target);
        }

        return res;
    }

    public int findMinIndex(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {
            int m = l + (r-l)/2;
            if (m>0 && nums[m-1] > nums[m]) {
                return m;
            }
            else if (nums[m] > nums[r]) {
                l = m+1;
            }
            else {
                r = m-1;
            }
        }

        return l;
    }

    public int binSearch(int[] nums, int s, int e, int target) {
        while (s <= e) {
            int m = s + (e-s)/2;
            if (target > nums[m]) {
                s = m + 1;
            }
            else if (target < nums[m]) {
                e = m - 1;
            }
            else {
                return m;
            }
        }

        return -1;
    }

    public int majorityElement(int[] nums) {
        int count = 1;
        int res = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == res) {
                count++;
            }
            else if (count == 0) {
                res = nums[i];
                count++;
            }
            else {
                count--;
            }
        }

        return res;
    }

    public List<Integer> majorityElement2(int[] nums) {
        HashMap<Integer,Integer> map = new HashMap<>(2);
        for (int i = 0; i < nums.length; i++) {
            int elem = nums[i];
            if (map.containsKey(elem)) {
                map.put(elem, map.get(elem)+1);
            }
            else if (map.size() == 2) {
                map.entrySet().forEach(n -> map.put(n.getKey(), map.get(n.getKey()) - 1));
            }
            else {
                map.put(elem, 1);
            }

            List<Integer> toRemove = new ArrayList<>();
            for (int key : map.keySet()) {
                if (map.get(key) == 0) {
                    toRemove.add(key);
                }
            }
            toRemove.forEach(n -> map.remove(n));
        }

        List<Integer> res = new ArrayList<>();
        for (Integer key : map.keySet()) {
            int frequency = 0;
            for (Integer num : nums) {
                if (num == key) {
                    frequency++;
                }
            }

            if (frequency > nums.length / 3) {
                res.add(key);
            }
        }

        return res;
    }

    public int guessNumber(int n) {
        int l = 1;
        int r = n;

        while (l <= r) {
            int m = l + (r-l)/2;
            int guess = guess(m);
            if (guess == 0) {
                return m;
            }
            else if (guess == -1) {
                r = m-1;
            }
            else {
                l = m+1;
            }
        }

        return -1;
    }

    public int calPoints(String[] operations) {
        Stack<Integer> stk = new Stack<>();
        for (String op : operations) {
            switch (op) {
                case "+":
                    var popped = stk.pop();
                    var res = popped + stk.peek();
                    stk.push(popped);
                    stk.push(res);
                    break;
                
                case "D":
                    stk.push(stk.peek() * 2);
                    break;

                case "C":
                    stk.pop();
                    break;

                default:
                    stk.push(Integer.parseInt(op));
                    break;
            }   
        }

        var result = 0;
        while (!stk.isEmpty()) {
            result += stk.pop();
        }

        return result;
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

// ------------------------------- BackTracking ------------------------------------------

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

    public class TrieNode {
        Map<Character, TrieNode> children;
        boolean word;

        public TrieNode() {
            children = new HashMap<>();
        }

        public void addWord(String word) {
            TrieNode curr = this;
            for (int i = 0; i < word.length(); i++) {
                if (!curr.children.containsKey(word.charAt(i))) {
                    curr.children.put(word.charAt(i), new TrieNode());
                }

                curr = curr.children.get(word.charAt(i));
            }

            curr.word = true;
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        Set<String> res = new HashSet<>();
        int r = board.length;
        int c = board[0].length;
        HashSet<String> set = new HashSet<>();

        TrieNode root = new TrieNode();
        for (String word : words) {
            root.addWord(word);
        }

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                findWordsHelper(i, j, board, set, root, res, "");
            }
        }

        return new ArrayList<>(res);
    }

    public void findWordsHelper(int r, int c, char[][] board, HashSet<String> set, TrieNode root, Set<String> res, String word) {
        String pos = r + "," + c;
        if (r < 0 || c < 0 || r >= board.length || c >= board[0].length || set.contains(pos) || !root.children.containsKey(board[r][c])) {
            return;
        }

        root = root.children.get(board[r][c]);
        word += board[r][c];
        if (root.word) {
            res.add(word);
        }

        set.add(pos);

        findWordsHelper(r+1, c, board, set, root, res, word);
        findWordsHelper(r, c+1, board, set, root, res, word);
        findWordsHelper(r, c-1, board, set, root, res, word);
        findWordsHelper(r-1, c, board, set, root, res, word);

        set.remove(pos);
    }

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (Integer stone : stones) {
            maxHeap.offer(stone);
        }

        while (!maxHeap.isEmpty()) {
            var stone1 = maxHeap.poll();
            if (maxHeap.size() >= 1) {
                var stone2 = maxHeap.poll();
                var diff = stone1 - stone2;
                if (diff > 0) {
                    maxHeap.offer(diff);
                }
            }
            else {
                return stone1;
            }
        }

        return 0;
    }

    public class Pair2<K,V> {
        public K val1;
        public V val2;

        public Pair2(K val1, V val2) {
            this.val1 = val1;
            this.val2 = val2;
        }

        public K getVal1() {
            return val1;
        }

        public V getVal2() {
            return val2;
        }
    }

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Pair2<Double, int[]>> maxHeap = new PriorityQueue<>((a,b) -> Double.compare(b.getVal1(), a.getVal1()));
        for (int i = 0; i < points.length; i++) {
            var val = points[i];
            var x = val[0];
            var y = val[1];

            var dist = Math.sqrt((x*x) + (y*y));
            maxHeap.offer(new Pair2<>(dist, val));
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int[][] res = new int[k][2];
        int i = 0;
        while (!maxHeap.isEmpty()) {
            var val = maxHeap.poll().val2;
            res[i++] = new int[] {val[0], val[1]};
        }

        return res;
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (Integer num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        return minHeap.peek();
    }

    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for (char ch : tasks) {
            count[ch - 'A']++;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int c : count) {
            if (c > 0) {
                maxHeap.offer(c);
            }
        }

        int time = 0;
        Queue<int[]> q = new LinkedList<>();
        while (!q.isEmpty() || !maxHeap.isEmpty()) {
            time++;

            if (!maxHeap.isEmpty()) {
                var val = maxHeap.poll() - 1;
                if (val > 0) {
                    q.offer(new int[] {val, time + n});
                }
            }
            else {
                // This is to reduce the number of loops if heap is empty and q items are not ready to be moved to heap
                time = q.peek()[1];
            }

            if (!q.isEmpty() && q.peek()[1] == time) {
                maxHeap.offer(q.poll()[0]);
            }
        }

        return time;
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

    
}
