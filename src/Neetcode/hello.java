package Neetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import Neetcode.LinkedList.ListNode;

public class hello {
    public static void main(String[] args) {
        //var temp = 'a';
        //System.out.println((int)temp);

        //var temp1 = groupAnagrams(new String[] {"act","pots","tops","cat","stop","hat"});
        //System.out.println(temp1);

        //System.out.println(Arrays.toString(productExceptSelf2(new int[] {1,2,4,6})));

        //System.out.println(isPalindrome("Was it a car or a cat I saw"));

        //System.out.println(trap2(new int[] {1,2,3,4,5,10}));

        //System.out.println(evalRPN(new String[] {"1","2","+","3","*","4","-"}));
        //System.out.println(largestRectangleArea(new int[] {7,1,7,2,2,4}));

        //System.out.println(search2(new int[] {3,1}, 3));

        //System.out.println(findMedianSortedArrays(new int[] {1,2}, new int[] {3}));

        //System.out.println(maxProfit(new int[] {2,1,2,1,0,1,2}));

        //System.out.println(characterReplacement("AAABABB", 1));

        //System.out.println(checkInclusion("ab", "lecabee"));

        //System.out.println(minWindow("a", "b"));

        /*LRUCache obj = new LRUCache(3);
            obj.put(1, 1);
            obj.put(2, 2);
            obj.put(3, 3);
            System.out.println(obj.get(1));
            System.out.println(obj.get(2));
            System.out.println(obj.get(4));
            obj.put(4, 4);
            System.out.println(obj.get(1));
            System.out.println(obj.get(2));
            System.out.println(obj.get(3));
            System.out.println(obj.get(4));
            System.out.println(obj.get(2));
            obj.put(1, 8);
            obj.put(3, 7);
            System.out.println(obj.get(1));
            System.out.println(obj.get(2)); */

            //LinkedList obj = new LinkedList<>();
            //ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6, null))))));
            //obj.reverseKGroup(head, 3);

            //var temp = generateParenthesis(2);

            int[] nums = new int[] {2,0,1};
            sortColors2(nums);
            System.out.println(nums);
    }

    public boolean hasDuplicate(int[] nums) {

        var map = new HashMap<Integer, Integer>();
        for (int i : nums) {
            if (map.containsKey(i)) {
                var value = map.get(i);
                map.put(i, value+1);
            }
            else {
                map.put(i, 1);
            }
        }

        for (var e : map.entrySet()) {
            if (e.getValue() > 1) {
                return true;
            }
        }

        return false;
    }

    public boolean isAnagram(String s, String t) {
        
        if (s.length() != t.length()) {
            return false;
        }

        var sMap = new HashMap<Character, Integer>();
        for(int i=0; i<s.length(); i++) {
            Character ch = s.charAt(i);
            if (sMap.containsKey(ch)) {
                var value = sMap.get(ch);
                sMap.put(ch, value+1);
            }
            else {
                sMap.put(ch, 1);
            }
        }

        for(int i=0; i<t.length(); i++) {
            Character ch = t.charAt(i);
            if (sMap.containsKey(ch)) {
                var value = sMap.get(ch);
                if (value == 1) {
                    sMap.remove(ch);
                }
                else {
                    sMap.put(ch, value-1);
                }
            }
            else {
                return false;
            }
        }

        return true;
    }

    public int[] twoSum(int[] nums, int target) {
        var map = new HashMap<Integer, Integer>();
        int[] result = new int[2];

        for (int i=0; i<nums.length ; i++) {
            var diff = target - nums[i];
            if (map.containsKey(diff)) {
                result[0] = map.get(diff);
                result[1] = i;
                return result;
            }
            else {
                map.put(nums[i], i);
            }
        }

        return result;
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        var map = new HashMap<String,List<String>>();
        for (String str : strs) {
            int[] alpbs = new int[26];
            for (int i=0; i<str.length(); i++) {
                Character ch = str.charAt(i);
                alpbs[ch - 'a']++;
            }

            String key = Arrays.toString(alpbs);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str);
        }

