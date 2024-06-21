package educative.Arrays;

public class ArrangePosNegValues {
    public static void main(String[] args) {
        int[] arr = new int[] {1,2,3,4,5};
        rearrange(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void rearrange(int[] arr) {
        int i = 0;
        int j = arr.length-1;

        while (i <= j) {
            while (i < arr.length && arr[i] < 0) {
                i++;
            }
            while (j >= 0 && arr[j] >= 0) {
                j--;
            }

            if (i<=j) {
                var temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                i++;
                j--;
            }
        }
    }
}
