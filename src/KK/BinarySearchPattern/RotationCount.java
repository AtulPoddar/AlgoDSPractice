package KK.BinarySearchPattern;

public class RotationCount {
    public static void main(String[] args) {
        int[] arr = new int[] {5,6,7,8,9,10,11,12,13};
        System.out.println(count(arr));
    }

    public static int count(int[] arr) {
        return findPivotIndex(arr) + 1;
    }

    public static int findPivotIndex(int[] arr) {
        int s=0;
        int e=arr.length-1;

        while (s<e) {
            int m = s + (e-s)/2;

            if (m < e && arr[m] > arr[m+1]) {
                return m;
            }

            if (m > s && arr[m] < arr[m-1]) {
                return m-1;
            }

            if (arr[s] >= arr[m]) {
                e = m;
            }
            else {
                s = m+1;
            }
        }

        return -1;
    }
}
