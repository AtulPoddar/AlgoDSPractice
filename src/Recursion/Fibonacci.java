package Recursion;
public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(Fib(10));

        for(int i = 0; i<=10; i++) {
            System.out.println(Fib(i));
        }
    }

    public static int Fib(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        return Fib(n - 1) + Fib(n - 2);
    }
}
