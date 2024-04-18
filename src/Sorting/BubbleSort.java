package Sorting;

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {4,3,2,1,34,0,12,89,43,90,27,4,8};
        BSortRec(arr, arr.length, 0);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void BubbSort(int[] arr) {
        int n = arr.length;
        boolean swapped = false;

        for(int i=0; i<n-1; i++) {
            for(int j=0; j<n-i-1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public static void BSortRec(int[] arr, int count, int index) {
        if (count == 0) {
            return;
        }

        if (count > index+1) {
            if (arr[index] > arr[index+1]) {
                int temp = arr[index];
                arr[index] = arr[index+1];
                arr[index+1] = temp;
            }

            BSortRec(arr, count, index+1);
        }
        else {
            BSortRec(arr, count-1, 0);
        }
    }
}
