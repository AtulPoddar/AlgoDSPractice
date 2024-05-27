package KK.CycleSortPattern;

import java.util.ArrayList;

public class DisappearedNums {
    public static void main(String[] args) {
        int[] arr = new int[] {4,3,2,7,8,2,3,1};
        System.out.println(disNums(arr));
    }

    public static ArrayList<Integer> disNums(int[] arr) {
        int i=0;

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

        var result = new ArrayList<Integer>();
        i=0;
        while (i<arr.length) {
            if (i != arr[i]-1) {
                result.add(i+1);
            }

            i++;
        }

        return result;
    }
}
