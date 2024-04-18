package BinarySearchPattern;

public class MountainPeak {
    public static void main(String[] args) {
        int[] arr = new int[] {1,2,3,3,3,3,5,6,7,8,9,4,3,3,3,3,3,3,3,3,2,1};
        //System.out.println(find2(arr));
        //System.out.println(findTarget(arr, 57));
        System.out.println(findTarget2(arr, 4));
    }

    public static int find(int[] arr) {
        int s=0;
        int e=arr.length-1;

        while (s<=e) {
            int m = s + (e-s)/2;

            if (arr[m] > arr[m+1] && arr[m] > arr[m-1]) {
                return arr[m];
            }

            if (arr[m] < arr[m+1]) {
                s = m+1;
            }
            else if (arr[m] < arr[m-1]) {
                e = m-1;
            }
        }

        return -1;
    }

    public static int find2(int[] arr) {
        int s=0;
        int e=arr.length-1;

        while (s<e) {
            int m = s + (e-s)/2;

            if (arr[m] > arr[m+1]) {
                e = m;    
            }
            else {
                s = m+1;
            }
        }

        return s;
    }

    public static int findTarget(int[] arr, int target) {
        int s=0;
        int e=arr.length-1;

        int ans = -1;
        while (s<=e) {
            int m = s + (e-s)/2;
            
            if (target == arr[m]) {
                ans = m;
                e = m-1;
            }
            else if (m < arr.length-1 && arr[m] < arr[m+1]) {
                if (target > arr[m]) {
                    s = m+1;
                }
                else {
                    e = m-1;
                }
            }
            else if (m > 0 && arr[m] < arr[m-1]) {
                if (target > arr[m]) {
                    e = m-1;
                }
                else {
                    s = m+1;
                }
            }
            else {
                if (target > arr[m]) {
                    s = m+1;
                }
                else {
                    e = m-1;
                }
            }
        }

        return ans;
    }

    public static int findTarget2(int[] arr, int target) {
        // Find Peak Element index
        int peakIndex = find2(arr);

        //Binary Search in 1st half
        int searchIndex = binSearch(arr, target, 0, peakIndex);

        //If not found, search in later half of array
        if (searchIndex == -1) {
            searchIndex = reverseBinSearch(arr, target, peakIndex+1, arr.length-1);
        }

        return searchIndex;
    }

    public static int binSearch(int[] arr, int target, int s, int e) {
        int ans = -1;
        while (s<=e) {
            int m = s + (e-s)/2;

            if (target > arr[m]) {
                s=m+1;
            }
            else if (target < arr[m]) {
                e = m-1;
            }
            else {
                ans = m;
                e = m-1;
            }
        }

        return ans;
    }

    public static int reverseBinSearch(int[] arr, int target, int s, int e) {
        int ans = -1;
        while (s<=e) {
            int m = s + (e-s)/2;

            if (target < arr[m]) {
                s=m+1;
            }
            else if (target > arr[m]) {
                e = m-1;
            }
            else {
                ans = m;
                e = m-1;
            }
        }

        return ans;
    }
}