        return new ArrayList<>(map.values());
    }

    public static int[] topKFrequent(int[] nums, int k) {
        var map = new HashMap<Integer,Integer>();
        for (int i : nums) {
            map.putIfAbsent(i, 0);
            map.put(i, map.get(i) + 1);
        }

        var temp = map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(k).collect(Collectors.toList());

        int[] result = new int[k];
        for(int i=0; i<k; i++) {
            result[i] = temp.get(i).getKey();
        }

        return result;
    }

    public static int[] topKFrequent2(int[] nums, int k) {
        
        var map = new HashMap<Integer, Integer>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        List<Integer>[] arr = new List[nums.length+1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new ArrayList<>();
        }
        for (var entry : map.entrySet()) {
            arr[entry.getValue()].add(entry.getKey());
        }

        var result = new int[k];
        int index = 0;
        for (int i = arr.length - 1; i > 0 && index < k; i--) {
            for (int val : arr[i]) {
                result[index++] = val;
                if (index == k) {
                    return result;
                }
            }
        }

        return result;
    }

    public String encode(List<String> strs) {
        if (strs.isEmpty()) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length()).append("#").append(str);
        }

        return sb.toString();
    }

    public List<String> decode(String str) {
        var result = new ArrayList<String>();
        if (str.isEmpty()) {
            return result;
        }

        int i=0;
        while (i < str.length()) {
            int j = i;
            StringBuilder num = new StringBuilder();
            while (str.charAt(j) != '#') {
                num.append(str.charAt(j));
                j++;
            }
            Integer numInt = Integer.parseInt(num.toString());

            i = j+1;
            j = 0;

            StringBuilder sb = new StringBuilder();
            while (j < numInt) {
                sb.append(str.charAt(i));
                i++;
                j++;
            }

            result.add(sb.toString());
        }

        return result;
    }

    public int[] productExceptSelf(int[] nums) {
        
        var result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int product = 1;
            for (int j = 0; j < nums.length; j++) {
                if (i != j) {
                    product *= nums[j];
                }
            }

            result[i] = product;
        }

        return result;
    }

    public static int[] productExceptSelf2(int[] nums) {
        
        int prod = 1;
        int countNums = 0;
        for (int i : nums) {
            if (i == 0) {
                countNums++;
            }
            else {
                prod *= i;
            }
        }

        if (countNums > 1) {
            return new int[nums.length];
        }

        var result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (countNums > 0) {
                result[i] = nums[i] == 0 ? prod : 0;
            }
            else {
                result[i] = prod / nums[i];
            }
        }

        return result;
    }

    public static int[] productExceptSelf3(int[] nums) {
        
        int[] prefix = new int[nums.length];
        int prod = 1;
        for (int i = 0; i < nums.length; i++) {
            prod *= nums[i];
            prefix[i] = prod;
        }

        int[] postfix = new int[nums.length];
        prod = 1;
        for (int i = nums.length-1; i >= 0; i--) {
            prod *= nums[i];
            postfix[i] = prod;
        }

        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int preProd = i > 0 ? prefix[i-1] : 1;
            int postProd = i < nums.length - 1 ? postfix[i+1] : 1;
            result[i] = preProd * postProd;
        }

        return result;
    }

    public static int[] productExceptSelf4(int[] nums) {
        
        int[] result = new int[nums.length];
        result[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i-1] * nums[i-1];
        }

        int postVal = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            postVal = i == nums.length-1 ? 1 : postVal*nums[i+1];
            result[i] = result[i] * postVal;
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
                var squareKey = (r/3) + "," + (c/3);
                if (rows.computeIfAbsent(r, k -> new HashSet<>()).contains(board[r][c]) ||
                    cols.computeIfAbsent(c, k -> new HashSet<>()).contains(board[r][c]) ||
                    squares.computeIfAbsent(squareKey, k -> new HashSet<>()).contains(board[r][c])) {
                    return false;
                }

                rows.get(r).add(board[r][c]);
                cols.get(c).add(board[r][c]);
                squares.get(squareKey).add(board[r][c]);
            }
        }

        return true;
    }

    public int longestConsecutive(int[] nums) {
        
        Set<Integer> set = new HashSet<>();
        for (Integer val : nums) {
            set.add(val);
        }

        int result = 0;
        for (Integer value : set) {
            int length = 0;
            if (!set.contains(value - 1)) {
                length++;
                while (set.contains(value + length)) {
                    length++;
                }
            }

            result = Math.max(length, result);
        }

        return result;
    }

    public static boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return false;
        }

        int st = 0;
        int en = s.length() - 1;

        while (st < en) {
            while(st < en && !isAlphaNumeric(s.charAt(st))) {
                st++;
            }
            while (st < en && !isAlphaNumeric(s.charAt(en))) {
                en--;
            }
            if (Character.toLowerCase(s.charAt(st)) == Character.toLowerCase(s.charAt(en))) {
                st++;
                en--;
            }
            else {
                return false;
            }
        }

        return true;
    }

    public static boolean isAlphaNumeric(char ch) {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >='0' && ch <= '9');
    }

    public int[] twoSum2(int[] numbers, int target) {
        
        var result = new int[2];
        if (numbers.length == 0) {
            return result;
        }
        
        int l = 0;
        int r = numbers.length - 1;

        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum == target) {
                result[0] = l+1;
                result[1] = r+1;
                return result;
            }
            else if (sum > target) {
                r--;
            }
            else {
                l++;
            }
        }

        return result;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            // If any positive number, that means sum will never be zero now.
            if (nums[i] > 0) {
                break;
            }
            // If same number as previous loop, continue, as no duplicate results needed.
            if (i > 0 && nums[i] == nums[i-1]) {
                continue;
            }

            int l = i+1;
            int r = nums.length - 1;

            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum > 0) {
                    r--;
                }
                else if (sum < 0) {
                    l++;
                }
                else {
                    result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    while (l < r && nums[l] == nums[l-1]) {
                        l++;
                    }
                }
            }
        }

        return result;
    }

    public int maxArea(int[] heights) {
        int result = 0;

        int l = 0;
        int r = heights.length - 1;
        while (l < r) {
            int area = Math.min(heights[l], heights[r])* (r-l);
            result = Math.max(result, area);

            if (heights[l] <= heights[r]) {
                l++;
            }
            else {
                r--;
            }
        }

        return result;
    }

    public static int trap(int[] height) {
        int result = 0;

        int[] maxleft = new int[height.length];
        maxleft[0] = 0;
        for (int i = 1; i < height.length; i++) {
            maxleft[i] = Math.max(maxleft[i-1], height[i-1]);
        }

        int[] maxright = new int[height.length];
        maxright[height.length - 1] = 0;
        for (int i = height.length - 2; i >= 0; i--) {
            maxright[i] = Math.max(maxright[i+1], height[i+1]);
        }

        for (int i = 0; i < height.length; i++) {
            int waterHeight = Math.min(maxleft[i], maxright[i]) - height[i];
            if (waterHeight > 0) {
                result += waterHeight;
            }
        }

        return result;
    }

    public static int trap2(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int l = 0, r = height.length - 1;
        int leftMax = height[l], rightMax = height[r];
        int res = 0;
        while (l < r) {
            if (leftMax < rightMax) {
                l++;
                leftMax = Math.max(leftMax, height[l]);
                res += leftMax - height[l];
            } else {
                r--;
                rightMax = Math.max(rightMax, height[r]);
                res += rightMax - height[r];
            }
        }
        return res;
    }

    public boolean isValid(String s) {
        if (s.isEmpty()) {
            return false;
        }

        Stack<Character> stk = new Stack<>();
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

    public static int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        
        Stack<Integer> stk = new Stack<>();
        for (String token : tokens) {
            if (token == "+" || token == "-" || token == "*" || token == "/") {
                if (stk.size() < 2) {
                    return 0;
                }
                else {
                    var elem2 = stk.pop();
                    var elem1 = stk.pop();
                    switch (token) {
                        case "+":
                            stk.push(elem1 + elem2);
                            break;
                        case "-":
                            stk.push(elem1 - elem2);
                            break;
                        case "*":
                            stk.push(elem1 * elem2);
                            break;
                        case "/":
                            stk.push(elem1 / elem2);
                            break;
                        default:
                            break;
                    }
                }
            }
            else {
                stk.push(Integer.parseInt(token));
            }
        }

        return stk.pop();
    }

    public static List<String> generateParenthesis(int n) {
        return paran("", 0, 0, n);
    }

    public static List<String> paran(String p, int open, int close, int n) {
        if (open == close && open == n) {
            var result = new ArrayList<String>();
            result.add(p);
            return result;
        }

        var result = new ArrayList<String>();
        if (open < n) {
            result.addAll(paran(p + "(", open+1, close, n));
        }

        if (close < open) {
            result.addAll(paran(p + ")", open, close+1, n));
        }

        return result;
    }

    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
            return new int[0];
        }
        
        int[] result = new int[temperatures.length];
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            int t = temperatures[i];
            while (!stack.isEmpty() && t > stack.peek()[0]) {
                var temp = stack.pop();
                result[temp[1]] = i - temp[1];
            }
            stack.push(new int[]{t,i});
        }

        return result;
    }

    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < position.length; i++) {
            arr[i][0] = position[i];
            arr[i][1] = speed[i];
        }

        Arrays.sort(arr, (a,b) -> Integer.compare(b[0], a[0]));
        double prevTime = (double)(target - arr[0][0]) / arr[0][1];
        int fleet = 1;
        for (int i = 1; i < arr.length; i++) {
            double currTime = (double)(target - arr[i][0]) / arr[i][1];
            if (currTime > prevTime) {
                fleet++;
                prevTime = currTime;
            }
        }

        return fleet;
    }

    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        
        Stack<Integer[]> stk = new Stack<>();
        int maxArea = 0;

        stk.push(new Integer[] {0, heights[0]});
        for (int i = 1; i < heights.length; i++) {
            if (heights[i] == stk.peek()[1]) {
                continue;
            }
            if (heights[i] > stk.peek()[1]) {
                stk.push(new Integer[] {i, heights[i]});
            }
            else {
                int lastPoppedIndex = 0;
                while (!stk.isEmpty() && stk.peek()[1] > heights[i]) {
                    var elem = stk.pop();
                    var area = (i - elem[0]) * elem[1];
                    maxArea = Math.max(maxArea, area);
                    lastPoppedIndex = elem[0];
                }
                if (stk.isEmpty()) {
                    stk.push(new Integer[] {0, heights[i]});
                }
                else if (stk.peek()[1] < heights[i]) {
                    stk.push(new Integer[] {lastPoppedIndex, heights[i]});
                }
            }
        }

        while (!stk.isEmpty()) {
            var popped = stk.pop();
            var area = ((heights.length) - popped[0]) * popped[1];
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int s = 0;
        int e = nums.length-1;

        while (s <= e) {
            int m = s + (e-s)/2;

            if (target > nums[m]) {
                s = m+1;
            }
            else if (target < nums[m]) {
                e = m-1;
            }
            else {
                return m;
            }
        }

        return -1;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;

        int s = 0;
        int e = row*col - 1;

        while (s<=e) {
            int m = s + (e-s)/2;
            int r = m/col;
            int c = m%col;

            if (target > matrix[r][c]) {
                s = m+1;
            }
            else if (target < matrix[r][c]) {
                e = m-1;
            }
            else {
                return true;
            }
        }

        return false;
    }

    public int minEatingSpeed(int[] piles, int h) {
        int s = 1;
        int e = Arrays.stream(piles).max().getAsInt();
        int result = e;

        while (s <= e) {
            int m = s + (e-s)/2;
            int totalTime = 0;
            for (int pile : piles) {
                totalTime += Math.ceil((double)pile/m);
            }

            if (totalTime <= h) {
                result = m;
                e = m-1;
            }
            else {
                s = m+1;
            }
        }

        return result;
    }

    public static int findMin(int[] nums) {
        int pivotIndex = findPivotIndex(nums);
        return nums[(pivotIndex+1) % nums.length];
    }

    public static int findPivotIndex(int[] nums) {
        int s = 0;
        int e = nums.length-1;

        while (s <= e) {
            int m = s + (e-s)/2;
            if (m < nums.length-1 && nums[m] > nums[m+1]) {
                return m;
            }
            else if (m > 0 && nums[m] < nums[m-1]) {
                return m-1;
            }
            else if (nums[s] >= nums[m]) {
                e = m-1;
            }
            else {
                s = m+1;
            }
        }

        return -1;
    }

    public static int search2(int[] nums, int target) {
        int pivotIndex = findPivotIndex(nums);
        int index = binSearch(nums,0,pivotIndex,target);
        if (index == -1) {
            return binSearch(nums, pivotIndex+1, nums.length-1, target);
        }

        return index;
    }

    public static int binSearch(int[] nums, int s, int e, int target) {
        while (s <= e) {
            int m = s + (e-s)/2;
            if (target > nums[m]) {
                s = m+1;
            }
            else if (target < nums[m]) {
                e = m-1;
            }
            else {
                return m;
            }
        }

        return -1;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] A = nums1;
        int[] B = nums2;
        int total = nums1.length + nums2.length;
        int half = (total)/2;

        if (A.length > B.length) {
            int[] temp = A;
            A = B;
            B = temp;
        }

        int s = 0;
        int e = A.length-1;
        while (s <= e) {
            int i = s + (e-s)/2;
            int j = half-i;

            int Aleft = i >= 0 ? A[i] : Integer.MIN_VALUE;
            int Aright = i < A.length-1 ? A[i+1] : Integer.MAX_VALUE;
            int Bleft = j >= 0 ? B[j] : Integer.MIN_VALUE;
            int Bright = j < B.length-1 ? B[j+1] : Integer.MAX_VALUE;

            if (Aleft <= Bright && Bleft <= Aright) {
                if (total % 2 != 0) {
                    return Math.min(Aright, Bright);
                }
                else {
                    return (Math.max(Aleft, Bleft) + Math.min(Bright, Bleft))/2.0;
                }
            }
            else if (Aleft > Bright) {
                e = i-1;
            }
            else {
                s = i+1;
            }
        }

        return -1;
    }

    public static int maxProfit(int[] prices) {
        int l = 0;
        int profit = 0;
        for (int r = 1; r < prices.length; r++) {
            if (prices[l] > prices[r]) {
                l = r;
            }

            profit = Math.max(profit, prices[r] - prices[l]);
        }

        return profit;
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int l = 0;
        int res = 0;
        Set<Character> set = new HashSet<>();
        for (int r = 0; r < s.length(); r++) {
            while (set.contains(s.charAt(r))) {
                set.remove(s.charAt(l));
                l++;
            }

            set.add(s.charAt(r));
            res = Math.max(res, r-l+1);
        }

        return res;
    }

    public static int characterReplacement(String s, int k) {
        int l = 0;
        int res = 0;
        Map<Character,Integer> map = new HashMap<>();
        int maxf = 0;

        for (int r = 0; r < s.length(); r++) {
            map.put(s.charAt(r), map.getOrDefault(s.charAt(r), 0) + 1);
            maxf = Math.max(maxf, map.get(s.charAt(r)));

            while ((r-l+1) - maxf > k) {
                map.put(s.charAt(l), map.get(s.charAt(l)) - 1);
                l++;
            }

            res = Math.max(res, r-l+1);
        }

        return res;
    }

    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        //Map<Character, Integer> map1 = new HashMap<>();
        int[] map1 = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            //map1.put(s1.charAt(i), map1.getOrDefault(s1.charAt(i), 0) + 1);
            map1[s1.charAt(i) - 'a']++;
        }

        int l = 0;
        //Map<Character, Integer> map2 = new HashMap<>();
        int[] map2 = new int[26];
        for (int r = 0; r < s2.length(); r++) {
            //map2.put(s2.charAt(r), map2.getOrDefault(s2.charAt(r), 0) + 1);
            map2[s2.charAt(r) - 'a']++;

            if (r-l+1 > s1.length()) {
                //map2.put(s2.charAt(l), map2.get(s2.charAt(l)) - 1);
                map2[s2.charAt(l) - 'a']--;
                l++;
            }

            if (r-l+1 == s1.length()) {
                if (Arrays.toString(map1).equals(Arrays.toString(map2))) {
                    return true;
                }
            }
        }

        return false;
    }

    public static String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }

        Map<Character,Integer> needMap = new HashMap<>();
        Map<Character,Integer> haveMap = new HashMap<>();
        int needCount = 0;
        int haveCount = 0;
        for (int i = 0; i < t.length(); i++) {
            needMap.put(t.charAt(i), needMap.getOrDefault(t.charAt(i), 0) + 1);
            haveMap.put(t.charAt(i), 0);
        }
        needCount = needMap.size();

        int l = 0;
        int resLen = Integer.MAX_VALUE;
        int fIndex = 0;
        int lIndex = 0;
        for (int r = 0; r < s.length(); r++) {
            var ch = s.charAt(r);
            if (needMap.containsKey(ch)) {
                haveMap.put(ch, haveMap.get(ch) + 1);
                if (haveMap.get(ch) == needMap.get(ch)) {
                    haveCount++;
                }
            }

            while (haveCount == needCount) {
                int len = r-l+1;
                if (resLen > len) {
                    resLen = len;
                    fIndex = l;
                    lIndex = r;
                }

                var chl = s.charAt(l);
                if (haveMap.containsKey(chl)) {
                    haveMap.put(chl, haveMap.get(chl) - 1);
                    if (haveMap.get(chl) < needMap.get(chl)) {
                        haveCount--;
                    }
                }
                l++;
            }
        }

        return resLen == Integer.MAX_VALUE ? "" : s.substring(fIndex, lIndex+1);
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n-k+1];

        for (int i = 0; i < n-k+1; i++) {
            int max = nums[i];
            for (int j = i; j < i+k; j++) {
                max = Math.max(max, nums[j]);
            }
            res[i] = max;
        }

        return res;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n-k+1];

        Deque<Integer> q = new LinkedList<>();
        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            while (!q.isEmpty() && nums[q.getLast()] < nums[r]) {
                q.removeLast();
            }
            q.addLast(r);

            if (l > q.getFirst()) {
                q.removeFirst();
            }

            // This means from this loop we start putting the 1st elem in the queue to result array.
            if ((r+1) >= k) {
                res[l] = nums[q.getFirst()];
                l++;
            }
        }

        return res;
    }

    public int[] getConcatenation(int[] nums) {
        int n = nums.length;
        int[] ans = new int[2*n];
        for (int i = 0; i < 2*n; i++) {
            if (i < n) {
                ans[i] = nums[i];
            }
            else {
                ans[i] = nums[i-n];
            }
        }

        return ans;
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            while (j < prefix.length() && j < strs[i].length()) {
                if (prefix.charAt(j) != strs[i].charAt(j)) {
                    break;
                }

                j++;
            }

            prefix = prefix.substring(0, j);
        }

        return prefix;
    }

    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[k++] = nums[i];
            }
        }

        return k;
    }

    public int majorityElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        Map<Integer,Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0)+1);
        }

        int n = nums.length;
        double majority = Math.floor(n/2) + 1;
        for (var i : map.entrySet()) {
            if (i.getValue() >= majority) {
                return i.getKey();
            }
        }

        return -1;
    }

    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        mergeSort(nums, 0, nums.length-1);
        return nums;
    }

    public void mergeSort(int[] nums, int s, int e) {
        if (e>s) {
            int m = s + (e-s)/2;

            mergeSort(nums, s, m);
            mergeSort(nums, m+1, e);
            merge(nums,s,m,e);
        }
    }

    public void merge(int[] nums, int s, int m, int e) {
        int n1 = m-s+1;
        int n2 = e-m;

        int[] L1 = new int[n1];
        int[] L2 = new int[n2];

        for (int i = 0; i < L1.length; i++) {
            L1[i] = nums[s+i];
        }

        for (int i = 0; i < L2.length; i++) {
            L2[i] = nums[m+1+i];
        }

        int i = 0;
        int j = 0;
        int k = s;

        while (i < n1 && j < n2) {
            if (L1[i] < L2[j]) {
                nums[k++] = L1[i++];
            }
            else {
                nums[k++] = L2[j++];
            }
        }

        while (i < n1) {
                nums[k++] = L1[i++];
            }
        while (j < n2) {
                nums[k++] = L2[j++];
            }
    }

    public void qSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }

        int m = low + (high-low)/2;
        int pivot = nums[m];
        int s = low;
        int e = high;
        
        while (s<=e) {

            while (nums[s] < pivot) {
                s++;
            }
            while (nums[e] > pivot) {
                e--;
            }

            if (s<=e) {
                int temp = nums[s];
                nums[s] = nums[e];
                nums[e] = temp;

                s++;
                e--;
            }
        }

        qSort(nums, low, e);
        qSort(nums, s, high);
    }

    public void sortColors(int[] nums) {
        int[] count = new int[3];
        for (int num : nums) {
            count[num]++;
        }

        int k = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i]-- > 0) {
                nums[k++] = i;
            }
        }
    }

    public static void sortColors2(int[] nums) {
        int n = nums.length;
        int l = 0;
        int r = n-1;

        int i = 0;
        while (i < nums.length && i <= r) {
            if (nums[i] == 0) {
                swap(nums, i, l);
                i++;
                l++;
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

    public static void swap(int[] nums, int i, int j) {
        var temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
