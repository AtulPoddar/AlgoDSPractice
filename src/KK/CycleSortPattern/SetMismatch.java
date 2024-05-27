package KK.CycleSortPattern;

import java.util.ArrayList;

public class SetMismatch {
    public static void main(String[] args) {
        int[] arr = new int[] {5,2,1,3,2};
        System.out.println(mismatchSet(arr));
    }

    public static ArrayList<Integer> mismatchSet(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            if (i == arr[i]-1 || arr[arr[i]-1] == arr[i]) {
                i++;
            }
            else {
                int swapIndex1 = i;
                int swapIndex2 = arr[i]-1;

                int temp = arr[swapIndex1];
                arr[swapIndex1] = arr[swapIndex2];
                arr[swapIndex2] = temp;
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        i=0;
        while (i < arr.length) {
            if (i != arr[i]-1) {
                result.add(arr[i]);
                result.add(i+1);
            }

            i++;
        }
        
        return result;
    } 
}
