package Recursion;

public class Dice {
    public static void main(String[] args) {
        diceFace("", 6, 7);
    }

    static int target = 4;

    public static void dice(String p, int up) {
        if (up == 0) {
            int sum = 0;
            for (int i = 0; i < p.length(); i++) {
                sum += p.charAt(i) - '0';
            }
            if (sum == target) {
                System.out.println(p);
            }

            return;
        }

        for (int i = 1; i <= up; i++) {
            String pr = p + i;
            if (pr.length() <= 4) {
                dice(pr, up - i);
            }
        }
    }

    public static void dice2(String p, int targetNum) {
        if (targetNum == 0) {
            System.out.println(p);
            return;
        }

        for (int i = 1; i <= 6 && i<=targetNum; i++) {
            dice2(p+i, targetNum-i);
        }
    }

    public static void diceFace(String p, int targetNum, int face) {
        if (targetNum == 0) {
            System.out.println(p);
            return;
        }

        for (int i = 1; i <= face && i<=targetNum; i++) {
            dice2(p+i, targetNum-i);
        }
    }
}
