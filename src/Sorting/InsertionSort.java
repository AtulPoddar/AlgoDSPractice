package Sorting;

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = {4,6,1,9,0,2,34,7,6,6};
        InsSort(arr);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void InsSort(int[] arr) {
        int n = arr.length;
        
        for(int i=1; i<n; i++) {
            int key = arr[i];
            int j = i-1;

            while (j >= 0 && arr[j] > key) {
                arr[j+1] = arr[j--];
            }

            arr[j+1] = key;
        }
    }
}
