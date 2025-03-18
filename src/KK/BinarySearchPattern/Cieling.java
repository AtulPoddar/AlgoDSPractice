package KK.BinarySearchPattern;

// Smallest number greater than or equal to target.
public class Cieling {
    public static void main(String[] args) {
        int[] arr = new int[] {1,7,23,56,78,90,109,110,117};
        System.out.println(ceil(arr, 112));
    }

    public static int ceil(int[] arr, int target) {
        int s=0;
        int e=arr.length-1;

        while (s<=e) {
            int m = s + (e-s)/2;
            
            if ((m > 0 ? target > arr[m-1] : true) && target <= arr[m]) {
                return arr[m];
            }

            if (target > arr[m]) {
                s = m + 1;
            }
            else {
                e = m-1;   
            }
        }

        return -1;
    }
}
