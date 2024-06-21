package educative.Arrays;

public class ArrangeMaxMinForm {
    public static void main(String[] args) {
        int[] arr = new int[] {1,2,3,4,5,6,7,8};
        var temp = rearrangeArray(arr);

        for (int i : temp) {
            System.out.println(i);
        }
    }

    public static int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        int i = n-1;
        int j = 0;
        while (j <= n-1) {
            result[j] = nums[i--];
            j = j+2;
        }

        j = 1;
        i = 0;
        while (j <= n-1) {
            result[j] = nums[i++];
            j = j+2;
        }

        // Replace this placeholder return statement with your code
        return result;
    }
}

