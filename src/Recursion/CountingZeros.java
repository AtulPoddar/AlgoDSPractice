package Recursion;
public class CountingZeros {
    public static void main(String[] args) {
        System.out.println(countZeros(110004, 0));
    }

    public static int countZeros(int n, int count) {
        if (n == 0) {
            return count;
        }

        return countZeros(n/10, n%10 == 0 ? count + 1 : count);
    }
}
