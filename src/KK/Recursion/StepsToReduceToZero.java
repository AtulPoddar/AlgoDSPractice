package KK.Recursion;
//Find number of steps to reduce a number to zero - If number is even, divide by 2, if it is odd, subtract by 1, finally to zero.

public class StepsToReduceToZero {
    public static void main(String[] args) {
        System.out.println(steps(8, 0));
    }

    public static int steps(int n, int count) {
        if (n == 0) {
            return count;
        }

        return steps(n % 2 == 0 ? n/2 : n-1, count + 1);
    }
}
