package educative.Arrays;

// Challenge: Find Two Numbers That Add Up to K
public class TwoNumsAddK {
    public static void main(String[] args) {
        int[] arr = new int[] {-1,9,56,12,-13,-6,23,19,71,-56,-14};
        int[] result = findSum(arr, -44);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }

    public static void sort(int[] arr) {
        mergeSort(arr, 0, arr.length-1);
    }

    private static void mergeSort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = low + (high-low)/2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid+1, high);
            merge(arr, low, mid, high);
        }
    }

    private static void merge(int[] arr, int l, int m, int h) {
        int n1 = m-l+1;
        int n2 = h-m;

        int[] arr1 = new int[n1];
        int[] arr2 = new int[n2];

        int i = 0;
        while (i < n1) {
            arr1[i] = arr[l+i];
            i++;
        }

        int j = 0;
        while (j < n2) {
            arr2[j] = arr[m+1+j];
            j++;
        }

        i = 0;
        j = 0;
        int k = l;
        while (i<n1 && j<n2) {
            if (arr1[i] <= arr2[j]) {
                arr[k++] = arr1[i++];
            }
            else {
                arr[k++] = arr2[j++];
            }
        }

        while (i<n1) {
            arr[k++] = arr1[i++];
        }

        while (j<n2) {
            arr[k++] = arr2[j++];
        }
    }

    public static int[] findSum(int[] arr, int n) {
        int[] result = new int[2];
        
        sort(arr);

        for (int i=0; i<arr.length; i++) {
            int target = n - arr[i];
            int index = BinSearch(arr, target);
            if (index != -1 && index != i) {
                result[0] = arr[i];
                result[1] = arr[index];
                return result;
            }
        }

        return new int[0];
    }

    private static int BinSearch(int[] arr, int target) {
        int s = 0;
        int e = arr.length-1;

        while (s < e) {
            int m = s + (e-s)/2;

            if (arr[m] > target) {
                e = m-1;
            }
            else if (arr[m] < target) {
                s = m+1;
            }
            else {
                return m;
            }
        }

        return -1;
    }
}
