package Sorting;

import java.util.Arrays;

public class CyclicSort {
    public static void main(String[] args) {
        int[] arr = new int[] {3,5,4,1};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            if (i+1 == arr[i]) {
                i++;
            }
            else {
                var swapIndex1 = i;
                var swapIndex2 = arr[i]-1;

                var temp = arr[swapIndex1];
                arr[swapIndex1] = arr[swapIndex2];
                arr[swapIndex2] = temp;
            }
        } 
    }
}
