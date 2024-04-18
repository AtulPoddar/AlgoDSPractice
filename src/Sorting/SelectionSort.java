package Sorting;

public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {4,6,1,9,0,2,34,7,6,6};
        SelSortRec(arr, 0, 0, 1);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void SelSort(int[] arr) {
        
        int n = arr.length;

        for(int i=0; i<n-1; i++) {
            int min_idx = i;
            for(int j=i+1; j<n; j++) {
                if (arr[min_idx] > arr[j]) {
                    min_idx = j;
                }
            }

            if (min_idx != i) {
                int temp = arr[i];
                arr[i] = arr[min_idx];
                arr[min_idx] = temp;
            }
        }
    }

    public static void SelSortRec(int[] arr, int sortingIndex, int minIndex, int index) {
        if (sortingIndex == arr.length-1) {
            return;
        }

        if (index <= arr.length-1) {
            if (arr[minIndex] > arr[index]) {
                minIndex = index;
            }

            SelSortRec(arr, sortingIndex, minIndex, index+1);
        }
        else {
            if (minIndex != sortingIndex) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[sortingIndex];
                arr[sortingIndex] = temp;
            }

            SelSortRec(arr, sortingIndex+1, sortingIndex+1, sortingIndex+2);
        }
    }
}
