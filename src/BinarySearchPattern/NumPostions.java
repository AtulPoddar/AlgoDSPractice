package BinarySearchPattern;

import java.util.Arrays;

public class NumPostions {
    public static void main(String[] args) {
        int[] arr = new int[] {1,2,3,7,8,9,10,11,12,13,13,14,15,16};
        System.out.println(Arrays.toString(pos(arr, 0)));
    }

    public static int[] pos(int[] arr, int target) {
        int[] ans = new int[] {-1,-1};

        int startIndex = search(arr, target, true);
        int endIndex = search(arr, target, false);

        ans[0] = startIndex;
        ans[1] = endIndex;

        return ans;
    }

    public static int search(int[] arr, int target, boolean findStartingIndex) {
        int ans = -1;

        int s = 0;
        int e = arr.length-1;

        while (s<=e) {
            int m = s + (e-s)/2;

            if (target > arr[m]) {
                s = m+1;
            }
            else if (target < arr[m]) {
                e = m-1;
            }
            else {
                ans = m;
                if (findStartingIndex) {
                    e = m-1;
                }
                else {
                    s = m+1;
                }
            }
        }

        return ans;
    }
}
