package KK.Searching;

public class BinarySearch {
    public static void main(String[] args) {
        //System.out.println(search(new int[] {1,5,8,34,67,89,100,109}, 1));

        int[] arr = {1,5,8,34,67,89,100,109};
        System.out.println(searchRec(arr, 34, 0, arr.length));
    }

    public static int search(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end-start)/2;

            if (target > arr[mid]) {
                start = mid+1;
            }
            else if (target < arr[mid]) {
                end = mid-1;
            }
            else {
                return mid;
            }
        }

        return -1;
    }

    public static int searchRec(int[] arr, int target, int start, int end) {
        if (start > end) {
            return -1;
        }

        int mid = start + (end-start)/2;

        if (arr[mid] == target) {
            return mid;
        }
        else if (target > arr[mid]) {
            return searchRec(arr, target, mid+1, end);
        }
        else {
            return searchRec(arr, target, start, mid-1);
        }
    }
}
