package Recursion;

public class LineraSearch {
    public static void main(String[] args) {
        System.out.println(search(new int[] {1,2,3,4,5}, 2, 0));
    }

    public static int search(int[] arr, int target, int index) {
        if (index == arr.length) {
            return -1;
        }

        return arr[index] == target ? index : search(arr, target, index + 1);
    }
}
