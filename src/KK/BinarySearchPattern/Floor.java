package KK.BinarySearchPattern;

// Largest number smaller than or equal to target.
public class Floor {
    public static void main(String[] args) {
        int[] arr = new int[] {1,7,23,56,78,90,109,110,117};
        System.out.println(flor(arr, 111));
    }

    public static int flor(int[] arr, int target) {
        int s=0;
        int e=arr.length-1;

        while (s<=e) {
            int m = s + (e-s)/2;
            
            if (target >= arr[m] && (m < arr.length-1 ? target < arr[m+1] : true)) {
                return arr[m];
            }

            if (target < arr[m]) {
                e = m-1;
            }
            else {
                s = m+1;   
            }
        }

        return -1;
    }
}
