package CycleSortPattern;

public class SmallestMissingPosNum {
    public static void main(String[] args) {
        int[] arr = new int[] {3,4,-1,-2,-3,-4,1,2,5};
        System.out.println(find(arr));
    }

    public static int find(int[] arr) {
        int i = 0;
        while (i < arr.length) {
            if (i == arr[i]-1 || arr[i] <= 0 || arr[i] >= arr.length) {
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

        i=0;
        while (i < arr.length) {
            if (i != arr[i]-1) {
                return i+1;
            }

            i++;
        }

        return i+1;
    }
}
