package BinarySearchPattern;

public class RotatedBS {
    public static void main(String[] args) {
        int[] arr = new int[] {7,8,9,10,11,12,1,2,3,4,5};
        System.out.println(search(arr, 1));
    }

    public static int search(int[] arr, int target) {
        int pivot = findPivot(arr);

        if (pivot == -1) {
            return binSearch(arr, 0, arr.length-1, target);
        }

        int firstSearch = binSearch(arr, 0, pivot, target);
        if (firstSearch != -1) {
            return firstSearch;
        }
        else {
            return binSearch(arr, pivot+1, arr.length-1, target);
        }
    }

    public static int findPivot(int[] arr) {
        int s=0;
        int e=arr.length-1;

        while (s<e) {
            int m = s + (e-s)/2;

            if (m < e && arr[m] > arr[m+1]) {
                return m;
            }

            if (m > s && arr[m] < arr[m-1]) {
                return m-1;
            }

            if (arr[s] >= arr[m]) {
                e=m;
            }
            else {
                s=m+1;
            }
        }

        return -1;
    }

    public static int binSearch(int[] arr, int s, int e, int target) {
        
        while (s<=e) {
            int m = s + (e-s)/2;

            if (arr[m] == target) {
                return m;
            }

            if (target > arr[m]) {
                s = m+1;
            }
            else {
                e = m-1;
            }
        }

        return -1;
    }

    public static int findPivotRotated(int[] arr) {
        int s=0;
        int e=arr.length-1;

        while (s<e) {
            int m = s + (e-s)/2;

            if (m < e && arr[m] > arr[m+1]) {
                return m;
            }

            if (m > s && arr[m] < arr[m-1]) {
                return m-1;
            }

            if (arr[s] == arr[m] && arr[s] == arr[e]) {
                if (arr[s] > arr[s+1]) {
                    return s;
                }
                s++;

                if (arr[e] < arr[e-1]) {
                    return e-1;
                }
                e--;
            }
            else if (arr[s] < arr[m] || (arr[s] == arr[m] && arr[m] < arr[e])) {
                s = m;
            }
            else {
                e = m-1;
            }
        }

        return -1;
    }
}
