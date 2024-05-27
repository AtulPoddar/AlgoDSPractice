package KK.CycleSortPattern;

public class OneRepeatedNum {
    public static void main(String[] args) {
        int[] arr = new int[] {6,1,4,2,3,5,6,7,8,9};
        System.out.println(repeatNum(arr));
    }

    public static int repeatNum(int[] arr) {
        int i=0;
        while (i < arr.length) {
            if (i == arr[i]-1) {
                i++;
            }
            else {
                if (arr[arr[i]-1] == arr[i]) {
                    return arr[i];
                }

                int swapIndex1 = i;
                int swapIndex2 = arr[i]-1;

                int temp = arr[swapIndex1];
                arr[swapIndex1] = arr[swapIndex2];
                arr[swapIndex2] = temp;
            }
        }

        return -1;
    }
}
