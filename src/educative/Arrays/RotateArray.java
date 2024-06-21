package educative.Arrays;

public class RotateArray {
    public static void main(String[] args) {
        int[] arr = new int[] {1,2,3,4,5};
        var result = rightRotate(arr, 7);
        for (int i : result) {
            System.out.println(i);
        }
    }

    public static int[] rightRotate(int[] nums, int k) {
        int n = nums.length;
        int rotationCount = k % n;
        if (rotationCount == 0) {
            return nums;
        }
        
        int[] result = new int[n];

        int j=0;
        for (int i=n-rotationCount; i<=n-1; i++) {
            result[j++] = nums[i];
        }

        for (int i=0; i<n-rotationCount; i++) {
            result[j++] = nums[i];
        }

        return result;
    }
}
