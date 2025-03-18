package KK.BinarySearchPattern;

public class LetterCeiling {
    public static void main(String[] args) {
        char[] arr = new char[] {'a', 'b', 'c','d','e','p','y'};
        System.out.println(lCeil(arr, 'z'));
    }

    public static char lCeil(char[] arr, char target) {
        int s = 0;
        int e = arr.length-1;

        while (s<=e) {
            int m = s + (e-s)/2;

            if (target == arr[m]) {
                return arr[m];
            }
            else if (target < arr[m]) {
                e = m-1;
            }
            else {
                s = m+1;
            }
        }

        return arr[s % arr.length];
    }

    /* public static char lCeil2(char[] arr, char target) {
        int s=0;
        int e=arr.length-1;

        while (s <= e) {
            int m = s + (e-s)/2;

            if (target >= arr[m] && (m < arr.length-1 ? target < arr[m+1] : true)) {
                return arr[(m+1) % arr.length];
            }

            if (target < arr[m]) {
                e = m-1;
            }
            else {
                s = m+1;
            }
        }

        return '0';
    } */
}
