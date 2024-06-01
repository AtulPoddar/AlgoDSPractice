package educative.Arrays;

// Challenge: Product of Array Except Self
public class ProductExceptSelf {
    public static void main(String[] args) {
        int[] arr = new int[] {5,3,-1,6,4};
        var temp = findProduct(arr);
        for (int i : temp) {
            System.out.println(i);
        }
    }

    public static int[] findProduct(int arr[]) {
        int n = arr.length;
        int left = 1;
        int[] product = new int[n];

        for (int i=0; i<n; i++) {
            product[i] = left;
            left = left * arr[i];
        }

        int right = 1;
        for (int i = n-1; i >= 0; i--) {
            product[i] = product[i] * right;
            right = right * arr[i];
        }

        // Replace this placeholder return statement with your code
        return product;
    }
}
