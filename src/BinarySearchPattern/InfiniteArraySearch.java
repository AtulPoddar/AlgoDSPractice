package BinarySearchPattern;

public class InfiniteArraySearch {
    public static void main(String[] args) {
        int[] arr = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21};
        System.out.println(find(arr, 8));
    }

    public static int find(int[] arr, int target) {
        int s = 0;
        int e = 1;

        int n = 1;
        while (true) {
            if (target <= arr[e]) {
                return binarySearch(arr, target, s, e);
            }

            s = s + (int)Math.pow(2, n);
            e = e + (int)Math.pow(2, n+1);
            n++;
        }
    }

    public static int binarySearch(int[] arr, int target, int start, int end) {
        while (start <= end) {
            int mid = start + (end-start)/2;

            if (target > arr[mid]) {
                start = mid+1;
            }
            else if (target < arr[mid]) {
                end = mid-1;
            }
            else {
                return mid;
            }
        }

        return -1;
    }
}
