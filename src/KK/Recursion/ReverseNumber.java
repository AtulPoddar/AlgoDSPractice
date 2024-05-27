package KK.Recursion;
public class ReverseNumber {
    public static void main(String[] args) {
        //rev1(7123476);
        //System.out.println(num);

        num = 1234321;
        System.out.println(rev2(num, (int)Math.log10(num) + 1));
    }

    public static int num = 0;

    public static void rev1(int n) {
        if (n == 0) {
            return;
        }

        num = num*10 + n%10;

        rev1(n/10);
    }

    public static int rev2(int n, int count) {
        if (count == 0) {
            return 0;
        }

        return (n % 10) * (int)Math.pow(10, count - 1) + rev2(n/10, count - 1);
    }
}
