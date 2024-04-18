package Recursion;
public class Palindrome {
    public static void main(String[] args) throws Exception {
        System.out.println(PalinRec(1234545));
    }

    public static boolean Palin(int n) {
        int count = (int)(Math.log10(n)) + 1;
        int start = count;
        int end = 1;
        
        while(start >= end) {
            if(((n / Math.pow(10, start - 1)) % 10) != ((n % Math.pow(10, end)) / Math.pow(10, end - 1))) {
                return false;
            }
            
            start--;
            end ++;
        }
        
        return true;
    }

    public static boolean PalinRec(int n) {
        int count = (int)Math.log10(n) + 1;

        return RecHelper(n, count);
    }

    public static boolean RecHelper(int n, int count) {
        if (count <= 1) {
            return true;
        }

        if (n % 10 != (n / (int)Math.pow(10, count - 1))) {
            return false;
        }

        return RecHelper((n/10) % (int)Math.pow(10, count - 2), count - 2);
    }
}
