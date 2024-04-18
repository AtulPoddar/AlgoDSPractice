package Sorting;

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {6,4,9,2,0,4,89,12};
        qSort(arr, 0, arr.length-1);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void qSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }

        int mid = low + (high-low)/2;
        int pivot = arr[mid];
        int s = low;
        int e = high;

        while (s<=e) {
            while (arr[s] < pivot) {
                s++;
            }
            while (arr[e] > pivot) {
                e--;
            }
            if (s<=e) {
                int temp = arr[s];
                arr[s] = arr[e];
                arr[e] = temp;

                s++;
                e--;
            }
        }

        qSort(arr, low, e);
        qSort(arr, s, high);
    }
}
