package educative.Arrays;

public class SecondMax {
    public static void main(String[] args) {
        int[] arr = new int[] {78,4,1,79,23,900};
        System.out.println(findSecondMaximum(arr));
    }

    public static int findSecondMaximum(int[] nums) {
        int firstMax = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;

        for (int num : nums) {
            if (num > secondMax) {
                if (num < firstMax) {
                    secondMax = num;
                }
                else if (num > firstMax) {
                    secondMax = firstMax;
                    firstMax = num;
                }
            }
        }

        // Replace this placeholder return statement with your code
        return secondMax;
    }
}
