package CycleSortPattern;

public class MissingNumber {
    public static void main(String[] args) {
        int[] arr = new int[] {0,3,2,1};
        System.out.println(findMissing2(arr));
    }

    public static int findMissing1(int[] arr) {
        int sum = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        return n*(n+1)/2 - sum;
    }

    public static int findMissing2(int[] arr) {
        int i=0;
        while (i < arr.length) {
            if (i == arr[i] || arr[i] >= arr.length) {
                i++;
            }
            else {
                int swapIndex1 = i;
                int swapIndex2 = arr[i];

                var temp = arr[swapIndex1];
                arr[swapIndex1] = arr[swapIndex2];
                arr[swapIndex2] = temp;
            }
        }

        i=0;
        while (i < arr.length) {
            if (i != arr[i]) {
                return i;
            }

            i++;
        }

        return i;
    }
}
