package KK.BinarySearchPattern;

public class SpiltArray {
    public static void main(String[] args) {
        int[] arr = new int[] {7,2,5,10,8};
        System.out.println(leastSum(arr, 2));
    }

    public static int leastSum(int[] arr, int m) {
        int start = 0;
        int end = 0;

        for (int i = 0; i < arr.length; i++) {
            start = Math.max(start, arr[i]);
            end += arr[i];
        }

        while (start < end) {
            int mid = start + (end-start)/2;

            int sum = 0;
            int pieces = 1;

            for (int a:arr) {
                if (sum + a > mid) {
                    sum = a;
                    pieces++;
                }
                else {
                    sum += a;
                }
            }

            if (pieces <= m) {
                end = mid;
            }
            else {
                start = mid+1;
            }
        }

        return end;
    }
}
