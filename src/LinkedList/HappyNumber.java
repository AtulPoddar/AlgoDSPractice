package LinkedList;

/* 

A number is called happy if it leads to 1 after a sequence of steps wherein each step number is replaced by the sum of squares of its digit that is if we start with Happy Number and keep replacing it with digits square sum, we reach 1. 

Examples : 

Input: n = 19
Output: True
19 is Happy Number,
1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1
As we reached to 1, 19 is a Happy Number.

Input: n = 20
Output: False 

*/


public class HappyNumber {

    public static void main(String[] args) {
        System.out.println(HappyNum(19));
    }

    public static boolean HappyNum(int n) {
        int s = n;
        int f = n;

        do {
            s = SumOfSquareDigits(s);
            f = SumOfSquareDigits(SumOfSquareDigits(f));
        } while (s != f);

        if (s == 1) {
            return true;
        }

        return false;
    }

    private static int SumOfSquareDigits(int n) {
        int ans = 0;
        while (n != 0) {
            int rem = n % 10;
            ans += rem * rem;
            n = n / 10;
        }
    
        return ans;
    }
}

