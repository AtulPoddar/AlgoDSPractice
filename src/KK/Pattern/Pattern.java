package KK.Pattern;

public class Pattern {
    public static void main(String[] args) {
        patternRec2(8,0);
    }

    // *****
    // ****
    // ***
    // **
    // *
    public static void pattern3(int n) {
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n-i+1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    // 1
    // 12
    // 123
    // 1234
    // 12345
    public static void pattern4(int n) {
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    // *
    // **
    // ***
    // ****
    // *****
    // ****
    // ***
    // **
    // *
    public static void pattern5(int n) {
        for(int i=1; i<=2*n-1; i++) {
            int col = i<=n ? i : 2*n-i;
            for(int j=1; j<=col; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    //     *
    //    * *
    //   * * *
    //  * * * *
    // * * * * *
    //  * * * *
    //   * * *
    //    * *
    //     *
    public static void pattern28(int n) {
        for(int i=1; i<=2*n-1; i++) {
            int initialSpace = i<=n ? n-i : i-n;
            int col = i<=n ? i : 2*n-i;
            for(int k=1; k<=initialSpace; k++) {
                System.out.print(" ");
            }
            for(int j=1; j<=col; j++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }

    //     1
    //    212
    //   32123
    //  4321234
    // 543212345
    public static void pattern30(int n) {
        for(int i=1; i<=n; i++) {
            int initialSpace = n-i;
            int col = 2*i-1;
            for(int k=1; k<=initialSpace; k++) {
                System.out.print(" ");
            }
            for(int j=1; j<=col; j++) {
                int print = j<=i ? i-j+1 : j-i+1;
                System.out.print(print);
            }
            System.out.println();
        }
    }

    // ****
    // ***
    // **
    // *
    public static void patternRec1(int r, int c) {
        if (r == 0) {
            return;
        }

        if (r > c) {
            System.out.print("*");
            patternRec1(r, c+1);
        }
        else {
            System.out.println();
            patternRec1(r-1, 0);
        }
    }

    // *
    // **
    // ***
    // ****
    public static void patternRec2(int r, int c) {
        if (r == 0) {
            return;
        }

        if (r > c) {
            patternRec2(r, c+1);
            System.out.print("*");
        }
        else {
            patternRec2(r-1, 0);
            System.out.println();
        }
    }
}
