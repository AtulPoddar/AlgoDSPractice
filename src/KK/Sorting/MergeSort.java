package KK.Sorting;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {5,0,9,2,6,7,1,3};
        mSort(arr, 0, arr.length-1);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    public static void mSort(int[] arr, int s, int e) {
        
        if (e>s) {
            int m = s + (e-s)/2;
            mSort(arr, s, m);
            mSort(arr, m+1, e);
            merge(arr,s,m,e);
        }
    }

    public static void merge(int[] arr, int s, int m, int e) {

        int n1 = m-s+1;
        int n2 = e-m;

        int[] L1 = new int[n1];
        int[] L2 = new int[n2];

        for(int i=0; i<n1; i++) {
            L1[i] = arr[s+i];
        }
        for(int j=0; j<n2; j++) {
            L2[j] = arr[m+1+j];
        }

        int i = 0;
        int j = 0;
        int k = s;

        while (i<n1 && j<n2) {
            if(L1[i] <= L2[j]) {
                arr[k++] = L1[i++];
            }
            else {
                arr[k++] = L2[j++];
            }
        }

        while(i<n1) {
            arr[k++] = L1[i++];
        }
        while(j<n2) {
            arr[k++] = L2[j++];
        }
    }
}
