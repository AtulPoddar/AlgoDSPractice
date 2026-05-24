package Neetcode;

import java.util.*;
import java.util.LinkedList;

import Neetcode.LinkedList.ListNode;

public class code {
    public static void main(String[] args) {
        var temp = simplifyPath("/neetcode/practice//...///../courses");
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

    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int l = 0;
        int minLength = Integer.MAX_VALUE;
        int sum = 0;
        for (int r = 0; r < nums.length; r++) {
            sum += nums[r];
            while (sum >= target) {
                minLength = Math.min(minLength, r-l+1);
                sum -= nums[l];
                l++;
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0, 1);
        int prefixSum = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            prefixSum += nums[i];
            int need = prefixSum - k;

            if (map.containsKey(need)) {
                count += map.get(need);
            }
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }

    public static class Pair2<K,V> {
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

    // Longest Happy String
    public static String longestDiverseString(int a, int b, int c) {
        PriorityQueue<Pair2<Character,Integer>> maxHeap = new PriorityQueue<>((x,y) -> y.val2 - x.val2);
        if (a > 0) {
            maxHeap.offer(new Pair2<>('a', a));
        }
        if (b > 0) {
            maxHeap.offer(new Pair2<>('b', b));
        }
        if (c > 0) {
            maxHeap.offer(new Pair2<>('c', c));
        }

        StringBuilder sb = new StringBuilder();
        Character lastChar = null;
        Character secondLastChar = null;
        while (!maxHeap.isEmpty()) {
            var maxPeek = maxHeap.peek();
            if ((lastChar == null || secondLastChar == null) || (lastChar != maxPeek.val1 || secondLastChar != maxPeek.val1)) {
                var elem1 = maxHeap.poll();
                sb.append(elem1.val1);
                var temp = lastChar;
                lastChar = elem1.val1;
                secondLastChar = temp;
                if (elem1.val2 - 1 > 0) {
                    maxHeap.offer(new Pair2<>(elem1.val1, elem1.val2 - 1));
                }
            }
            else {
                var elem1 = maxHeap.poll();
                if (maxHeap.isEmpty()) {
                    break;
                }
                var elem2 = maxHeap.poll();
                sb.append(elem2.val1);
                if (elem2.val2 - 1 > 0) {
                    maxHeap.offer(new Pair2<>(elem2.val1, elem2.val2-1));
                }
                maxHeap.offer(elem1);
                var temp = lastChar;
                lastChar = elem2.val1;
                secondLastChar = temp;
            }
        }

        return sb.toString();
    }

    // Word Break 2 (Backtracking)
    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<>(wordDict);
        List<String> res = new ArrayList<>();
        wordBreakHelper(s, 0, new ArrayList<>(), set, res);
        return res;
    }

    public void wordBreakHelper(String s, int i, List<String> curr, HashSet<String> wordSet, List<String> res) {
        if (i == s.length()) {
            res.add(String.join(" ", curr));
            return;
        }

        for (int j = i; j < s.length(); j++) {
            var sub = s.substring(i, j+1);
            if (wordSet.contains(sub)) {
                curr.add(sub);
                wordBreakHelper(s, j+1, curr, wordSet, res);
                curr.remove(curr.size()-1);
            }
        }
    }

    public class TrieNode {
        public Map<Character,TrieNode> children;
        public boolean isWord;

        public TrieNode() {
            children = new HashMap<>();
            isWord = false;
        }

        public void addWord(String word) {
            TrieNode curr = this;
            for (Character ch : word.toCharArray()) {
                if (!curr.children.containsKey(ch)) {
                    curr.children.put(ch, new TrieNode());
                }

                curr = curr.children.get(ch);
            }
            curr.isWord = true;
        }
    }

    // Word Break 2 (Trie+BackTracking)
    public List<String> wordBreak2(String s, List<String> wordDict) {
        TrieNode root = new TrieNode();
        for (String w : wordDict) {
            root.addWord(w);
        }

        List<String> res = new ArrayList<>();
        wbHelper(s, 0, new ArrayList<>(), root, res);
        return res;
    }

    public void wbHelper(String s, int i, List<String> curr, TrieNode root, List<String> res) {
        if (i == s.length()) {
            res.add(String.join(" ", curr));
            return;
        }

        TrieNode node = root;
        StringBuilder word = new StringBuilder();
        for (int j = i; j < s.length(); j++) {
            var ch = s.charAt(j);
            if (!node.children.containsKey(ch)) {
                break;
            }
            node = node.children.get(ch);
            word.append(ch);
            if (node.isWord) {
                curr.add(word.toString());
                wbHelper(s, j+1, curr, root, res);
                curr.remove(curr.size()-1);
            }
        }
    }

    // Combinations
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        combineHelper(n, k, 1, new ArrayList<>(), res);
        return res;
    }

    public void combineHelper(int n, int k, int i, List<Integer> curr, List<List<Integer>> res) {
        if (curr.size() == k) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int j = i; j <= n; j++) {
            curr.add(j);
            combineHelper(n, k, j+1, curr, res);
            curr.remove(curr.size()-1);
        }
    }

    public int mySqrt(int x) {
        int l = 0;
        int r = x;
        int res = 0;

        while (l <= r) {
            int m = l + (r-l)/2;
            long mul = (long) m * m;
            if (mul > x) {
                r = m-1;
            }
            else if (mul < x) {
                l = m+1;
                res = m;
            }
            else {
                return m;
            }
        }

        return res;
    }

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int k = 0;
        int l = 0;
        int r = 0;
        int n = nums.length;
        while (r < n) {
            k++;
            while (r < n && nums[r] == nums[l]) {
                r++;
            }
            if (r == n) {
                return k;
            }

            l++;
            nums[l] = nums[r];
        }

        return k;
    }

    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int l = 0;
        int r = nums.length - 1;
        int i = 0;
        while (i <= r) {
            if (nums[i] == 0) {
                swap(nums, i, l);
                l++;
                i++;
            }
            else if (nums[i] == 2) {
                swap(nums, i, r);
                r--;
            }
            else {
                i++;
            }
        }
    }

    public void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

    public static String simplifyPath(String path) {
        if (path.isEmpty() || path.equals("/")) {
            return path;
        }

        Stack<String> stk = new Stack<>();
        var paths = path.split("/");
        for (String str : paths) {
            if (str.isEmpty() || str.equals(".")) {
                continue;
            }

            if (str.equals("..")) {
                if (!stk.isEmpty()) {
                    stk.pop();
                }
                continue;
            }

            stk.push(str);
        }

        if (stk.isEmpty()) {
            return "/";
        }

        return "/" + String.join("/", stk);
    }
}
