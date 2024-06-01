package educative.Arrays;

public class FirstNonRepeat {
    public static void main(String[] args) {
        int[] arr = new int[] {1,2,3,1,2,3,4};
        System.out.println(findFirstUnique(arr));
    }

    public static int findFirstUnique(int[] nums) {
        int result = -1;
        int n = nums.length;

        for (int i=0; i<n; i++) {
            int key = nums[i];
            boolean foundDup = false;
            for (int j=0; j<n; j++) {
                if (i == j) {
                    continue;
                }

                if (key == nums[j]) {
                    foundDup = true;
                    break;
                }
            }

            if (!foundDup) {
                return key;
            }
        }

        // Replace this placeholder return statement with your code
        return result;
    }
}
