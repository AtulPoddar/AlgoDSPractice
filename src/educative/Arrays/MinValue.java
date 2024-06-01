package educative.Arrays;

public class MinValue {
    public static void main(String[] args) {
        
    }

    public static int findMinimum(int[] arr) {
        int min_idx = 0;

        for (int i=1; i<arr.length; i++) {
            if (arr[i] < arr[min_idx]) {
                min_idx = i;
            }
        }

        // Replace this placeholder return statement with your code
        return arr[min_idx];
    }
}
