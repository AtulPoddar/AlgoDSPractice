package Recursion;
public class NToOne {
    public static void main(String[] args) {
        oneToN(10);
    }

    public static void nToOne(int n) {
        if (n == 0) {
            return;
        }

        System.out.println(n);
        nToOne(n - 1);
    }

    public static void oneToN(int n) {
        if (n == 0) {
            return;
        }
        
        oneToN(n - 1);
        System.out.println(n);
    }
}
